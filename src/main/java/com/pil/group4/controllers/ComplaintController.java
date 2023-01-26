package com.pil.group4.controllers;

import com.pil.group4.models.ComplaintModel;
import com.pil.group4.services.IComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/complaint")
public class ComplaintController {

    private final IComplaintService complaintService;

    @Autowired
    public ComplaintController(IComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    @GetMapping
    public List<ComplaintModel> getComplaints() {
        return this.complaintService.getComplaints();
    }

    @GetMapping("/{id}")
    public Optional<ComplaintModel> getComplaintById(@PathVariable("id") Long complaintId){
        return complaintService.getComplaintById(complaintId);
    }

    @PostMapping
    public ComplaintModel saveComplaint(@RequestBody ComplaintModel complaintModel) {
        return complaintService.saveComplaint(complaintModel);
    }
    @DeleteMapping(path = "/{id}")
    public String deleteComplaintById(@PathVariable("id") Long id) {
        return this.complaintService.deleteOfComplaint(id);
    }

    @PutMapping("/{id}")
    public ComplaintModel updateComplaintById(@PathVariable("id") Long id, @RequestBody ComplaintModel complaintModel) {
        return complaintService.updateComplaintById(complaintModel,id);
    }

}
