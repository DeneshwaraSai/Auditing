package com.example.java_jaVers.repository;

import com.example.java_jaVers.entity.Request;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@JaversSpringDataAuditable
public interface RequestRepository extends JpaRepository<Request, String> {
    Request save(Request request);

    List<Request> findAll();

    Request findByEmployeeIdAndId(String employeeId, String id);

    Optional<Request> findById(String id);
}
