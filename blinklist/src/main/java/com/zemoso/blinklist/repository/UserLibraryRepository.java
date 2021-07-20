package com.zemoso.blinklist.repository;

import com.zemoso.blinklist.model.UserLibrary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLibraryRepository extends JpaRepository<UserLibrary,Integer> {
}
