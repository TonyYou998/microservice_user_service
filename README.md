#Link to other services in my capstone
##
1.user service:https://github.com/TonyYou998/microservice_user_service
2.booking service:https://github.com/TonyYou998/microservice_booking_service
3.hotel service:https://github.com/TonyYou998/microservice_hotel_Service
4.config server:https://github.com/TonyYou998/microservice_config_server
5.discovery server:https://github.com/TonyYou998/microservice_discovery_server
6.api gateway:https://github.com/TonyYou998/microservice_gateway
7.base project:https://github.com/TonyYou998/microservice_base_project
8.frontend:https://github.com/TonyYou998/microservice_airbnb_fe
To deploy my capstone to k8s find in https://github.com/TonyYou998/microservice_gateway/tree/master/kubernete
# microservice_user_service
## Start the application
1. open your terminal and run docker-compose up -d command to start docker container of application
2. install all depnedencies by mvn clean install command or right click on pom.xml->Maven->Reload project
3. start the application

## Stop git add .env
to prevent git add .env use command git rm --cached .env
