package org.example.carrier.domain.mail.domain.repository;

import org.example.carrier.domain.mail.domain.Mail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MailRepository extends JpaRepository<Mail, Long> {
    Optional<Mail> findByGmailId(String gmailId);
}
