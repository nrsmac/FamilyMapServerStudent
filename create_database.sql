DROP TABLE users;
DROP TABLE events;
DROP TABLE persons;
DROP TABLE authtokens;

CREATE TABLE users(
	personID varchar(255) not null primary key, /*TODO make sure these match in the model members */
	username varchar(255) not null,
	password varchar(255) not null,
	email varchar(255) not null,     
	firstName varchar(255) not null,
	lastName varchar(255) not null,
	gender varchar(255) not null
);

CREATE TABLE events(   
	eventID varchar(255) not null primary key,
	associatedUsername varchar(255) not null,
	personID varchar(255) not null,
	eventType varchar(255) not null,
	latitude double(255),   /*TODO make double or real*/
	longitude double(255),
	country varchar(255),   
	city varchar(255),   
	year INTEGER,
	FOREIGN KEY (associatedUsername) REFERENCES users(username)
);

CREATE TABLE persons ( 	
	personID varchar(255) NOT NULL primary key,
	associatedUsername varchar(255) NOT NULL,
	firstName varchar(255) NOT NULL,
	lastName varchar(255) NOT NULL,
	gender varchar(255) NOT NULL, 	
	fatherID varchar(255),
	motherID varchar(255),
	spouseID varchar(255),
	FOREIGN KEY (associatedUsername) REFERENCES users(username)
);

CREATE TABLE authtokens (
	username varchar(255) not null primary key,
	authtoken varchar(255) not null,
	foreign key (username) references users(username)
);
