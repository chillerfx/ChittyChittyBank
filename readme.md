### ChittyChitty bank application

## About
This project was completed during 2017/2018 Erasmus+ spring stay at [EHU UPV / EHU: University of the Basque Country](https://www.ehu.eus/en) for Software engineering class.
Project consists of individual work of me ( Artūras Sotničenko ). Supervising was done by teacher JESUS IBAÑEZ MARTINEZ-CONDE.
Statement of the project can be found in **ChittychittyBank_Statement.pdf** file.

## What has been done?
Inception of the project consisted of the parts
1. Requirements
2. Design
3. Implementation

Requirements part consisted of use case modeling derived from **Project statement**
Of this part can be found in **Model/ChittyChittyDiagrams.mdj** (you can open it with [StarUML](https://staruml.io)

Design part includes modeling of the domain classes derived from use cases done in first part  with the same tool.

Implementation of the project is final application that is built with java. In my case this was REST Server-Client application built on top of [Spring boot](https://spring.io/) and [JavaFX](http://www.oracle.com/technetwork/java/javase/overview/javafx-overview-2158620.html) frameworks with [MySQL](https://www.mysql.com/) datastore

## How to run this project?
# Prerequisites
* Server - Java SDK 1.8, MySql57
* Client - Java SDK 1.8 
1. Clone this repository locally.
2. Import it into IDE of choise.
3. Create new(empty) database schema.
4. Change the database connection strings in  **\src\main\resources\application-server.properties** to the ones you just created in *step 2*. You can also change or add other configuration.
# Now the tricky/hacky part
# Running the application
5. If you just want to run this application (Client and Server) from IDE and localhost
5.1 To run the REST server run the main() from **com.ChittyBankService.ChittyChittyBankApplcation**
5.2 To run the client on or remote host change ALL connection URL string that are in com.ChittyBankService.Client to server address (@TODO move this to config file **\src\main\resources\application-client.properties**)
5.2.1 Run **com.ChittyBankService.Client.Main** to launch the client desktop application

# Building The JAR

In order to build JAR's there's two ways. Both has it's pros and cons
## Building the Hacky way (easy way)

1. Comment the main class in **com.ChittyBankService.Client.Main**
2. Maven Build. This will create Server JAR at target folder ( name could be different ). Move whole folder somewhere safe
3. Uncomment the main class in **com.ChittyBankService.Client.Main** and comment main class in **com.ChittyBankService.ChittyChittyBankApplcation**
4. Maven build. This will create Client JAR at target folder ( name could be different ). Move whole folder somewhere safe

## Building the correct way (hard) (with Maven)

1. Configure **pom.xml** to build two JAR files with different main entries (**com.ChittyBankService.Client.Main** and **com.ChittyBankService.ChittyChittyBankApplcation** ). 
2. Maven Build.

# Some considerations 
* There're bugs in client.
* I forgot.

Pull request are welcome.


