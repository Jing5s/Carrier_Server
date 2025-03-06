package org.example.carrier.domain.calendar.presentation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.carrier.domain.calendar.presentation.dto.request.FindCategoryRequest;
import org.example.carrier.domain.calendar.presentation.dto.response.GetSchedulesResponse;
import org.example.carrier.domain.calendar.service.QueryScheduleService;
import org.example.carrier.domain.user.facade.UserFacade;
import org.example.carrier.global.feign.gpt.dto.request.GptBasicRequest;
import org.example.carrier.global.feign.gpt.dto.request.GptMailSummaryRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/schedules")
@RestController
public class QueryScheduleController {
    private final QueryScheduleService queryScheduleService;
    private final ObjectMapper objectMapper;

    @GetMapping
    public List<GetSchedulesResponse> getSchedules(
            @Valid @ModelAttribute FindCategoryRequest request
    ) {
        return queryScheduleService.getSchedules(request, UserFacade.getCurrentUser());
    }

    @GetMapping("/test")
    public GptBasicRequest getGptBasic() throws JsonProcessingException {
        GptMailSummaryRequest request = new GptMailSummaryRequest("sdfsdf", "sdfsdf", "sdfsdfsdf");
        return new GptBasicRequest(objectMapper, request);
    }
}
