package com.pil.group4.controllers;

import com.pil.group4.models.IdeaModel;
import com.pil.group4.services.IIdeaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/idea")
public class IdeaController {
    private final IIdeaService ideaService;

    @Autowired
    public IdeaController(IIdeaService ideaService) {
        this.ideaService = ideaService;
    }

    @GetMapping
    public ArrayList<IdeaModel> getIdeas() {
        return ideaService.getIdeas();
    }

    @PostMapping
    public IdeaModel saveIdea(@RequestBody IdeaModel ideaModel) {
        return ideaService.saveIdea(ideaModel);
    }

    @GetMapping("/{id}")
    public Optional<IdeaModel> getIdeaById(@PathVariable("id") Long idIdea) {
        return ideaService.getIdeaById(idIdea);
    }

    @PutMapping("/{id}")
    public IdeaModel updateIdeaById(@PathVariable("id") Long id, @RequestBody IdeaModel newIdeaModel) {
        return ideaService.updateIdeaById(newIdeaModel, id);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteIdeaById(@PathVariable("id") Long id) {
        return this.ideaService.deleteIdea(id);
    }
}
