DO
$$
    BEGIN
        IF NOT EXISTS (SELECT 1 FROM pg_database WHERE datname = 'user_service') THEN
            CREATE DATABASE user_service;
        END IF;
    END
$$;

DO
$$
    BEGIN
        IF NOT EXISTS (SELECT 1 FROM pg_database WHERE datname = 'provider_service') THEN
            CREATE DATABASE provider_service;
        END IF;
    END
$$;