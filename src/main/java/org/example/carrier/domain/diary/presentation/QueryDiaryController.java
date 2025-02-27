package org.example.carrier.domain.diary.presentation;

import lombok.RequiredArgsConstructor;
import org.example.carrier.domain.diary.presentation.dto.response.DiaryResponse;
import org.example.carrier.domain.diary.service.QueryDiaryService;
import org.example.carrier.domain.user.facade.UserFacade;
import org.springframework.web.bind.annotation.GetMapping;
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
    public List<DiaryResponse> getDiaries() {
        return queryDiaryService.getDiaries(UserFacade.getCurrentUser());
    }
}
