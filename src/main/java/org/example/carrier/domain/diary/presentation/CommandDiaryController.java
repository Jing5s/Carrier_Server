package org.example.carrier.domain.diary.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.carrier.domain.diary.presentation.dto.request.CreateDiaryRequest;
import org.example.carrier.domain.diary.service.CommandDiaryService;
import org.example.carrier.domain.user.facade.UserFacade;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/diary")
@RestController
public class CommandDiaryController {
    private final CommandDiaryService commandDiaryService;

    @PostMapping
    public Long createDiary(
            @Valid @RequestBody CreateDiaryRequest request
    ) {
        return commandDiaryService.createDiary(request, UserFacade.getCurrentUser());
    }
}
