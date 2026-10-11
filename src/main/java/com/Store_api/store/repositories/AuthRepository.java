package com.Store_api.store.repositories;

import com.Store_api.store.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<User,Long> {
}
