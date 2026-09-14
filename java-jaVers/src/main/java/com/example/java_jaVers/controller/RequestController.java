package com.example.java_jaVers.controller;

import com.example.java_jaVers.entity.Request;
import com.example.java_jaVers.entity.RequestStatus;
import com.example.java_jaVers.service.RequestAuditService;
import com.example.java_jaVers.service.RequestService;
import org.javers.core.diff.Change;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/requests/v1")
public class RequestController {

    private RequestService requestService;
    private RequestAuditService requestAuditService;

    public RequestController(RequestService requestService, RequestAuditService requestAuditService) {
        this.requestService = requestService;
        this.requestAuditService = requestAuditService;
    }

    @PostMapping("/save")
    public Request save(@RequestBody Request request) {
        return this.requestService.save(request);
    }

    @GetMapping("/all")
    public List<Request> findAll() {
        return this.requestService.findAll();
    }

    @GetMapping("/employeeId/{employeeId}/id/{id}")
    public Request findByEmployeeIdAndId(@PathVariable String employeeId, @PathVariable String id) {
        return this.requestService.findByEmployeeIdAndId(employeeId, id);
    }

    @GetMapping("/id/{id}")
    public Request findById(@PathVariable String id) {
        return this.requestService.findById(id);
    }

    @PutMapping("/update/{id}")
    public Request updateRequest(@PathVariable String id, @RequestBody Request request) {
        return this.requestService.updateRequest(id, request);
    }

    @PutMapping("/updateStatus")
    public Request updateRequestStatus(@RequestBody Request request) {
        return this.requestService.updateRequestStatus(request.getId(), request.getStatus());
    }

    @GetMapping("/audit/{requestId}")
    public Map<Long, List<Change>> getRequestChanges(@PathVariable String requestId) {
        return this.requestAuditService.getRequestChangesByVersion(requestId);
    }
}
