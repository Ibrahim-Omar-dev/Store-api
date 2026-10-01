package com.Store_api.store.repositories;


import com.Store_api.store.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}