package com.example.java_hibernate_envers.service;

import com.example.java_hibernate_envers.dto.RequestAuditDto;
import com.example.java_hibernate_envers.entity.Request;
import com.example.java_hibernate_envers.entity.RequestStatus;
import com.example.java_hibernate_envers.repository.RequestRepository;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class RequestService {

    RequestRepository requestRepository;

    @PersistenceContext
    private EntityManager entityManager;

    RequestService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

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

    public List<RequestAuditDto> getAuditById(String id) {
        AuditReader reader = AuditReaderFactory.get(entityManager);
        List<Number> revisions = reader.getRevisions(Request.class, id);
        List<RequestAuditDto> history = new ArrayList<>();
        for (Number rev : revisions) {
            Request req = reader.find(Request.class, id, rev);
            Date revDate = reader.getRevisionDate(rev);
            history.add(new RequestAuditDto(req, rev.longValue(), revDate));
        }
        if (history.isEmpty()) {
            return null;
        }
        return history.stream().sorted((RequestAuditDto rt1, RequestAuditDto rt2) -> rt2.getRevisionDate().compareTo(rt1.getRevisionDate())).toList();
    }
}
