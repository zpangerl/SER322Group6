SER322 Group 6 Deliverable 4

Steps for creating the database:
Please make sure you have MySQL Workbench installed and working.
1. Open the included GameExplorer_Group6_create.sql in MySQL
2. Execute the create statements
3. Open the included GameExplorer_Group6_insert.sql in MySQL
4. Execute the insert statements

Information on the environment for running the project:
Please make sure you have java 18, as this will be run using java through the command line.
Please also ensure that you have the mysql connector (The whole folder is included for ease of access, you do not need to download it separately)
As mentioned previously, make sure you have MySQL Workbench. The version that was used is 8.0.36

Steps for executing application:
1. Navigate to the src/ folder provided in the zip file in your command prompt.
2. To compile, enter the following: javac -cp <classpath for the driver jar file> ser322/*.java
For example, in Windows, it will be "javac -cp .;path/to/jarfile ser322/*.java
3. To run the application, enter the following: java -cp <classpath for the driver jar file> ser322.Main <url> <username> <password> <Driver>
For example, in Windows, it will be "java -cp .;path/to/jarfile ser322.Main jdbc:mysql://localhost:3306/gameexplorer root root com.mysql.cj.jdbc.Driver
The welcome screen and the main menu will then appear!

Video URL: <TODO>

Other information: <OPTIONAL TODO>

Individual contributions:

Zach:
1. Main menu logic
2. Executor.java
3. ListExecutor.java
4. InsertExecutor.java
5. Video for insert/update queries

Shivansh:
<TODO>

Daryl:
<TODO>