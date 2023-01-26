package com.pil.group4.services;


import com.pil.group4.models.IdeaModel;
import com.pil.group4.repositories.IdeaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class IdeaService implements IIdeaService {

    private final IdeaRepository ideaRepository;

    @Autowired
    public IdeaService(IdeaRepository ideaRepository) {
        this.ideaRepository = ideaRepository;
    }

    @Override
    public ArrayList<IdeaModel> getIdeas() {
        return (ArrayList<IdeaModel>) ideaRepository.findAll();
    }

    @Override
    public IdeaModel saveIdea(IdeaModel ideaModel) {
        return ideaRepository.save(ideaModel);
    }

    @Override
    public Optional<IdeaModel> getIdeaById(Long idIdea) {
        return ideaRepository.findById(idIdea);
    }

    public IdeaModel updateIdeaById(IdeaModel newIdeaModel, Long id) {
        return ideaRepository.findById(id)
                .map(idea -> {
                    idea.setIdeaName(newIdeaModel.getIdeaName());
                    idea.setDescription(newIdeaModel.getDescription());
                    return ideaRepository.save(idea);
                })
                .orElseGet(() -> ideaRepository.save(newIdeaModel));
    }

    @Override
    public String deleteIdea(Long id) {
        try {
            ideaRepository.deleteById(id);
            return "The Idea with the id: " + id + ", was removed";
        } catch (Exception e) {
            return "The Idea with the id: " + id + ", wasn't removed";
        }
    }
}
