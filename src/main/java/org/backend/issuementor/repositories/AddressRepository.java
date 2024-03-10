package org.backend.issuementor.repositories;

import org.backend.issuementor.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

}