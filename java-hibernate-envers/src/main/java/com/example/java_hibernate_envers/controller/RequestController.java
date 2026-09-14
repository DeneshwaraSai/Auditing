package com.example.java_hibernate_envers.controller;

import com.example.java_hibernate_envers.entity.Request;
import com.example.java_hibernate_envers.dto.RequestAuditDto;
import com.example.java_hibernate_envers.service.RequestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/requests/v1")
public class RequestController {

    private RequestService requestService;

    public RequestController(RequestService requestService) {
        this.requestService = requestService;
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

    @GetMapping("/audit/{id}")
    public List<RequestAuditDto> getAuditById(@PathVariable String id) {
        return this.requestService.getAuditById(id);
    }
}
