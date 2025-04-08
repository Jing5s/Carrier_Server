package org.example.carrier.domain.meet.domain.repository;

import org.example.carrier.domain.meet.domain.Meet;
import org.example.carrier.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MeetRepository extends JpaRepository<Meet, Long>, CustomMeetRepository {
    Optional<Meet> findByIdAndUser(Long id, User user);
}
