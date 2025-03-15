package org.example.carrier.domain.calendar.domain.repository;

import org.example.carrier.domain.calendar.domain.Schedule;
import org.example.carrier.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long>, CustomScheduleRepository {
    Optional<Schedule> findByIdAndUser(Long id, User user);
}
