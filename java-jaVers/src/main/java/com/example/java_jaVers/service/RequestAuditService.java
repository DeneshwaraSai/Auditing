package com.example.java_jaVers.service;

import com.example.java_jaVers.entity.Request;
import org.javers.core.Javers;
import org.springframework.stereotype.Service;
import org.javers.core.diff.Change;
import org.javers.core.metamodel.object.CdoSnapshot;
import org.javers.repository.jql.JqlQuery;
import org.javers.repository.jql.QueryBuilder;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class RequestAuditService {

    private Javers javers;

    public  RequestAuditService(Javers javers) {
        this.javers = javers;
    }

    public List<Change> getRequestChanges(String requestId) {

        JqlQuery query = QueryBuilder
                .byInstanceId(requestId, Request.class)
                .build();

        List<Change> changes = javers.findChanges(query);
        return changes;
    }

    public Map<Long, List<Change>> getRequestChangesByVersion(String requestId) {
        List<CdoSnapshot> snapshots = javers.findSnapshots(
                QueryBuilder.byInstanceId(requestId, Request.class).build()
        );

        Map<Long, List<Change>> changesByVersion = new LinkedHashMap<>();
        for (CdoSnapshot snapshot : snapshots) {
            List<Change> changes = javers.findChanges(
                    QueryBuilder.byInstanceId(requestId, Request.class)
                            .withCommitId(snapshot.getCommitId())
                            .build()
            );
            changesByVersion.put(snapshot.getVersion(), changes);
        }
        return changesByVersion;
    }
}
