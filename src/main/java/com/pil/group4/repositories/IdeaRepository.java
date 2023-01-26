package com.pil.group4.repositories;


import com.pil.group4.models.IdeaModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IdeaRepository extends JpaRepository<IdeaModel, Long> {
}
