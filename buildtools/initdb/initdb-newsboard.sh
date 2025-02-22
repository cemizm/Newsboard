#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
    CREATE USER $NEWSBOARD_USER WITH PASSWORD '$NEWSBOARD_PW';
    CREATE DATABASE $NEWSBOARD_DB OWNER $NEWSBOARD_USER;
EOSQL