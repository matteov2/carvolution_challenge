# carvolution_challenge

Local MySQL Database Setup
- Install MySQL "https://dev.mysql.com/downloads/mysql/"
- Start Local MySql Server
- Setup new MySQL Connection on Port 3306 (or use already existing one)
- Create Database "CREATE DATABASE db_carvolution_challenge"
- Add Connection Username and Password on Line 2 and 3 in "application.properties"
- (Sample data is automatically loaded into the db at startup)

REST Endpoints:
- GET /carvolution/users -> List all Users from the Database
- GET /carvolution/user/user_id -> List a specific User
- POST /user -> Save a User
- PUT /adress/user_id -> Edit/Save Adress of a User
