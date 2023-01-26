package com.pil.group4.services;


import com.pil.group4.models.IdeaModel;

import java.util.ArrayList;
import java.util.Optional;

public interface IIdeaService {
    ArrayList<IdeaModel> getIdeas();

    IdeaModel saveIdea(IdeaModel ideaModel);

    Optional<IdeaModel> getIdeaById(Long idIdea);

    IdeaModel updateIdeaById(IdeaModel newIdeaModel, Long id);

    String deleteIdea(Long id);
}
