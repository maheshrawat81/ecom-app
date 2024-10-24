mvn clean install -DskipTests 
mvn spring-boot:run 
gradle clean build -x test 
gradlew bootRun

## api/users

curl -v -X POST http://localhost:8989/api/users/register -d
\'{\"username\":\"delete\",\"password\":\"delete\",\"email\":\"teamamex\"}\'
-H \"Content-Type: application/json\" 

curl -v -X POST
http://localhost:8989/api/users/login -d
\'{\"username\":\"mahesh\",\"password\":\"mahesh\"}\' -H \"Content-Type:
application/json\" 

curl -v -X POST http://localhost:8989/api/users/login
-d \'{\"username\":\"rawat\",\"password\":\"rawat\"}\' -H
\"Content-Type: application/json\" 

curl -v
http://localhost:8989/api/users/abc -H \'Authorization:Bearer
eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJVU0VSIl0sInN1YiI6Im1haGVzaCIsImlhdCI6MTcyOTc1ODEzOSwiZXhwIjoxNzI5NzU4NDM5fQ.RxLJRL11xgicErhq_iFKf42PP8zo3OsAbO_omQaxidk\'

curl -X PUT http://root:root@localhost:5984/ecom/\_design/User -d \'{
\"\_id\": \"\_design/User\", \"views\": { \"by_username\": { \"map\":
\"function (doc) { if (doc.username) { emit(doc.username, doc); } }\" }
} }\' -H \"Content-Type: application/json\"

curl -X GET
\'http://root:root@localhost:5984/ecom/\_design/User/\_view/by_username?key=\"rawat\"\'

---

## /api/products

curl -v -X PUT
http://localhost:8084/api/products/c63ebd3cf234de3c8ad434b8b501a469 -d
\'{\"name\":\"iphone\",\"price\":1500.00,\"quantity\":20,\"description\":\"8/256gb\",\"seller_name\":\"\"}\'
-H \'Authorization:Bearer
eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyYXdhdCIsImlhdCI6MTcyOTY2MDM3MCwiZXhwIjoxNzI5NjYwNjcwfQ.Cvx0jR_KTXXr9ejGvlWryBsk8WGKOTMxzdIdVC6rzGw\'
-H \"Content-Type: application/json\" 

curl -v -X POST
http://localhost:8084/api/products/add -d \'{\"name\":\"Product
Name\",\"price\":12.99,\"quantity\":130,\"description\":\"description\",\"seller_name\":\"test\"}\'
-H \"Content-Type: application/json\" -H \'Authorization:Bearer
eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyYXdhdCIsImlhdCI6MTcyOTY2MTg0NSwiZXhwIjoxNzI5NjYyMTQ1fQ.-5gmg16ddgTx9IiDkiPmn3BVtAr-Nd8Y2FsS6C3AaQU\'

---

## api/orders

 curl -v -X POST
\'http://localhost:8082/api/orders/add?username=rawat\' -d
\'{\"8c1bc065-b283-4349-8d7e-9fe06d2c7ee\":2}\' -H \"Content-Type:
application/json\"

curl -v -X GET \'http://localhost:8082/api/orders?username=rawat\'

curl -X PUT
http://root:root@localhost:5984/order_management/\_design/Order -d \'{
\"\_id\": \"\_design/Order\", \"views\": { \"by_username\": { \"map\":
\"function (doc) { if (doc.username) { emit(doc.username, doc); } }\" }
} }\' -H \"Content-Type: application/json\"

curl -X GET
\'http://root:root@localhost:5984/order_management/\_design/Order/\_view/by_username?key=\"rawat\"\'
