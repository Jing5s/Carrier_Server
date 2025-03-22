package org.example.carrier.domain.diary.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.carrier.domain.calendar.domain.repository.ScheduleRepository;
import org.example.carrier.domain.category.domain.Category;
import org.example.carrier.domain.category.domain.repository.CategoryRepository;
import org.example.carrier.domain.diary.domain.Diary;
import org.example.carrier.domain.diary.domain.repository.DiaryRepository;
import org.example.carrier.domain.diary.exception.DiaryNotFoundException;
import org.example.carrier.domain.diary.presentation.dto.request.DiaryRecommendRequest;
import org.example.carrier.domain.diary.presentation.dto.request.GetDiariesRequest;
import org.example.carrier.domain.diary.presentation.dto.response.DiaryResponse;
import org.example.carrier.domain.mail.domain.repository.MailRepository;
import org.example.carrier.domain.todo.domain.repository.TodoRepository;
import org.example.carrier.domain.user.domain.User;
import org.example.carrier.global.annotation.CustomService;
import org.example.carrier.global.config.properties.GptProperties;
import org.example.carrier.global.feign.gpt.GptClient;
import org.example.carrier.global.feign.gpt.dto.request.GptBasicRequest;
import org.example.carrier.global.feign.gpt.dto.request.GptTodayTipsRequest;
import org.example.carrier.global.feign.gpt.dto.request.element.MailElement;
import org.example.carrier.global.feign.gpt.dto.request.element.ScheduleElement;
import org.example.carrier.global.feign.gpt.dto.request.element.TodoElement;
import org.example.carrier.global.feign.gpt.dto.response.GptBasicResponse;
import org.example.carrier.global.feign.gpt.dto.response.GptDiaryRecommendResponse;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@CustomService(readOnly = true)
public class QueryDiaryService {
    private final DiaryRepository diaryRepository;
    private final GptClient gptClient;
    private final GptProperties gptProperties;
    private final ObjectMapper objectMapper;
    private final TodoRepository todoRepository;
    private final CategoryRepository categoryRepository;
    private final ScheduleRepository scheduleRepository;
    private final MailRepository mailRepository;

    public DiaryResponse getDiary(Long id, User cUser) {
        Diary diary = diaryRepository.findByIdAndUser(id, cUser)
                .orElseThrow(() -> DiaryNotFoundException.EXCEPTION);

        return new DiaryResponse(diary);
    }

    public List<DiaryResponse> getDiaries(@Valid GetDiariesRequest request, User cUser) {
        return diaryRepository.findAllDiaryByDateAndUser(
                        request.startDateTime(),
                        request.endDateTime(),
                        cUser).stream()
                .map(DiaryResponse::new)
                .toList();
    }

    public GptDiaryRecommendResponse getRecommend(DiaryRecommendRequest request, User cUser) throws JsonProcessingException {


        List<TodoElement> todos = todoRepository.findAllByDateAndUser(request.date(), cUser).stream()
                .map(TodoElement::from)
                .toList();

        List<ScheduleElement> schedules = getTodaySchedules(request.date(), cUser);

        List<MailElement> mails = mailRepository.findAllByUserAndDate(request.date(), cUser).stream()
                .map(MailElement::from)
                .toList();

        GptBasicRequest gptRequest = GptTodayTipsRequest.of(objectMapper, schedules, todos, mails);
        GptBasicResponse gptResponse = gptClient.getGptResponse(gptProperties.getToken(), gptRequest);

        return (GptDiaryRecommendResponse) gptResponse.getResponse(objectMapper, GptDiaryRecommendResponse.class);
    }

    private List<ScheduleElement> getTodaySchedules(LocalDate date, User cUser) {
        List<Category> categories = categoryRepository.findAllByUser(cUser);

        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.plusDays(1).atStartOfDay().minusSeconds(1);
        return scheduleRepository.findScheduleByDateAndCategories(startOfDay, endOfDay, categories, cUser).stream()
                .map(ScheduleElement::from)
                .toList();
    }
}
