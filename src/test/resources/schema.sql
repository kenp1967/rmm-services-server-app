DROP TABLE IF EXISTS users CASCADE;

CREATE TABLE users (
	uniqueid varchar(128) NOT NULL PRIMARY KEY,
	email varchar(128) NOT NULL,
	code varchar(128),
	given varchar(128) NOT NULL,
	family varchar(128) NOT NULL
);

CREATE INDEX email_idx ON users (email);

DROP TABLE IF EXISTS services CASCADE;

CREATE TABLE services (
	key varchar(12) NOT NULL PRIMARY KEY,
	display varchar(128) NOT NULL,
	cost real NOT NULL
);

DROP TABLE IF EXISTS user_devices CASCADE;

CREATE TABLE user_devices (
	uniqueid varchar(128) NOT NULL PRIMARY KEY,
	name varchar(128) NOT NULL,
	type varchar(128) NOT NULL,
	ownerid varchar(128) NOT NULL
);

CREATE INDEX ownerid_idx ON user_devices (ownerid);
ALTER TABLE user_devices ADD CONSTRAINT FK_User_Device FOREIGN KEY (ownerid) REFERENCES user_devices(ownerid);

DROP TABLE IF EXISTS user_services CASCADE;

CREATE TABLE user_services (
	userid varchar(128) NOT NULL,
	serviceid varchar(128) NOT NULL
);

CREATE INDEX userid_idx ON user_services (userid);
