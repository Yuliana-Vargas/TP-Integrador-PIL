package com.pil.group4.services;

import com.pil.group4.models.IdeaModel;
import com.pil.group4.repositories.IdeaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class IdeaServiceTest {
    @Mock
    private IdeaRepository ideaRepository;

    @InjectMocks
    private IdeaService ideaService;

    @Test
    void getIdeasTest() {
        List<IdeaModel> ideas = new ArrayList<>();
        assertTrue(ideas.isEmpty());
        ideas.add(new IdeaModel(1L, "Idea 1", "glass"));
        ideas.add(new IdeaModel(2L, "Idea 2", "paper"));
        ideas.add(new IdeaModel(3L, "Idea 3", "botle"));

        assertFalse(ideas.isEmpty());
        when(ideaRepository.findAll()).thenReturn(ideas);
        assertEquals(ideaService.getIdeas(), ideas);
    }

    @Test
    void saveIdeaTest() {
        IdeaModel ideaModel = new IdeaModel(1L, "Idea 1", "glass");
        when(ideaRepository.save(ideaModel)).then(invocation -> invocation.getArgument(0));

        IdeaModel savedIdea = ideaService.saveIdea(ideaModel);
        assertThat(savedIdea).isNotNull();
        verify(ideaRepository).save(any(IdeaModel.class));
    }

    @Test
    void getIdeaByIdTest() {
        Long ideaId = 1L;
        IdeaModel ideaModel = new IdeaModel(1L, "Idea 1", "glass");

        when(ideaRepository.findById(ideaId)).thenReturn(Optional.of(ideaModel));

        Optional<IdeaModel> ideaExpected = ideaService.getIdeaById(ideaId);
        assertThat(ideaExpected).isNotNull();
        assertTrue(ideaExpected.isPresent());
        assertEquals("Idea 1", ideaExpected.get().getIdeaName());
        verify(ideaRepository).findById(any(Long.class));
    }

    @Test
    void updateIdeaByIdTest() {
        IdeaModel ideaModel = new IdeaModel(1L, "Idea 1", "glass");
        when(ideaRepository.save(ideaModel)).thenReturn(ideaModel);
        IdeaModel ideaExpected = ideaService.updateIdeaById(ideaModel, ideaModel.getId());
        assertThat(ideaExpected).isNotNull();
        verify(ideaRepository).save(any(IdeaModel.class));
    }

    @Test
    void deleteIdeaTest() {
        Long ideaId = 1L;

        ideaService.deleteIdea(ideaId);
        ideaService.deleteIdea(ideaId);
        verify(ideaRepository, times(2)).deleteById(ideaId);
    }
}
