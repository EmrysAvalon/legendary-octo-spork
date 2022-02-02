INSERT INTO users (username, password, enabled)
VALUES
    ('diana.slater', '$2a$12$YIZC..fODsP1ijczw21h.OeSjI/pg3nthdqNDnCyP.ET/Gg4bgNaO', TRUE),
    ('irene.wright', '$2a$12$rJKgcN5XiD5ey9aPhMH3sO8FG3bZNGDTv.gy.Zl54xL5v9Tqb3SW2', TRUE),
    ('connor.butler', '$2a$12$cpbvFj/c9HAqS3Y1KXBw5.2tyJT8beXc4YJ/GHiEymOy8/EY1dR8W', TRUE);

INSERT INTO authorities (username, authority)
VALUES
    ('diana.slater', 'ROLE_RECEPTIONIST'),
    ('irene.wright', 'ROLE_RECEPTIONIST'),
    ('irene.wright', 'ROLE_ADMINISTRATIVE_WORKER'),
    ('connor.butler', 'ROLE_RECEPTIONIST'),
    ('connor.butler', 'ROLE_ADMINISTRATIVE_WORKER'),
    ('connor.butler', 'ROLE_ADMIN');

INSERT INTO owners (name)
VALUES
    ('Tanja'),
    ('Sandra');

INSERT INTO pets (name, date_of_birth, owner_id)
VALUES
    ('Olivia', '2020-05-29', 1),
    ('Oliver', '2020-07-22', 1),
    ('Pim', '2020-08-19', 2);