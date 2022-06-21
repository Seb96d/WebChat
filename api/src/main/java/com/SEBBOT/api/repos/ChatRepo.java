package com.SEBBOT.api.repos;

import com.SEBBOT.api.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepo extends JpaRepository<Message, Long> {

    List<Message> findAllByConversation(Long id);

    List<Message> findAllByConversationAndUser(Long id, String user);
}

