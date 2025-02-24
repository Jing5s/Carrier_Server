package org.example.carrier.domain.category.domain.repository;

import org.example.carrier.domain.category.domain.Category;
import org.example.carrier.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByIdAndUser(Long id, User user);
    List<Category> findAllByUser(User user);
}
