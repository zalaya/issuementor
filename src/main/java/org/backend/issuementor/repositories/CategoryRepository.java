package org.backend.issuementor.repositories;

import org.backend.issuementor.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}