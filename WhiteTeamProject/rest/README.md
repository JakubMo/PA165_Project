Documentation of rest API for service check.


First run this command in rest module directory in order to compile project and run tomcat:

mvn clean install -f ../pom.xml; mvn tomcat7:run

#GET all service checks
curl -i -X GET http://localhost:8080/pa165/rest/servicecheck/


#GET service check with given id
curl -i -X GET http://localhost:8080/pa165/rest/servicecheck/detail/{id}

e.g. for service check with id 1
curl -i -X GET http://localhost:8080/pa165/rest/servicecheck/detail/1


#CREATE new service check example
curl -X POST -i -H "Content-Type: application/json" --data '{"status":"DONE_OK","serviceCheckDate":"2015-08-15 10:15","vehicle":{"id":1,"vin":"FNHCE75100U019029","brand":"Honda","model":"Accord","type":"liftback","yearOfProduction":2005,"engineType":"petrol","mileage":200399,"serviceCheckInterval":12,"maxMileage":300000},"serviceEmployee":"Jan Suchy","report":"Report written by Honza"}' http://localhost:8080/pa165/rest/servicecheck/create


#UPDATE service check report
curl -i -X PUT http://localhost:8080/pa165/rest/servicecheck/updatereport/1/{report}

e.g.
curl -i -X PUT http://localhost:8080/pa165/rest/servicecheck/updatereport/1/My%20new%20report


#UPDATE service check employee
curl -i -X PUT http://localhost:8080/pa165/rest/servicecheck/updateemployee/1/{employee}

e.g.
curl -i -X PUT http://localhost:8080/pa165/rest/servicecheck/updateemployee/1/John%20Smith


#UPDATE service check date
curl -i -X PUT http://localhost:8080/pa165/rest/servicecheck/updatedate/1/{date}

e.g.
curl -i -X PUT http://localhost:8080/pa165/rest/servicecheck/updatedate/1/2015-08-15


#UPDATE service check status
curl -i -X PUT http://localhost:8080/pa165/rest/servicecheck/updatestatus/1/{status}

e.g.
curl -i -X PUT http://localhost:8080/pa165/rest/servicecheck/updatestatus/1/DONE_NOT_OK


#DELETE service check
curl -i -X DELETE  http://localhost:8080/pa165/rest/servicecheck/delete/{id}

e.g.
curl -i -X DELETE  http://localhost:8080/pa165/rest/servicecheck/delete/1
