CREATE TABLE IF NOT EXISTS LANGUAGE(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR
);

CREATE TABLE IF NOT EXISTS VOICE(
    id BIGSERIAL PRIMARY KEY,
    code VARCHAR,
    language_id BIGINT REFERENCES LANGUAGE(id),
    gender VARCHAR NOT NULL
);