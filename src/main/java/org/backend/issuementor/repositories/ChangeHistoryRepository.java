package org.backend.issuementor.repositories;

import org.backend.issuementor.models.ChangeHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChangeHistoryRepository extends JpaRepository<ChangeHistory, Long> {
}