
/* Drop Tables */

DROP TABLE customer CASCADE CONSTRAINTS;
DROP TABLE restaurant CASCADE CONSTRAINTS;

/* Drop Sequences */

DROP SEQUENCE restaurant_id;

/* Create Sequences */

CREATE SEQUENCE restaurant_id INCREMENT BY 1 START WITH 1;


/* Create Tables */

CREATE TABLE customer
(
	id varchar2(15 char) PRIMARY KEY,
	pw varchar2(15 char),
	district varchar2(15 char),
	discription varchar2(15 char),
	grade varchar2(6 char)
);


CREATE TABLE restaurant
(
	restaurant_id number PRIMARY KEY,
	title varchar2(20 char),
	sitelink varchar2(30 char),
	contact varchar2(15 char),
	address varchar2(30 char),
	district varchar2(15 char),
	category varchar2(15 char)
);



