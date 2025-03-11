package org.example.carrier.domain.calendar.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.carrier.domain.calendar.domain.repository.CustomScheduleRepository;
import org.example.carrier.domain.calendar.presentation.dto.request.FindCategoryRequest;
import org.example.carrier.domain.calendar.presentation.dto.response.GetSchedulesResponse;
import org.example.carrier.domain.user.domain.User;
import org.example.carrier.global.annotation.CustomService;

import java.util.List;

@RequiredArgsConstructor
@CustomService(readOnly = true)
public class QueryScheduleService {
    private final CustomScheduleRepository customScheduleRepository;

    public List<GetSchedulesResponse> getSchedules(@Valid FindCategoryRequest request, User cUser) {
        return customScheduleRepository.findScheduleByDate(
                        request.startDate(), request.endDate(), cUser)
                .stream().map(GetSchedulesResponse::new).toList();
    }
}
