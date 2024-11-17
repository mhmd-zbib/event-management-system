CREATE
DATABASE user_service;
CREATE
DATABASE provider_service;

\c
provider_service;

CREATE TABLE providers
(
    id          SERIAL PRIMARY KEY,
    userId      BIGINT       NOT NULL,
    bio         VARCHAR(500) NOT NULL,
    serviceType VARCHAR(255) NOT NULL,
    rating      DOUBLE PRECISION CHECK (rating >= 0 AND rating <= 5),
    available   BOOLEAN      NOT NULL,
    hourlyRate  DOUBLE PRECISION CHECK (hourlyRate >= 0),
    serviceArea VARCHAR(100) NOT NULL,
    CONSTRAINT unique_userId UNIQUE (userId)
);