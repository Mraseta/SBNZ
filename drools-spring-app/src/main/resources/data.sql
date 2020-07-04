insert into sbnz.user (username, password,first_name,last_name,category,type,status) values ('user', '123','nikola','roncevic',0,0,0);
insert into sbnz.user (username, password,first_name,last_name,category,type,status) values ('owner', '123','marko','markovic',0,1,0);
insert into sbnz.user (username, password,first_name,last_name,category,type,status) values ('admin', '123','stipe','stipevic',0,2,0);

insert into sbnz.accommodation(name,country,city,address,zone,owner,price_per_day,distance_from_center,discount) values ('smestaj1','srbija','novi sad','detelinara1',0,'owner',100,1,0);
insert into sbnz.accommodation(name,country,city,address,zone,owner,price_per_day,distance_from_center,discount) values ('smestaj2','srbija','novi sad','detelinara2',0,'owner',150,2,0);
insert into sbnz.accommodation(name,country,city,address,zone,owner,price_per_day,distance_from_center,discount) values ('smestaj3','srbija','novi sad','detelinara3',0,'owner',200,5,0);
insert into sbnz.accommodation(name,country,city,address,zone,owner,price_per_day,distance_from_center,discount) values ('smestaj4','srbija','novi sad','detelinara4',0,'owner',250,8,0);
insert into sbnz.accommodation(name,country,city,address,zone,owner,price_per_day,distance_from_center,discount) values ('smestaj5','srbija','novi sad','detelinara5',0,'owner',120,15,0);
insert into sbnz.accommodation(name,country,city,address,zone,owner,price_per_day,distance_from_center,discount) values ('smestaj6','srbija','novi sad','detelinara6',0,'owner',130,20,0);



insert into sbnz.reservation(user_id,accommodation_id,start_date,end_date,discount,total_price,status) values (1,1,'2020-1-1','2020-2-1',0,0,2);
insert into sbnz.reservation(user_id,accommodation_id,start_date,end_date,discount,total_price,status) values (1,2,'2020-1-1','2020-2-1',0,0,2);
insert into sbnz.reservation(user_id,accommodation_id,start_date,end_date,discount,total_price,status) values (1,3,'2020-1-1','2020-2-1',0,0,2);
insert into sbnz.reservation(user_id,accommodation_id,start_date,end_date,discount,total_price,status) values (1,3,'2020-1-1','2020-2-1',0,0,1);
insert into sbnz.reservation(user_id,accommodation_id,start_date,end_date,discount,total_price,status) values (1,5,'2020-1-1','2020-2-1',0,0,1);
insert into sbnz.reservation(user_id,accommodation_id,start_date,end_date,discount,total_price,status) values (1,6,'2020-1-1','2020-2-1',0,0,1);