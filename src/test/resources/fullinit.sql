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


INSERT INTO users (uniqueid, email, code, given, family) VALUES ('AC134343:18CF3BD3:1774F3025AD:-8000', 'tester1@fakemail.us', 'password', 'Tester1', 'Coding');
INSERT INTO users (uniqueid, email, code, given, family) VALUES ('AC134343:18CF3BD3:1774F3025AD:-7FFF', 'tester2@fakemail.us', 'password', 'Tester2', 'Coding');
INSERT INTO users (uniqueid, email, code, given, family) VALUES ('AC134343:18CF3BD3:1774F3025AD:-7FFE', 'tester3@fakemail.us', 'password', 'Tester3', 'Coding');
INSERT INTO users (uniqueid, email, code, given, family) VALUES ('AC134343:18CF3BD3:1774F3025AD:-7FFD', 'tester4@fakemail.us', 'password', 'Tester4', 'Coding');
INSERT INTO users (uniqueid, email, code, given, family) VALUES ('AC134343:18CF3BD3:1774F3025AD:-7FFC', 'tester5@fakemail.us', 'password', 'Tester5', 'Coding');

INSERT INTO services (key, display, cost) VALUES ('AVW', 'Windows Antivirus', 5.00);
INSERT INTO services (key, display, cost) VALUES ('AVM', 'Mac Antivirus', 7.00);
INSERT INTO services (key, display, cost) VALUES ('CDB', 'Cloudberry', 3.00);
INSERT INTO services (key, display, cost) VALUES ('PSA', 'PSA', 2.00);
INSERT INTO services (key, display, cost) VALUES ('TVW', 'TeamViewer', 1.00);
INSERT INTO services (key, display, cost) VALUES ('DVC', 'Devices', 4.00);
