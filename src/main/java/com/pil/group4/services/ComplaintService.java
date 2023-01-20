package com.pil.group4.services;

import com.pil.group4.models.ComplaintModel;
import com.pil.group4.repositories.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComplaintService implements IComplaintService {

    private final ComplaintRepository complaintRepository;

    @Autowired
    public ComplaintService(ComplaintRepository complaintRepository) {
        this.complaintRepository = complaintRepository;
    }

    @Override
    public Optional<ComplaintModel> getComplaintById(Long complaintId){
        return complaintRepository.findById(complaintId);
    }

    @Override
    public List<ComplaintModel> getComplaints() {
        return this.complaintRepository.findAll();
    }

    @Override
    public ComplaintModel saveComplaint(ComplaintModel complaintModel) {
        return complaintRepository.save(complaintModel);
    }

    @Override
    public String deleteOfComplaint(Long id){
        try{
            complaintRepository.deleteById(id);
            return "The Complaint with id: " + id + ", was removed";
        } catch (Exception e){
            return "The Complaint with id: " + id + ", wasn't removed";
        }
    }
}
