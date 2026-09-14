Create Database WFM;

use WFM;

CREATE TABLE requests (
    id VARCHAR(36) NOT NULL,
    employee_id BIGINT NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    status VARCHAR(20) NOT NULL,
    total_hours DOUBLE,
    total_days DOUBLE,
    PRIMARY KEY (id)
);


select * from WFM.requests;
