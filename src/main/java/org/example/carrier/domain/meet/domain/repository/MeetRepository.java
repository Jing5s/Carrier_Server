package org.example.carrier.domain.meet.domain.repository;

import org.example.carrier.domain.meet.domain.Meet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetRepository extends JpaRepository<Meet, Long> {
}
