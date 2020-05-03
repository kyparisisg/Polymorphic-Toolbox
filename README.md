# Polymorphic Toolbox



### Reference Documentation

A Web Application that allows its users to manipulate data without any restrictions.

The application comes with the following default credentials for admin access.

Username: admin@polymorphic-toolbox.com

Password: admin


### Guide-Overview

The admin will be able to configure devices that are accessible in the internal network
where the application is hosted, or devices that are facing the internet with a static 
public IPv4 address.
The users will be invited by the admin through the User Interface with an email
that contains information for their registration.

The ability to transfer files from a server/device to another server/device is provided
within the User Interface.
Furthermore, assuming that permissions are granted from admin, a user can use the Polymorphic
Toolbox to back up files on a private directory of an AWS S3 Bucket.
The user is provided with a functionality to manipulate its personal private encrypted directory. 
Restoring a file from the cloud to a server/device is another functionality provided from the
interface.
Additionally, the user can delete files that are located in his/her private directory on S3 Bucket.

Finally, the user can view all successful and unsuccessful transactions that were submitted.


### Guide-Server Transfer

In order to successfully complete a server transfer, be sure your servers are set up successfully.
Please see the admin dashboard guide if that is not the case, or if unexpected errors occur.
To start a server transfer, select the "Server Transfer" button from the user dashboard.
A form will open, listing the IDs of all visible servers. Please note that admins have 
permission to access any entered server but users will need to granted individual permission
for each server they wish to have access to. Enter the ID of the server you wish to transfer a 
file from and continue to the next form. A form will open, listing all the files in the default 
directory of the server. If no files are listed, ensure that they are in the default directory
and not in a separate folder. Type the file name that you wish to transfer and continue to the next
form. Another form listing accessible servers will appear, enter the ID of the desired destination 
server and continue. If the transfer was successful, the page will say that the transfer succeeded
and list the details of the transfer, including a status of 1. If the transfer was not successful, 
the page will say that the transfer failed and show the details of the failed transfer including a
status of 0. Be sure that the server is set up properly to allow for secure copy over the default or
specified port. In either case, an entry will be added to the history tab on the user dashboard, 
showing the details of the transfer.


### Guide-Cloud Backup, Restore and Delete

In order to successfully complete a cloud backup, be sure your servers are set up successfully.
Please see the admin dashboard guide if that is not the case, or if unexpected errors occur.
To start a cloud backup, select the "Back Up" button from the user dashboard.
A form will open, listing the IDs of all visible servers. Please note that admins have 
permission to access any entered server but users will need to granted individual permission
for each server they wish to have access to. Enter the ID of the server you wish to backup a 
file from and continue to the next form. A form will open, listing all the files in the default 
directory of the server. If no files are listed, ensure that they are in the default directory
and not in a separate folder. Type the file name that you wish to backup and continue to the next
form. The file will then be uploaded to the your account's directory in the s3 bucket (such a
directory will be created if it does not already exist). An entry will be added to the history
tab on the user dashboard, showing the details of the transfer.

The process is reversed for the restore process. To start a cloud restore, select the "Restore" button
from the user dashboard. A form will open, listing all the files in your account's directory of the s3 bucket.
Type the file name that you wish to restore and continue to the next form. A form will open, listing the
IDs of all visible servers. Enter the ID of the server you wish to restore a file to. The file will be 
added to the default directory of that server. An entry will be added to the history tab on the user
dashboard, showing the details of the transfer.

To delete a file from the cloud s3 bucket, select the "Delete" button from the user dashboard. A form
will open, listing all the files in your account's directory of the s3 bucket. Type the file name
that you wish to delete and continue. The file will be deleted from the s3 bucket.


### Guide-Admin Dashboard


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

