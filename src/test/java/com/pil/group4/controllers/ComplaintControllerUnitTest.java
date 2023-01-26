package com.pil.group4.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pil.group4.models.ComplaintModel;
import com.pil.group4.services.ComplaintService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.pil.group4.models.TypeOfComplaint.*;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebMvcTest(controllers = ComplaintController.class)
public class ComplaintControllerUnitTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private ComplaintService complaintService;
    private List<ComplaintModel> complaintList;

    @BeforeEach
    void setUp() {
        this.complaintList = new ArrayList<>();
        this.complaintList.add(new ComplaintModel(1L, ANOTHER_REASON, "not used"));
        this.complaintList.add(new ComplaintModel(2L, FOR_MISUSE, "bad recylcing"));
        this.complaintList.add(new ComplaintModel(3L, FOR_VANDALISM, "painted wall"));
    }

    @Test
    public void getComplaintsTest() throws Exception {
        when(complaintService.getComplaints()).thenReturn(complaintList);

        mockMvc.perform(get("/complaint"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].typeOfComplaint", is(ANOTHER_REASON.name())))
                .andExpect(jsonPath("$[0].description", is("not used")));
    }

    @Test
    public void getComplaintByIdTest() throws Exception {
        final Long complaintId = 1L;
        final ComplaintModel complaintModel = new ComplaintModel(1L,ANOTHER_REASON, "not used");

        when(complaintService.getComplaintById(complaintId)).thenReturn(Optional.of(complaintModel));
        mockMvc.perform(get("/complaint/{id}", complaintId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.typeOfComplaint",is(complaintModel.getTypeOfComplaint().name())))
                .andExpect(jsonPath("$.description", is(complaintModel.getDescription())));
    }

    @Test
    void saveComplainTest() throws Exception {
        when(complaintService.saveComplaint(any(ComplaintModel.class))).then((invocation) -> invocation.getArgument(0));

        ComplaintModel complaintModel = new ComplaintModel(1L, ANOTHER_REASON, "not used");

        mockMvc.perform(post("/complaint")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(complaintModel)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.typeOfComplaint",is(complaintModel.getTypeOfComplaint().name())))
                .andExpect(jsonPath("$.description", is(complaintModel.getDescription())));
    }

    @Test
    public void deleteComplaintTest() throws Exception {
        Long complaintId = 1L;
        ComplaintModel complaintModel = new ComplaintModel(1L, ANOTHER_REASON, "not used");
        when(complaintService.getComplaintById(complaintId)).thenReturn(Optional.of(complaintModel));

        mockMvc.perform(delete("/complaint/{id}", complaintModel.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
    @Test
    void updateComplaintByIdTest() throws Exception {
        Long complaintId = 1L;
        ComplaintModel complaintModel = new ComplaintModel(complaintId,ANOTHER_REASON, "not used");
        when(complaintService.getComplaintById(complaintId)).thenReturn(Optional.of(complaintModel));
        when(complaintService.updateComplaintById(any(ComplaintModel.class),any(Long.class))).then((invocation) -> invocation.getArgument(0));

        mockMvc.perform(put("/complaint/{id}", complaintModel.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(complaintModel)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.typeOfComplaint",is(complaintModel.getTypeOfComplaint().name())))
                .andExpect(jsonPath("$.description", is(complaintModel.getDescription())));
    }
}
