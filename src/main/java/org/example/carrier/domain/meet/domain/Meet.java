package org.example.carrier.domain.meet.domain;

import jakarta.persistence.Column;
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

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "meets")
public class Meet extends BaseEntity {
    private String title;

    @Column(columnDefinition = "TEXT")
    private String text;

    @Column(name = "summary_text", columnDefinition = "TEXT")
    private String summaryText;

    private String time;

    @Column(name = "audio_link")
    private String audioLink;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Meet(String title, String text, String summaryText, String time, String audioLink, User user) {
        this.title = title;
        this.text = text;
        this.summaryText = summaryText;
        this.time = time;
        this.audioLink = audioLink;
        this.user = user;
    }
}
