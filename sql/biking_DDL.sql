

/* Drop Tables */

DROP TABLE return_info CASCADE CONSTRAINTS;
DROP TABLE rent_info CASCADE CONSTRAINTS;
DROP TABLE bike CASCADE CONSTRAINTS;
DROP TABLE b_user CASCADE CONSTRAINTS;
DROP TABLE rent_spot CASCADE CONSTRAINTS;



/* Drop Sequences */

DROP SEQUENCE bike_id;
DROP SEQUENCE rent_id;


/* Create Sequences */

CREATE SEQUENCE bike_id INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE rent_id INCREMENT BY 1 START WITH 1;



/* Create Tables */

CREATE TABLE bike
(
	bike_id number PRIMARY KEY,
	rent_spot_name varchar2(20)
);


CREATE TABLE b_user
(
	id varchar2(20) PRIMARY KEY,
	pw varchar2(20) NOT NULL,
	name varchar2(20) NOT NULL,
	phone varchar2(20) NOT NULL
	
);


CREATE TABLE rent_info
(
	rent_id number PRIMARY KEY,
	rent_time date NOT NULL,
	bike_id number NOT NULL UNIQUE,
	id varchar2(20),
	rent_spot_name varchar2(20)
);


CREATE TABLE rent_spot
(
	rent_spot_name varchar2(20) PRIMARY KEY,
	num_bike number NOT NULL
);


CREATE TABLE return_info
(
	rent_id number PRIMARY KEY,
	sch_time date NOT NULL,
	return_time date,
	rent_spot_name varchar2(20) UNIQUE
);

/* Create Foreign Keys */

ALTER TABLE rent_info
	ADD FOREIGN KEY (bike_id)
	REFERENCES bike (bike_id)
;


ALTER TABLE rent_info
	ADD FOREIGN KEY (id)
	REFERENCES b_user (id)
;


ALTER TABLE return_info
	ADD FOREIGN KEY (rent_id)
	REFERENCES rent_info (rent_id)
;


ALTER TABLE bike
	ADD FOREIGN KEY (rent_spot_name)
	REFERENCES rent_spot (rent_spot_name)
;


ALTER TABLE rent_info
	ADD FOREIGN KEY (rent_spot_name)
	REFERENCES rent_spot (rent_spot_name)
;


ALTER TABLE return_info
	ADD FOREIGN KEY (rent_spot_name)
	REFERENCES rent_spot (rent_spot_name)
;

purge recyclebin;

