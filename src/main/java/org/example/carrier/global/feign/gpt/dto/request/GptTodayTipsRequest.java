package org.example.carrier.global.feign.gpt.dto.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.carrier.global.feign.gpt.dto.request.element.MailElement;
import org.example.carrier.global.feign.gpt.dto.request.element.ScheduleElement;
import org.example.carrier.global.feign.gpt.dto.request.element.TodoElement;

import java.util.List;

public record GptTodayTipsRequest(
        List<ScheduleElement> schedule,
        List<TodoElement> todo,
        List<MailElement> mail
) {
    public static GptBasicRequest of(ObjectMapper objectMapper, List<ScheduleElement> schedule, List<TodoElement> todo, List<MailElement> mail) throws JsonProcessingException {
        GptTodayTipsRequest request = new GptTodayTipsRequest(schedule, todo, mail);

        return GptBasicRequest.todayTips(objectMapper, request);
    }
}
