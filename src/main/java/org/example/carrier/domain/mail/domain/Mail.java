package org.example.carrier.domain.mail.domain;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.carrier.domain.user.domain.User;
import org.example.carrier.global.entity.BaseEntity;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tbl_mail")
public class Mail extends BaseEntity {
    @Column(name = "gmail_id", nullable = false)
    private String gmailId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String from;

    @Column(nullable = false)
    private String to;

    @Column(nullable = false)
    private String subject;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(name = "is_read", nullable = false)
    private Boolean isRead;

    @ElementCollection
    @CollectionTable(name = "tbl_mail_labels")
    @Column(nullable = false)
    private List<String> labels;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
