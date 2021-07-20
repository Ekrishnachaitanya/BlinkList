package com.zemoso.blinklist.repository;

import com.zemoso.blinklist.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
