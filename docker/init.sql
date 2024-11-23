ALTER USER guest WITH SUPERUSER;
SELECT 1
FROM pg_database
WHERE datname = 'user_service';
-- If no result is returned, execute:
CREATE DATABASE user_service;

-- Check if 'provider_service' exists, if not, create it
SELECT 1
FROM pg_database
WHERE datname = 'provider_service';
-- If no result is returned, execute:
CREATE DATABASE provider_service;


-- Grant all privileges on 'user_service' to 'guest'
GRANT ALL PRIVILEGES ON DATABASE user_service TO guest;

-- Grant all privileges on 'provider_service' to 'guest'
GRANT ALL PRIVILEGES ON DATABASE provider_service TO guest;
