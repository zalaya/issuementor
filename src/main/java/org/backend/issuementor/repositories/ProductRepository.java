package org.backend.issuementor.repositories;

import org.backend.issuementor.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}