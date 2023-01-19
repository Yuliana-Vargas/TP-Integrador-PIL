package com.pil.group4.services;

import com.pil.group4.models.ComplaintModel;
import com.pil.group4.repositories.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplaintService implements IComplaintService {

    private final ComplaintRepository complaintRepository;

    @Autowired
    public ComplaintService(ComplaintRepository complaintRepository) {
        this.complaintRepository = complaintRepository;
    }

    @Override
    public ComplaintModel getComplaintById(Long complaintId) {
        return this.complaintRepository.findById(complaintId).orElse(null);
    }

    @Override
    public List<ComplaintModel> getComplaints() {
        return this.complaintRepository.findAll();
    }

    @Override
    public ComplaintModel saveComplaint(ComplaintModel complaintModel) {
        return complaintRepository.save(complaintModel);
    }
}
