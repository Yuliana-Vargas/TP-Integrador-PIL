package com.pil.group4.services;

import com.pil.group4.models.ComplaintModel;

import java.util.List;
import java.util.Optional;

public interface IComplaintService {

    Optional<ComplaintModel> getComplaintById(Long complaintId);

    List<ComplaintModel> getComplaints();

    ComplaintModel saveComplaint(ComplaintModel complaintModel);
    String deleteOfComplaint(Long idComplaint);
}
