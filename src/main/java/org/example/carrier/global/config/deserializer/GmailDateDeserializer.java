package org.example.carrier.global.config.deserializer;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Locale;

public class GmailDateDeserializer {

    private static final DateTimeFormatter FORMATTER = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .appendPattern("EEE, ")
            .appendValueReduced(ChronoField.DAY_OF_MONTH, 1, 2, 1)
            .appendPattern(" MMM yyyy HH:mm:ss ")
            .appendPattern("Z")
            .optionalStart()
            .appendPattern(" '('z')'")
            .optionalEnd()
            .toFormatter(Locale.ENGLISH);

    public static LocalDateTime parse(String dateStr) {
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(dateStr, FORMATTER);
        return zonedDateTime.withZoneSameInstant(ZoneOffset.UTC).toLocalDateTime();
    }
}
