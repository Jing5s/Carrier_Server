package org.example.carrier.domain.calendar.domain.repository;

import org.example.carrier.domain.calendar.domain.CalendarCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalendarCategoryRepository extends JpaRepository<CalendarCategory, Long> {
}
