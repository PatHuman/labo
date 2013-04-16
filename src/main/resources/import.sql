-- You can use this file to load seed data into the database using SQL statements
insert into Member (id, name, email, team) values (0, 'John Smith', 'john.smith@mailinator.com', 'HR-IT');
insert into Department (id, name, version) values (0, 'Human Resources', 1);
insert into team (id, name, department, version) values (0, 'HR-IT', 'Human Resources', 1);
insert into project (id, description, name, owner, version) values (0, 'provide a sample code in JAVA web technologie for AVG', 'Code sample', 'Pat', 1);