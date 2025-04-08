package org.example.carrier.domain.meet.domain.repository;

import org.example.carrier.domain.meet.domain.Meet;
import org.example.carrier.domain.user.domain.User;

import java.util.List;

public interface CustomMeetRepository {
    List<Meet> findAllMeetsDesc(User user);
}
