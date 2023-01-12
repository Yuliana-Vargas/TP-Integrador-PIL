package com.pil.group4.repositories;

import com.pil.group4.models.SupervisorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupervisorRepository extends JpaRepository<SupervisorModel, Long> {
}
