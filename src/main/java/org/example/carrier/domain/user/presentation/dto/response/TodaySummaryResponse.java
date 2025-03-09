package org.example.carrier.domain.user.presentation.dto.response;

import org.example.carrier.global.feign.gpt.dto.request.element.MailElement;
import org.example.carrier.global.feign.gpt.dto.request.element.ScheduleElement;
import org.example.carrier.global.feign.gpt.dto.request.element.TodoElement;

import java.util.List;

public record TodaySummaryResponse(
        List<ScheduleElement> schedules,
        List<TodoElement> todos,
        List<MailElement> mails,
        List<String> tips
) {
    public static TodaySummaryResponse of(
            List<ScheduleElement> schedules,
            List<TodoElement> todos,
            List<MailElement> mails,
            List<String> tips
    ) {
        return new TodaySummaryResponse(
                schedules, todos, mails, tips
        );
    }
}
