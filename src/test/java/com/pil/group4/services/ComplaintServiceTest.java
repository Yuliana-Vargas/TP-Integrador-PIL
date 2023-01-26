package com.pil.group4.services;
import com.pil.group4.models.ComplaintModel;

import com.pil.group4.models.TypeOfComplaint;
import com.pil.group4.repositories.ComplaintRepository;

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
class ComplaintServiceTest {
    @Mock
    private ComplaintRepository complaintRepository;

    @InjectMocks
    private ComplaintService complaintService;

    @Test
    void getComplaintTest() {
        List<ComplaintModel> complaints = new ArrayList<>();
        assertTrue(complaints.isEmpty());
        complaints.add(new ComplaintModel(1L, TypeOfComplaint.FOR_VANDALISM,"bad recycling"));
        complaints.add(new ComplaintModel(2L, TypeOfComplaint.FOR_MISUSE,"bad recycling"));
        complaints.add(new ComplaintModel(3L, TypeOfComplaint.ANOTHER_REASON,"bad used"));

        assertFalse(complaints.isEmpty());
        when(complaintRepository.findAll()).thenReturn(complaints);
        assertEquals(complaintService.getComplaints(),complaints);
    }

    @Test
    void saveComplaintTest() {
        ComplaintModel complaintModel = new ComplaintModel(1L, TypeOfComplaint.FOR_VANDALISM,"bad recycling");
        when(complaintRepository.save(complaintModel)).then(invocation -> invocation.getArgument(0));

        ComplaintModel savedComplaint = complaintService.saveComplaint(complaintModel);
        assertThat(savedComplaint).isNotNull();
        verify(complaintRepository).save(any(ComplaintModel.class));
    }

    @Test
    void getComplaintByIdTest() {
        Long complaintId = 1L;
        ComplaintModel complaintModel = new ComplaintModel(1L, TypeOfComplaint.FOR_VANDALISM,"bad recycling");

        when(complaintRepository.findById(complaintId)).thenReturn(Optional.of(complaintModel));

        Optional<ComplaintModel> complaintExpected = complaintService.getComplaintById(complaintId);
        assertThat(complaintExpected).isNotNull();
        assertTrue(complaintExpected.isPresent());
        assertEquals("bad recycling",complaintExpected.get().getDescription());
        verify(complaintRepository).findById(any(Long.class));
    }

    @Test
    void updateComplaintByIdTest() {
        ComplaintModel complaintModel = new ComplaintModel(1L, TypeOfComplaint.FOR_VANDALISM,"bad recycling");
        when(complaintRepository.save(complaintModel)).thenReturn(complaintModel);
        ComplaintModel complaintExpected = complaintService.updateComplaintById(complaintModel,complaintModel.getId());
        assertThat(complaintExpected).isNotNull();
        verify(complaintRepository).save(any(ComplaintModel.class));
    }

    @Test
    void deleteComplaintTest() {
        Long complaintId = 1L;

        complaintService.deleteOfComplaint(complaintId);
        complaintService.deleteOfComplaint(complaintId);
        verify(complaintRepository, times(2)).deleteById(complaintId);
    }
}

