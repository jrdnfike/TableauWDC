Tableau Web Data Connector

Java source files:

DB_Interface - performs the SQL queries
ResultSetConverter - converts the java.sql.ResultSet objects into JSON form
WDC_Config - javax.ws.rs.Application subclass, instantiates web service classes
WDC_Service - the web service class which receives and responds to HTTP requests
WDC_Test - a local test

Java dependencies:

Jersey
http://repo1.maven.org/maven2/org/glassfish/jersey/bundles/jaxrs-ri/2.25.1/jaxrs-ri-2.25.1.zip

org.json package
https://mvnrepository.com/artifact/org.json/json/20160810

MySQL Java connector
https://cdn.mysql.com//Downloads/Connector-J/mysql-connector-java-5.1.40.zip

NOTES: 

1. Ensure that the mysql-connector-java jar is stored in $CATALINA_HOME
for Tomcat to use.

2. Ensure the javax.ws.rs path tags are properly configured 
in WDC_Config and WDC_Service for the GET request for JSON data.

In my instance, I use the following URL to send the GET request:

$.getJSON("http://localhost:8090/Tableau_WDC/app/service/query.json",...

I run the program, which I named Tableau_WDC, on a local Tomcat server, hence the 
base path of http://localhost:8090/Tableau_WDC/. (your port # may be different)

The next part of the path, "app," means the WDC_Application class should have 
the tag @ApplicationPath("app").

Further, the WDC_Service class needs the path tag @Path("service") in order to
appear under the /app/service path as described.

Lastly, the method runQuery() in WDC_Service has the tag 
@GET
@Path("query.json")
@Produces({APPLICATION_JSON})

meaning this method will run on a GET from the path /app/service/query.json
and produce JSON as output.


Web files:

index.html - A basic page to submit the connection information to Tableau
wdc.js - implements the getSchema and getData functions required by Tableau

Web Dependencies:

Bootstrap UI (ui-bootstrap-tpls-0.12.1.js)
Bootstrap CSS (bootstrap.min.css)

jQuery
https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js

Tableau WDC API
https://connectors.tableau.com/libs/tableauwdc-2.1.latest.js