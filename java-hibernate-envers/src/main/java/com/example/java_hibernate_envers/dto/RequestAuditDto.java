package com.example.java_hibernate_envers.dto;

import com.example.java_hibernate_envers.entity.Request;

import java.util.Date;

public class RequestAuditDto {

    private Request request;
    private long revision;
    private Date revisionDate;

    public RequestAuditDto() {}

    public RequestAuditDto(Request request, long revision, Date revisionDate) {
        this.request = request;
        this.revision = revision;
        this.revisionDate = revisionDate;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public long getRevision() {
        return revision;
    }

    public void setRevision(long revision) {
        this.revision = revision;
    }

    public Date getRevisionDate() {
        return revisionDate;
    }

    public void setRevisionDate(Date revisionDate) {
        this.revisionDate = revisionDate;
    }
}
