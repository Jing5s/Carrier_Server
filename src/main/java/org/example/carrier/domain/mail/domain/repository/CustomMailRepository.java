package org.example.carrier.domain.mail.domain.repository;

import org.example.carrier.domain.mail.domain.Mail;
import org.example.carrier.domain.user.domain.User;

import java.util.List;

public interface CustomMailRepository {
    List<Mail> findAllByUserOrderByDate(User user);
    Long findMaxHistoryId(User user);
}
