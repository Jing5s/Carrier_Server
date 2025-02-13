package org.example.carrier.domain.todo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.carrier.domain.todo.domain.type.Priority;
import org.example.carrier.domain.user.domain.User;
import org.example.carrier.global.entity.BaseEntity;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tbl_todo")
public class Todo extends BaseEntity {
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private LocalDate date;

    @Column(name = "is_repeat", nullable = false)
    private Boolean isRepeat;

    @Column(nullable = false)
    private Boolean isDone = false;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Priority priority;

    private String memo;

    private String location;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Todo(String title, LocalDate date, Boolean isRepeat,
                Priority priority, String memo, String location, User user) {
        this.title = title;
        this.date = date;
        this.isRepeat = isRepeat;
        this.priority = priority;
        this.memo = memo;
        this.location = location;
        this.user = user;
    }
}
