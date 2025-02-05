package org.example.carrier.domain.calendar.domain.repository;

import org.example.carrier.domain.calendar.domain.Category;
import org.example.carrier.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByUser(User user);
}
