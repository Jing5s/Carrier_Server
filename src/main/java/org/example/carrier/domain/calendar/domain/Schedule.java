package org.example.carrier.domain.calendar.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.carrier.domain.category.domain.Category;
import org.example.carrier.domain.user.domain.User;
import org.example.carrier.global.entity.BaseEntity;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tbl_schedule")
public class Schedule extends BaseEntity {
    @Column(nullable = false)
    private String title;

    @Column(name = "all_day", nullable = false)
    private Boolean allDay;

    @Column(name = "is_repeat", nullable = false)
    private Boolean isRepeat;

    @Column(nullable = false)
    private String memo;

    @Column(name = "start_date", nullable = false)
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
                    Boolean isRepeat,
                    String memo,
                    LocalDateTime startDate,
                    LocalDateTime endDate,
                    String location,
                    boolean isEditable,
                    User user,
                    Category category) {
        this.title = title;
        this.allDay = allDay;
        this.isRepeat = isRepeat;
        this.memo = memo;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.isEditable = isEditable;
        this.user = user;
        this.category = category;
    }
}
