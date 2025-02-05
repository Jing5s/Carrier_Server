package org.example.carrier.domain.calendar.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.carrier.domain.user.domain.User;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tbl_schedule")
public class Schedule {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(name = "all_day", nullable = false)
    private Boolean allDay;

    @Column(nullable = false)
    private Boolean repeat;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    private String location;

    @Column(name = "is_editable", nullable = false)
    private boolean isEditable = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Builder
    public Schedule(String title,
                    Boolean allDay,
                    Boolean repeat,
                    LocalDateTime startDate,
                    LocalDateTime endDate,
                    String location,
                    boolean isEditable,
                    User user,
                    Category category) {
        this.title = title;
        this.allDay = allDay;
        this.repeat = repeat;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.isEditable = isEditable;
        this.user = user;
        this.category = category;
    }
}
