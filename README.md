### 1. Explanation about docker-compose files.

#### **database-master :**

* This is responsible for get & run mysql server container.
* This container will be the master node of our mysql cluster. It will be responsible for handling slave nodes and also data writing part.
* This has been configured to create a new database & users for the backend service (db:"upwork" | user:"upwork" | password:"upwork").
* This master node will be run on port 8010 within the VM and that port has been configured in "docker-compose-database.yml".

#### **database-slave :**

* This is responsible for get & run mysql server container.
* This container will be a slave node of our mysql cluster. These slave nodes will always sync with the master node and responsible for handling data reading part.
* These slave nodes will be run on port 8020 within the VM and that port has been configured in "docker-compose-database.yml".
* We can scale these slave nodes as per our requirements to handle the load on database server.

#### **backend-server :**

* This is responsible for get & run tomcat server container.
* This will get the already built spring-boot war file from "~/target" directory within the project directory and then copy it to the "webapps" directory of a tomcat server.
* This tomcat server will be run on port 8080 by default within the VM and that port can be configured in "application.yml" if needed.
* We can scale this backend service as per our requirements to handle the load on the server.

#### **load-balance-server :**

* This is responsible for get & run nginx server container.
* This server will act as the load balancer for our deployed backend services.
* Configurations for nginx server is in the "nginx.conf" (check "~/docker/nginx" directory within the project directory).
* This nginx server will be run on port 80 within the docker container, and it has been exposed to outside using port 8000 (check "docker-compose-backend.yml").
* When we send a request to port 8000, then it will be automatically redirected to an available backend service.

### 2. Basic commands

* First build all Spring-Boot projects & generate war files.

* Then go to the project directory where the docker-compose files located. These files include the all configurations that need to build & run all docker containers.

* Run below command to enable docker swarm mode. This will start your current host as a manager node in the swarm. You can add worker nodes manually to this swarm later by following the documentation. https://gdevillele.github.io/engine/swarm/

> > _docker swarm init_

* Run below command to deploy & run your docker containers.

> > _docker-compose -f [YAML_FILE] up_

* Run the containers with scaling.

> > _docker-compose -f [YAML_FILE] up --scale [SERVICE_NAME]=[NO_OF_CONTAINERS]_

* Stop containers.

> > _docker-compose stop_

### 3. Example Scenario (Ex :- authentication-service)

![](https://github.com/prasadm1022/docker-load-balancing-complete/blob/main/architecture.png)

* first run the "**_docker-compose -f docker-compose-database.yml up --scale database-slave=2_**" command to start the database server.

* this will start 3 containers, one from "database-master" & two from "database-slave" in this case. Let's say they are db-master, db-slave-1, db-slave-2.

* then run the "**_docker-compose -f docker-compose-backend.yml up --scale backend-server=2_**" command to start the backend server.

* this will start 3 containers, one from "load-balance-server" & two from "backend-server" in this case. Let's say they are load-balancer, be-server-1, be-server-2.

* Now there are 6 containers are running within our main container.
    * db-master
    * db-slave-1
    * db-slave-2
    * load-balancer
    * be-server-1
    * be-server-2

* run below curl command 2 times parallel to test the API.

> > curl --location --request POST 'http://localhost:8000/authentication-service/v1/auth/login' --header 'Content-Type:application/json' --data-raw '{"username": "prasadm","password": "123456789"}'

* first request will be sent to the "be-server-1" and data will be read from "db-slave-1".

* second request will be sent to the "be-server-2" and data will be read from "db-slave-2".
