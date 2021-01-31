# rmm-services-server-app

As the Spring Framework was a requirement, I chose to go with spring boot given that it 
appears to have all of the life on spring efforts and it is becoming the standard for 
microservices.

Other technologies were chose because of requirements but also because of a potentially
seamless fit.  The need for persistence made this a 3 tier web project with the restful 
endpoints as front end negotiating with the service and all dependent upon the repository.

For persistence I've gone with JPA backed by JTA so that the more complex operations 
could be properly treated as a unit of work.

The integrations tests can be run from within the maven project as so:

mvn test

The application can be run from within the maven project:

mvn spring-boot:run

If the application is packaged (mvn package) into an executable archive then

java -jar <<filename>>.jar

Executable war files can be run in the same fashion.  As a war file it can also be deployed to
any compliant servlet container.
