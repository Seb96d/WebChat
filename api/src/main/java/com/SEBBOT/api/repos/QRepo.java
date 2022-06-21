package com.SEBBOT.api.repos;

import com.SEBBOT.api.entities.QnA;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QRepo extends JpaRepository<QnA, Long> {
    List<QnA> findAllByQuestion(String message);
}
