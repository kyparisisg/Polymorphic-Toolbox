#Polymorphic Toolbox



### Reference Documentation

A Web Application that allows its users to manipulate data without any restrictions.

The application comes with the following default credentials for admin access.

Username: admin@polymorphic-toolbox.com

Password: admin1Q2w3e4R



### Guide-Uses

The admin will be able to configure devices that are accessible in the internal network
where the application is hosted, or devices that are facing the internet with a static 
public IPv4 address.
The users will be invited by the admin through the User Interface with an email
that contains information for their registration.

The ability to transfer files from a server/device to another server/device is provided
within the User Interface.
Furhtermore, assuming that permissions are granted from admin, a user can use the Polymorphic
Toolbox to back up files on a private diretory of an AWS S3 Bucket.
The user is provided with a functionality to manipulate its personal private encrypted directory. 
Restoring a file from the cloud to a server/device is another functionality provided from the
interface.
Additionally the user can delete files that are located in his/her private directory on S3 Bucket.

Finally the user can view all sucessful and unsuccessfull transactions that were submitted.


### Software Prerequisites 

* Java JDK (preferred version jdk-11.0.7.jdk)

* Spring Boot 

* Gradle Plugin 

* nginx

* mysql DB (version 8.0.11 or latest listening on default port)


### Instalation 

* Clone Git Repo

* ./gradlew build       (to build the project)

* gradle bootRun        (To Run the Project)



