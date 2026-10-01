package com.Store_api.store.repositories;


import com.Store_api.store.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends JpaRepository<Category, Byte> {
}