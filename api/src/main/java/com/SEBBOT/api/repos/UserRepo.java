package com.SEBBOT.api.repos;

import com.SEBBOT.api.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, String> {
    User findAllByEmail(String username);
}
