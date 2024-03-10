package org.backend.issuementor.repositories;

import org.backend.issuementor.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}