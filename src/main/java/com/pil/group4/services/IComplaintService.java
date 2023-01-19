package com.pil.group4.services;

import com.pil.group4.models.ComplaintModel;

import java.util.List;

public interface IComplaintService {

    ComplaintModel getComplaintById(Long complaintId);

    List<ComplaintModel> getComplaints();

    ComplaintModel saveComplaint(ComplaintModel complaintModel);
}
