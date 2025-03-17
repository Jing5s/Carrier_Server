package org.example.carrier.domain.mail.domain.repository;

import org.example.carrier.domain.mail.domain.Mail;
import org.example.carrier.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MailRepository extends JpaRepository<Mail, Long>, CustomMailRepository {
    Optional<Mail> findByGmailId(String gmailId);
    Boolean existsByUser(User user);
}
