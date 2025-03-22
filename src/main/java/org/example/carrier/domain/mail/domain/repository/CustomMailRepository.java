package org.example.carrier.domain.mail.domain.repository;

import org.example.carrier.domain.mail.domain.Mail;
import org.example.carrier.domain.user.domain.User;

import java.time.LocalDate;
import java.util.List;

public interface CustomMailRepository {
    List<Mail> findAllByUserOrderByDate(User user);
    List<Mail> findAllByUserAndDate(LocalDate date, User user);
    Long findMaxHistoryId(User user);
}
