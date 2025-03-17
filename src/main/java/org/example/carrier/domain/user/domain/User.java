package org.example.carrier.domain.user.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.val;
import org.example.carrier.domain.calendar.domain.Schedule;
import org.example.carrier.domain.category.domain.Category;
import org.example.carrier.domain.diary.domain.Diary;
import org.example.carrier.domain.mail.domain.Mail;
import org.example.carrier.domain.todo.domain.Todo;
import org.example.carrier.global.entity.BaseEntity;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_user")
@Entity
public class User extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String email;

    @Column(length = 15, nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String picture;

    @Column(name = "refresh_token", nullable = false)
    private String googleRefreshToken;

    @Column(name = "notification_time")
    private LocalTime notificationTime;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Todo> todos = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Schedule> schedules = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Mail> mails = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Diary> diaries = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Category> categories = new ArrayList<>();

    @Builder
    public User(String email, String nickname, String picture, String googleRefreshToken) {
        this.email = email;
        this.nickname = nickname;
        this.picture = picture;
        this.googleRefreshToken = googleRefreshToken;
    }

    public User update(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public User updatePicture(String picture) {
        this.picture = picture;
        return this;
    }

    public void updateGoogleRefreshToken(String googleRefreshToken) {
        this.googleRefreshToken = googleRefreshToken;
    }

    public User updateNotificationTime(LocalTime notificationTime) {
        this.notificationTime = notificationTime;
        return this;
    }
}
