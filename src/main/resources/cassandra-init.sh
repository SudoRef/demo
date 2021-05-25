#!/bin/bash
CQL="DROP keyspace users;
CREATE KEYSPACE users WITH replication = {'class': 'SimpleStrategy', 'replication_factor': '1'} AND durable_writes = true;
CREATE TABLE IF NOT EXISTS  users.users (name text, birthdate timestamp, email text PRIMARY KEY,password text);
INSERT INTO users.users (email, birthdate, name, password) VALUES ('email', '2000-01-01 03:00:00.0', 'John Smith', 'password');"
until echo $CQL | cqlsh;
do
    echo "cqlsh: Cassandra is unavailable to initialize - will retry later"
    sleep 2
done &
exec /docker-entrypoint.sh "$@"