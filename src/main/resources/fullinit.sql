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
	device_type varchar(128) NOT NULL,
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

INSERT INTO services (key, display, cost) VALUES ('AVW', 'Windows Antivirus', 5.00);
INSERT INTO services (key, display, cost) VALUES ('AVM', 'Mac Antivirus', 7.00);
INSERT INTO services (key, display, cost) VALUES ('CDB', 'Cloudberry', 3.00);
INSERT INTO services (key, display, cost) VALUES ('PSA', 'PSA', 2.00);
INSERT INTO services (key, display, cost) VALUES ('TVW', 'TeamViewer', 1.00);
INSERT INTO services (key, display, cost) VALUES ('DVC', 'Devices', 4.00);

INSERT INTO users (uniqueid, email, code, given, family) VALUES ('AC134343:18CF3BD3:1774F3025AD:-8000', 'tester1@fakemail.us', 'password', 'Tester1', 'Coding');
INSERT INTO user_services VALUES ('AC134343:18CF3BD3:1774F3025AD:-8000', 'CDB');
INSERT INTO user_services VALUES ('AC134343:18CF3BD3:1774F3025AD:-8000', 'PSA');
INSERT INTO user_services VALUES ('AC134343:18CF3BD3:1774F3025AD:-8000', 'TVW');
INSERT INTO user_devices VALUES ('AC134343:-667824A6:1775D2CF34E:-8000', 'Tester1 Device1', 'Windows Workstation', 'AC134343:18CF3BD3:1774F3025AD:-8000');
INSERT INTO user_devices VALUES ('AC134343:-1765C80:1775D2E2185:-8000', 'Tester1 Device1', 'Windows Server', 'AC134343:18CF3BD3:1774F3025AD:-8000');
INSERT INTO user_devices VALUES ('AC134343:-1765C80:1775D2E2185:-7FFF', 'Tester1 Device1', 'Main', 'AC134343:18CF3BD3:1774F3025AD:-8000');
INSERT INTO user_devices VALUES ('AC134343:-667824A6:1775D2CF34E:-7FFE', 'Tester1 Device1', 'Windows Workstation', 'AC134343:18CF3BD3:1774F3025AD:-8000');
INSERT INTO user_devices VALUES ('AC134343:-1765C80:1775D2E2185:-7FFD', 'Tester1 Device1', 'Windows Server', 'AC134343:18CF3BD3:1774F3025AD:-8000');
INSERT INTO user_devices VALUES ('AC134343:-1765C80:1775D2E2185:-7FFC', 'Tester1 Device1', 'Main', 'AC134343:18CF3BD3:1774F3025AD:-8000');

INSERT INTO users (uniqueid, email, code, given, family) VALUES ('AC134343:18CF3BD3:1774F3025AD:-7FFF', 'tester2@fakemail.us', 'password', 'Tester2', 'Coding');
INSERT INTO user_services VALUES ('AC134343:18CF3BD3:1774F3025AD:-7FFF', 'AVW');
INSERT INTO user_services VALUES ('AC134343:18CF3BD3:1774F3025AD:-7FFF', 'PSA');
INSERT INTO user_services VALUES ('AC134343:18CF3BD3:1774F3025AD:-7FFF', 'TVW');
INSERT INTO user_devices VALUES ('AC134343:-667824A6:177562CF34E:-8000', 'Tester2 Device2', 'Windows Workstation', 'AC134343:18CF3BD3:1774F3025AD:-7FFF');
INSERT INTO user_devices VALUES ('AC134343:-1765C80:1775D2E2985:-8000', 'Tester2 Device2', 'Windows Server', 'AC134343:18CF3BD3:1774F3025AD:-7FFF');
INSERT INTO user_devices VALUES ('AC134343:-1745C80:1775D2E2185:-7FFF', 'Tester2 Device2', 'Main', 'AC134343:18CF3BD3:1774F3025AD:-7FFF');
INSERT INTO user_devices VALUES ('AC134343:-667834A6:1775D2CF34E:-7FFE', 'Tester2 Device2', 'Windows Workstation', 'AC134343:18CF3BD3:1774F3025AD:-7FFF');
INSERT INTO user_devices VALUES ('AC134343:-1265C80:1775D2E2185:-7FFD', 'Tester2 Device2', 'Windows Server', 'AC134343:18CF3BD3:1774F3025AD:-7FFF');
INSERT INTO user_devices VALUES ('AC134343:-1765C80:1775D2D2185:-7FFC', 'Tester2 Device2', 'Main', 'AC134343:18CF3BD3:1774F3025AD:-7FFF');

INSERT INTO users (uniqueid, email, code, given, family) VALUES ('AC134343:18CF3BD3:1774F3025AD:-7FFE', 'tester3@fakemail.us', 'password', 'Tester3', 'Coding');
INSERT INTO user_services VALUES ('AC134343:18CF3BD3:1774F3025AD:-7FFE', 'AVM');
INSERT INTO user_services VALUES ('AC134343:18CF3BD3:1774F3025AD:-7FFE', 'CDB');
INSERT INTO user_services VALUES ('AC134343:18CF3BD3:1774F3025AD:-7FFE', 'PSA');
INSERT INTO user_devices VALUES ('AC134343:6314D25B:1775D7EFC2E:-8000', 'Tester3 Device3', 'Windows Workstation', 'AC134343:18CF3BD3:1774F3025AD:-7FFE');
INSERT INTO user_devices VALUES ('AC134343:6314D25B:1775D7EFC2E:-7FFF', 'Tester3 Device3', 'Windows Server', 'AC134343:18CF3BD3:1774F3025AD:-7FFE');
INSERT INTO user_devices VALUES ('AC134343:6314D25B:1775D7EFC2E:-7FFE', 'Tester3 Device3', 'Main', 'AC134343:18CF3BD3:1774F3025AD:-7FFE');
INSERT INTO user_devices VALUES ('AC134343:6314D25B:1775D7EFC2E:-7FFD', 'Tester3 Device3', 'Windows Workstation', 'AC134343:18CF3BD3:1774F3025AD:-7FFE');
INSERT INTO user_devices VALUES ('AC134343:6314D25B:1775D7EFC2E:-7FFC', 'Tester3 Device3', 'Windows Server', 'AC134343:18CF3BD3:1774F3025AD:-7FFE');
INSERT INTO user_devices VALUES ('AC134343:6314D25B:1775D7EFC2E:-7FFB', 'Tester3 Device3', 'Main', 'AC134343:18CF3BD3:1774F3025AD:-7FFE');

INSERT INTO users (uniqueid, email, code, given, family) VALUES ('AC134343:18CF3BD3:1774F3025AD:-7FFD', 'tester4@fakemail.us', 'password', 'Tester4', 'Coding');
INSERT INTO users (uniqueid, email, code, given, family) VALUES ('AC134343:18CF3BD3:1774F3025AD:-7FFC', 'tester5@fakemail.us', 'password', 'Tester5', 'Coding');

INSERT INTO users (uniqueid, email, code, given, family) VALUES ('AC134343:18CF3BD3:1774F3025AD:-7FFB', 'tester6@fakemail.us', 'password', 'Tester6', 'Coding');
INSERT INTO users (uniqueid, email, code, given, family) VALUES ('AC134343:18CF3BD3:1774F3025AD:-7FFA', 'tester7@fakemail.us', 'password', 'Tester7', 'Coding');
INSERT INTO users (uniqueid, email, code, given, family) VALUES ('AC134343:18CF3BD3:1774F3025AD:-7FEF', 'tester8@fakemail.us', 'password', 'Tester8', 'Coding');
INSERT INTO users (uniqueid, email, code, given, family) VALUES ('AC134343:18CF3BD3:1774F3025AD:-7FEE', 'tester9@fakemail.us', 'password', 'Tester9', 'Coding');
INSERT INTO users (uniqueid, email, code, given, family) VALUES ('AC134343:18CF3BD3:1774F3025AD:-7FED', 'tester0@fakemail.us', 'password', 'Tester0', 'Coding');

