package org.example.carrier.global.feign.gpt.dto.request;

import org.example.carrier.global.feign.gpt.dto.request.element.MailElement;
import org.example.carrier.global.feign.gpt.dto.request.element.ScheduleElement;
import org.example.carrier.global.feign.gpt.dto.request.element.TodoElement;

import java.util.List;

public record GptTodayTipsRequest(
        List<ScheduleElement> schedule,
        List<TodoElement> todo,
        List<MailElement> mail
) {
}
