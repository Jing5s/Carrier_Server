package org.example.carrier.domain.user.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.carrier.domain.calendar.domain.repository.CustomScheduleRepository;
import org.example.carrier.domain.category.domain.Category;
import org.example.carrier.domain.category.domain.repository.CategoryRepository;
import org.example.carrier.domain.mail.domain.repository.CustomMailRepository;
import org.example.carrier.domain.todo.domain.repository.TodoRepository;
import org.example.carrier.domain.user.domain.User;
import org.example.carrier.domain.user.presentation.dto.response.TodaySummaryResponse;
import org.example.carrier.domain.user.presentation.dto.response.UserProfileResponse;
import org.example.carrier.global.annotation.CustomService;
import org.example.carrier.global.config.properties.GptProperties;
import org.example.carrier.global.feign.gpt.GptClient;
import org.example.carrier.global.feign.gpt.dto.request.GptBasicRequest;
import org.example.carrier.global.feign.gpt.dto.request.GptTodayTipsRequest;
import org.example.carrier.global.feign.gpt.dto.request.element.MailElement;
import org.example.carrier.global.feign.gpt.dto.request.element.ScheduleElement;
import org.example.carrier.global.feign.gpt.dto.request.element.TodoElement;
import org.example.carrier.global.feign.gpt.dto.response.GptBasicResponse;
import org.example.carrier.global.feign.gpt.dto.response.GptTodayTipsResponse;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@CustomService(readOnly = true)
public class QueryUserService {
    private final TodoRepository todoRepository;
    private final CustomScheduleRepository scheduleRepository;
    private final CategoryRepository categoryRepository;
    private final CustomMailRepository mailRepository;
    private final GptClient gptClient;
    private final GptProperties gptProperties;
    private final ObjectMapper objectMapper;

    public UserProfileResponse getUserInfo(User cuser) {
        return new UserProfileResponse(cuser);
    }

    public TodaySummaryResponse getTodaySummary(User cUser) throws JsonProcessingException {
        List<TodoElement> todos = todoRepository.findAllByDateAndUser(LocalDate.now(), cUser).stream()
                .map(TodoElement::from)
                .toList();

        List<ScheduleElement> schedules = getTodaySchedules(cUser);

        List<MailElement> mails = mailRepository.findAllByTodayAndUser(cUser).stream()
                .map(MailElement::from)
                .toList();

        GptBasicRequest request = GptTodayTipsRequest.of(objectMapper, schedules, todos, mails);
        GptBasicResponse gptResponse = gptClient.getGptResponse("Bearer " + gptProperties.getToken(), request);
        GptTodayTipsResponse tips = (GptTodayTipsResponse) gptResponse.getResponse(objectMapper, GptTodayTipsResponse.class);

        return TodaySummaryResponse.of(schedules, todos, mails, (tips.tips()));
    }

    private List<ScheduleElement> getTodaySchedules(User cUser) {
        List<Category> categories = categoryRepository.findAllByUser(cUser);

        LocalDateTime startOfDay = LocalDateTime.now().toLocalDate().atStartOfDay();
        LocalDateTime endOfDay = startOfDay.plusDays(1).minusSeconds(1);
        return scheduleRepository.findScheduleByDateAndCategories(startOfDay, endOfDay, categories, cUser).stream()
                .map(ScheduleElement::from)
                .toList();
    }
}
