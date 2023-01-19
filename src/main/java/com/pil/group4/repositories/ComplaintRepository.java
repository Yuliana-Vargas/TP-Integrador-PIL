package com.pil.group4.repositories;

import com.pil.group4.models.ComplaintModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplaintRepository extends JpaRepository<ComplaintModel, Long> {
}
