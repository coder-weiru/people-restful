people-restful
===============================
A sample REST service project using Spring frameworks

###1. Technologies used
* Maven 3.3.3
* Spring 4.1.6.RELEASE
* HSQLDB 2.3.2
* MyBatis 3.2.4

###2. Development IDE
* Spring Tool Suite (STS)

###3. To run this project locally
* Using Spring Tool Suite
 1. git clone https://github.com/coder-weiru/people-restful
 2. import people-restful into STS via **existing maven projects into workspace** option.
 3. add people-restful web application to a VMware vFabric tc Server instance
 4. start the server
 
* Using other application server
 1. git clone https://github.com/coder-weiru/people-restful
 2. build war file using maven command
 3. deploy the resulted war file to other web application server, such as Tomcat
 4. start the server 
 
 The REST API endpoints can be accessed via ```http://localhost:8080/people-restful/```

###4. The following REST API endpoints are available 

```/family```   [POST]

To add a new family

```/family/update```   [POST]

To update an existing family

```/family/find```   [GET]

To retrieve all families

```/family/find/{name}```   [GET]

To find families by name

```/family/{fid}```    [GET]

To get a family by a family id
	
```/delFamily/{fid}```    [DELETE]

To delete a family by a family id
	
```/person```   [POST]

To add a new person

```/person/update```    [POST]

To update an existing person

```/person/find```   [GET]

To retrieve all people

```/person/find/{name}```   [GET]

To find people by name

```/person/{pid}```    [GET]

To get a person by a person id	

```/delPerson/{pid}```    [DELETE]

To delete a person by a person id
	
```/add/person/{pid}/family/{fid}```    [GET]

To associate a person to a family	

```/del/person/{pid}/family/{fid}```    [GET]

To remove a person from a family

```/familyPeople/{fid}```          [GET]

To get all people associated with a specific family

###5. Testing
Besides the JUnit tests within the project, integration tests can be performed through people-restful-integration-test project.
Please refer to [people-restful-integration-test] (https://github.com/coder-weiru/people-restful-integration-test) project.
