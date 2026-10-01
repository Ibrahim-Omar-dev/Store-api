package com.Store_api.store.repositories;

import com.Store_api.store.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}