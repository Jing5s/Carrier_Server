package org.example.carrier.global.config.deserializer;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

public class GmailDateDeserializer {

    private static final DateTimeFormatter FORMATTER = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .appendPattern("EEE, dd MMM yyyy HH:mm:ss ")
            .optionalStart()
            .appendPattern("z")
            .optionalEnd()
            .optionalStart()
            .appendPattern("X")
            .optionalEnd()
            .optionalStart()
            .appendPattern("XX")
            .optionalEnd()
            .optionalStart()
            .appendPattern("XXX")
            .optionalEnd()
            .optionalStart()
            .appendPattern(" '('z')'")
            .optionalEnd()
            .toFormatter(Locale.ENGLISH);

    public static LocalDateTime parse(String dateStr) {
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(dateStr, FORMATTER);
        return zonedDateTime.withZoneSameInstant(ZoneId.of("UTC")).toLocalDateTime();
    }
}
