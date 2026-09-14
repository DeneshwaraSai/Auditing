package com.example.java_jaVers.service;

import com.example.java_jaVers.entity.Request;
import com.example.java_jaVers.entity.RequestStatus;
import com.example.java_jaVers.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RequestService {

    @Autowired
    RequestRepository requestRepository;

    public Request save(Request request) {
        return this.requestRepository.save(request);
    }

    public List<Request> findAll() {
        return this.requestRepository.findAll();
    }

    public Request findByEmployeeIdAndId(String employeeId, String id) {
        return this.requestRepository.findByEmployeeIdAndId(employeeId, id);
    }

    public Request findById(String id) {
        return this.requestRepository.findById(id).orElse(null);
    }

    public Request updateRequest(String id, Request request) {
        Request prevRequest = this.requestRepository.findById(id).orElse(null);
        if (prevRequest != null) {
            prevRequest.setEmployeeId(request.getEmployeeId());
            prevRequest.setStartDate(request.getStartDate());
            prevRequest.setEndDate(request.getEndDate());
            prevRequest.setStatus(request.getStatus());
            prevRequest.setTotalHours(request.getTotalHours());
            prevRequest.setTotalDays(request.getTotalDays());
            return this.requestRepository.save(prevRequest);
        }
        return null;
    }

    public Request updateRequestStatus(String id, RequestStatus status) {
        Request request = this.requestRepository.findById(id).orElse(null);
        if (request != null) {
            request.setStatus(status);
            return this.requestRepository.save(request);
        }
        return null;
    }
}
