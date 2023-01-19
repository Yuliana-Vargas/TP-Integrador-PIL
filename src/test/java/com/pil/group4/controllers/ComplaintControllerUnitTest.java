package com.pil.group4.controllers;

import com.pil.group4.models.ComplaintModel;
import com.pil.group4.models.TypeOfComplaint;
import com.pil.group4.services.ComplaintService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class ComplaintControllerUnitTest {
    @Mock
    private ComplaintService complaintService;

    @InjectMocks
    private ComplaintController complaintController;

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
}
