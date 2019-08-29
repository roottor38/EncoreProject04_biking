insert into rent_spot (rent_spot_name, num_bike) values ('서초1', 5);
insert into rent_spot (rent_spot_name, num_bike) values ('교대1', 5);
insert into rent_spot (rent_spot_name, num_bike) values ('교대정문', 5);
insert into rent_spot (rent_spot_name, num_bike) values ('교대입구', 5);
insert into rent_spot (rent_spot_name, num_bike) values ('교대교차로', 5);
insert into rent_spot (rent_spot_name, num_bike) values ('대여중', 0);


insert into b_user (id, pw, name, phone) values ('user1', '1234', 'name1', '010');
insert into b_user (id, pw, name, phone) values ('user2', '1234', 'name2', '010');
insert into b_user (id, pw, name, phone) values ('user3', '1234', 'name3', '010');
insert into b_user (id, pw, name, phone) values ('user4', '1234', 'name4', '010');

insert into bike values (bike_id.nextval, '서초1');
insert into bike values (bike_id.nextval, '서초1');
insert into bike values (bike_id.nextval, '서초1');
insert into bike values (bike_id.nextval, '서초1');
insert into bike values (bike_id.nextval, '서초1');

insert into bike values (bike_id.nextval, '교대1');
insert into bike values (bike_id.nextval, '교대1');
insert into bike values (bike_id.nextval, '교대1');
insert into bike values (bike_id.nextval, '교대1');
insert into bike values (bike_id.nextval, '교대1');

insert into bike values (bike_id.nextval, '교대정문');
insert into bike values (bike_id.nextval, '교대정문');
insert into bike values (bike_id.nextval, '교대정문');
insert into bike values (bike_id.nextval, '교대정문');
insert into bike values (bike_id.nextval, '교대정문');

insert into bike values (bike_id.nextval, '교대입구');
insert into bike values (bike_id.nextval, '교대입구');
insert into bike values (bike_id.nextval, '교대입구');
insert into bike values (bike_id.nextval, '교대입구');
insert into bike values (bike_id.nextval, '교대입구');


insert into bike values (bike_id.nextval, '교대교차로');
insert into bike values (bike_id.nextval, '교대교차로');
insert into bike values (bike_id.nextval, '교대교차로');
insert into bike values (bike_id.nextval, '교대교차로');
insert into bike values (bike_id.nextval, '교대교차로');
commit;