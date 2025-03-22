package org.example.carrier.domain.diary.presentation;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.carrier.domain.diary.presentation.dto.request.DiaryRecommendRequest;
import org.example.carrier.domain.diary.presentation.dto.request.GetDiariesRequest;
import org.example.carrier.domain.diary.presentation.dto.response.DiaryResponse;
import org.example.carrier.domain.diary.service.QueryDiaryService;
import org.example.carrier.domain.user.facade.UserFacade;
import org.example.carrier.global.feign.gpt.dto.response.GptDiaryRecommendResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/diaries")
@RestController
public class QueryDiaryController {
    private final QueryDiaryService queryDiaryService;

    @GetMapping("/{id}")
    public DiaryResponse getDiary(
            @PathVariable Long id
    ) {
        return queryDiaryService.getDiary(id, UserFacade.getCurrentUser());
    }

    @GetMapping
    public List<DiaryResponse> getDiaries(
            @Valid @ModelAttribute GetDiariesRequest request
    ) {
        return queryDiaryService.getDiaries(request, UserFacade.getCurrentUser());
    }

    @GetMapping("/recommend")
    public GptDiaryRecommendResponse getRecommend(
            @ModelAttribute DiaryRecommendRequest request
    ) throws JsonProcessingException {
        return queryDiaryService.getRecommend(request, UserFacade.getCurrentUser());
    }
}
