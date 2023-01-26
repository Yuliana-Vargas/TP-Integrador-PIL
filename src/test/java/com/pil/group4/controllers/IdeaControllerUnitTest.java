package com.pil.group4.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pil.group4.models.IdeaModel;
import com.pil.group4.services.IdeaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = IdeaController.class)
public class IdeaControllerUnitTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private IdeaService ideaService;

    private List<IdeaModel> ideaList;

    @BeforeEach
    void setUp() {
        this.ideaList = new ArrayList<>();
        this.ideaList.add(new IdeaModel(1L, "Idea 1", "glass"));
        this.ideaList.add(new IdeaModel(2L, "Idea 2", "paper"));
        this.ideaList.add(new IdeaModel(3L, "Idea 3", "botle"));

    }

    @Test
    void getIdeaTest() throws Exception {
        when(ideaService.getIdeas()).thenReturn((ArrayList<IdeaModel>) ideaList);

        mockMvc.perform(get("/idea"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[2].ideaName", is("Idea 3")))
                .andExpect(jsonPath("$[0].description", is("glass")));
    }

    @Test
    void getIdeaByIdTest() throws Exception {
        final Long ideaId = 1L;
        final IdeaModel ideaModel = new IdeaModel(1L, "Idea 1", "glass");

        when(ideaService.getIdeaById(ideaId)).thenReturn(Optional.of(ideaModel));

        mockMvc.perform(get("/idea/{id}", ideaId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.ideaName", is(ideaModel.getIdeaName())))
                .andExpect(jsonPath("$.description", is(ideaModel.getDescription())));
    }

    @Test
    void saveIdeaTest() throws Exception {
        when(ideaService.saveIdea(any(IdeaModel.class))).then((invocation) -> invocation.getArgument(0));

        IdeaModel ideaModel = new IdeaModel(null, "Idea 1", "glass");

        mockMvc.perform(post("/idea")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ideaModel)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ideaName", is(ideaModel.getIdeaName())))
                .andExpect(jsonPath("$.description", is(ideaModel.getDescription())));
    }

    @Test
    void updateIdeaByIdTest() throws Exception {
        Long ideaId = 1L;
        IdeaModel ideaModel = new IdeaModel(ideaId, "Idea 1", "glass");
        when(ideaService.getIdeaById(ideaId)).thenReturn(Optional.of(ideaModel));
        when(ideaService.updateIdeaById(any(IdeaModel.class), any(Long.class))).then((invocation) -> invocation.getArgument(0));

        mockMvc.perform(put("/idea/{id}", ideaModel.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ideaModel)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ideaName", is(ideaModel.getIdeaName())))
                .andExpect(jsonPath("$.description", is(ideaModel.getDescription())));
    }

    @Test
    void deleteIdeaByIdTest() throws Exception {
        Long ideaId = 1L;
        IdeaModel ideaModel = new IdeaModel(ideaId, "Idea 1", "glass");
        when(ideaService.getIdeaById(ideaModel.getId())).thenReturn(Optional.of(ideaModel));

        mockMvc.perform(delete("/idea/{id}", ideaModel.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}

