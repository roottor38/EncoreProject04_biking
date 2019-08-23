-- bike_tablespace 생성
create tablespace bike_tablespace
datafile '/u01/app/oracle/oradata/XE/bike_tablespace.dbf' size 1024m;

-- 유저 생성
CREATE USER bike 
IDENTIFIED BY bike
DEFAULT TABLESPACE bike_tablespace
TEMPORARY TABLESPACE TEMP;

--모든 권한 주기
GRANT connect, resource, dba TO bike;