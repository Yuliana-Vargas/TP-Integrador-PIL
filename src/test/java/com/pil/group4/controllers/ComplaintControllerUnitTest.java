package com.pil.group4.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pil.group4.models.ComplaintModel;
import com.pil.group4.models.ComplaintModel;
import com.pil.group4.models.TypeOfComplaint;
import com.pil.group4.services.ComplaintService;
import com.pil.group4.services.ComplaintService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

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
    void setUp(){
        this.complaintList = new ArrayList<>();
        this.complaintList.add(new ComplaintModel());
        this.complaintList.add(new ComplaintModel());
        this.complaintList.add(new ComplaintModel());
    }



    /*
        @Test
        public void getComplaints(){
            ArrayList<ComplaintModel> complaints = new ArrayList<>();
            assertNotNull(complaints);
            assertTrue(complaints.isEmpty());

            ComplaintModel complaint1 = new ComplaintModel();
            ComplaintModel complaint2 = new ComplaintModel();
            ComplaintModel complaint3 = new ComplaintModel();

            complaint1.setTypeOfComplaint(TypeOfComplaint.ANOTHER_REASON);
            complaint2.setTypeOfComplaint(TypeOfComplaint.FOR_VANDALISM);
            complaint3.setTypeOfComplaint(TypeOfComplaint.FOR_MISUSE);
            complaint1.setDescription("No se usa seguido");
            complaint2.setDescription("La zona fue destruida");
            complaint3.setDescription("Tiran electronicos");

            complaints.add(complaint1);
            complaints.add(complaint2);
            complaints.add(complaint3);

            assertFalse(complaints.isEmpty());
            assertEquals(3, complaints.size());
            when(complaintService.getComplaints()).thenReturn(complaints);
            assertEquals(complaints, complaintController.getComplaints());
        }
    */
    @Test
    public void getComplaintsTest() throws Exception{
        when(complaintService.getComplaints()).thenReturn((ArrayList<ComplaintModel>)complaintList);

        mockMvc.perform(get("/complaint"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(3)))
                .andExpect(jsonPath("$.typeOfComplaint",is(TypeOfComplaint.ANOTHER_REASON)))
                .andExpect(jsonPath("$.description",is("not used")));
    }
    /*
    @Test
    public void getComplaintById(){
        ComplaintModel complaint = new ComplaintModel();
        complaint.setId(1L);
        complaint.setTypeOfComplaint(TypeOfComplaint.FOR_MISUSE);
        complaint.setDescription("Tiran residuos no reciclables");

        when(complaintService.getComplaintById(complaint.getId())).thenReturn(Optional.of(complaint));
        Optional<ComplaintModel> complaintById = complaintService.getComplaintById(1L);

        assertNotNull(complaintById);
        assertEquals(complaint.getId(), complaintById.orElseThrow().getId());
    }
    */
    @Test
    public void getComplaintByIdTest() throws Exception{
        final Long complaintId =1L;
        final ComplaintModel complaintModel = new ComplaintModel();

        when(ComplaintService.getComplaintById(complaintId)).thenReturn(Optional.of(complaintModel));
        mockMvc.perform(get("/complaint/{id}", complaintId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.description", is(complaintModel.getDescription())));
    }

    @Test
    void saveComplainTest() throws Exception {
        when(complaintService.saveComplaint(any(ComplaintModel.class))).then((invocation) -> invocation.getArgument(0));

        ComplaintModel complaintModel = new ComplaintModel();

        mockMvc.perform(post("/complaint")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(complaintModel)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description", is(complaintModel.getDescription())));
    }
    /*
        @Test
        public void deleteComplaintTest() {
            ComplaintModel complaintModel = new ComplaintModel();
            complaintModel.setId(2L);

            complaintService.deleteOfComplaint(complaintModel.getId());

            assertFalse(complaintService.getComplaintById(2L).isPresent());

        }*/
    @Test
    public void deleteComplaintTest() throws Exception{
        Long complaintId = 1L;
        ComplaintModel complaintModel = new ComplaintModel();
        when(complaintService.getComplaintById(complaintId)).thenReturn(Optional.of(complaintModel));

        mockMvc.perform(delete("/complaint/{id}",complaintModel.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
    @Test
    void updateComplaintByIdTest() throws Exception {
        Long complaintId = 1L;
        ComplaintModel complaintModel = new ComplaintModel();
        when(complaintService.getComplaintById(complaintId)).thenReturn(Optional.of(complaintModel));
        when(complaintService.updateComplaintById(any(ComplaintModel.class).getId(), any())).then((invocation) -> invocation.getArgument(0));

        mockMvc.perform(put("/complaint/{id}", complaintModel.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(complaintModel)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description", is(complaintModel.getDescription())));
    }

/*
    @Test
    public void updateComplaintIdTest() {
        ComplaintModel complaintModel = new ComplaintModel();
        complaintModel.setId(1L);
        complaintModel.setTypeOfComplaint(TypeOfComplaint.ANOTHER_REASON);
        complaintModel.setDescription("they are recycling badly");

        ComplaintModel complaintModel1 = new ComplaintModel();
        complaintModel1.setId(1L);
        complaintModel1.setTypeOfComplaint(TypeOfComplaint.FOR_MISUSE);
        complaintModel1.setDescription("they are recycling badly");

        when(complaintController.updateComplaintById(complaintModel1, complaintModel.getId())).thenReturn(complaintModel1);
        assertEquals(complaintModel1, complaintController.updateComplaintById(complaintModel1, complaintModel.getId()));
    }*/
}
