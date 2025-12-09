INSERT INTO affiliates (name, email, document, salary, address, start_date, active)
VALUES ('Test Pending User', 'pending@test.com', '999999999', 3000000, 'Test Address', '2023-01-01', true)
ON CONFLICT (document) DO NOTHING;

INSERT INTO credit_applications (affiliate_id, amount, term, status, base_rate, created_at)
VALUES (
    (SELECT id FROM affiliates WHERE document = '999999999'),
    2000000,
    12,
    'PENDING',
    NULL,
    NOW()
);

-- Users (Password: password123)
-- Hash generated for 'password123': $2a$10$8.UnVuG9HHgffUDAlk8qfOpNaNXZZr9toq2k.9.uy1SvJae0.JoC
INSERT INTO users (username, password, roles)
VALUES 
    ('admin', '$2a$10$8.UnVuG9HHgffUDAlk8qfOpNaNXZZr9toq2k.9.uy1SvJae0.JoC', 'ROLE_ADMIN'),
    ('analista', '$2a$10$8.UnVuG9HHgffUDAlk8qfOpNaNXZZr9toq2k.9.uy1SvJae0.JoC', 'ROLE_ANALISTA')
ON CONFLICT (username) DO NOTHING;
