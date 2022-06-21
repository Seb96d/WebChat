package com.seb.SebBot.repos;

import com.seb.SebBot.entities.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConvRepo extends JpaRepository<Conversation, Long> {
}

