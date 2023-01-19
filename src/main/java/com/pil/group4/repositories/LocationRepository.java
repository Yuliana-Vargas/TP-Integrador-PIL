package com.pil.group4.repositories;

import com.pil.group4.models.LocationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<LocationModel, Long> {
}
