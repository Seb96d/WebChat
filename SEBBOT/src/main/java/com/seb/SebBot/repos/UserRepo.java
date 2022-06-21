package com.seb.SebBot.repos;

import com.seb.SebBot.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, String> {
    User findAllByEmail(String username);
}
