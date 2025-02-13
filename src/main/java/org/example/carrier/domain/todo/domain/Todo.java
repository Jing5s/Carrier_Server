package org.example.carrier.domain.todo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.carrier.domain.todo.domain.type.Priority;
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
}
