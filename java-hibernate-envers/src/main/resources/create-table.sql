Create Database WFM_ENVERS;

use WFM_ENVERS;

CREATE TABLE WFM_ENVERS.requests (
    id VARCHAR(36) NOT NULL,
    employee_id BIGINT NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    status VARCHAR(30) NOT NULL,
    total_hours DOUBLE NULL,
    total_days DOUBLE NULL,
    PRIMARY KEY (id)
);

CREATE TABLE WFM_ENVERS.revinfo (
    rev INT NOT NULL AUTO_INCREMENT,
    rev_timestamp BIGINT NOT NULL,
    PRIMARY KEY (rev)
);

CREATE TABLE WFM_ENVERS.requests_aud (
    id VARCHAR(36) NOT NULL,
    rev INT NOT NULL,
    revtype TINYINT NOT NULL,
    employee_id BIGINT NULL,
    start_date DATE NULL,
    end_date DATE NULL,
    status VARCHAR(30) NULL,
    total_hours DOUBLE NULL,
    total_days DOUBLE NULL,
    PRIMARY KEY (id, rev),
    CONSTRAINT fk_requests_aud_rev FOREIGN KEY (rev) REFERENCES revinfo(rev)
);