package org.example.carrier.domain.calendar.domain.repository;

import org.example.carrier.domain.calendar.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
