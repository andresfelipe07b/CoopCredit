CREATE TABLE affiliates (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    document VARCHAR(50) UNIQUE NOT NULL,
    salary DOUBLE PRECISION NOT NULL,
    address VARCHAR(255),
    start_date DATE NOT NULL,
    active BOOLEAN DEFAULT TRUE
);

CREATE TABLE credit_applications (
    id BIGSERIAL PRIMARY KEY,
    affiliate_id BIGINT NOT NULL,
    amount DOUBLE PRECISION NOT NULL,
    term INTEGER NOT NULL,
    base_rate DECIMAL(5,2),
    status VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_affiliate FOREIGN KEY (affiliate_id) REFERENCES affiliates(id)
);

CREATE TABLE risk_evaluations (
    id BIGSERIAL PRIMARY KEY,
    application_id BIGINT UNIQUE NOT NULL,
    score INTEGER NOT NULL,
    reason TEXT,
    risk_level VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_application FOREIGN KEY (application_id) REFERENCES credit_applications(id)
);

CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    roles VARCHAR(255)
);
