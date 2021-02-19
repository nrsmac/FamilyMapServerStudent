DROP TABLE users;
DROP TABLE events;
DROP TABLE persons;
DROP TABLE auth_tokens;
CREATE TABLE users(
	user_id varchar(255) not null primary key, /*TODO make sure these match in the model members */
	username varchar(255) not null,
	password varchar(255) not null,
	email varchar(255) not null,     
	first_name varchar(255) not null,     
	last_name varchar(255) not null,     
	gender varchar(255) not null
);

CREATE TABLE events(   
	event_id varchar(255) not null primary key,   
	username varchar(255) not null,
	person_id varchar(255) not null,
	event_type varchar(255) not null,   
	latitude double(255),   /*TODO make double or real*/
	longitude double(255),
	country varchar(255),   
	city varchar(255),   
	year varchar(255), 
	FOREIGN KEY (username) REFERENCES users(username)	
);

CREATE TABLE persons ( 	
	person_id varchar(255) NOT NULL primary key, 	
	username varchar(255) NOT NULL,
	first_name varchar(255) NOT NULL, 
	last_name varchar(255) NOT NULL, 	
	gender varchar(255) NOT NULL, 	
	father_id varchar(255), 	
	mother_id varchar(255), 	
	spouse_id varchar(255), 	
	FOREIGN KEY (username) REFERENCES users(username)	
);

CREATE TABLE auth_tokens (
	auth_id varchar(255) NOT NULL primary key,
	username varchar(255) not null,
	auth_token varchar(255) not null,
	foreign key (username) references users(username)
);
