package com.fareye.training.repository;

import com.fareye.training.model.Todo;
import com.fareye.training.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

    List<Todo> findByTitleAndUserEmail(String title, String userEmail);

    List<Todo> findByUserEmail(String userEmail);
}
