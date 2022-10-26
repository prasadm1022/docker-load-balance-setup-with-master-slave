-- synchronization settings
CHANGE MASTER TO
    MASTER_HOST='database-master',
    MASTER_PORT=8010,
    MASTER_USER='replication_user',
    MASTER_PASSWORD='replication_pwd';

-- start synchronization
START SLAVE;

-- view slave node status command "SHOW SLAVE STATUS"