-- create synchronous account
CREATE USER 'replication_user'@'%' IDENTIFIED BY 'replication_pwd';

-- authorized authority
GRANT REPLICATION SLAVE, REPLICATION CLIENT ON *.* TO 'replication_user'@'%';

-- view master node status command "SHOW MASTER STATUS"