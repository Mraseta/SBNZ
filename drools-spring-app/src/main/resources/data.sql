insert into sbnz.user (username, password,first_name,last_name,category) values ('nikola', '123','nikola','roncevic',0);
insert into sbnz.user (username, password,first_name,last_name,category) values ('marko', '123','marko','markovic',0);
insert into sbnz.user (username, password,first_name,last_name,category) values ('stipe', '123','stipe','stipevic',0);

insert into sbnz.accommodation(name,country,city,address,zone,owner,price_per_day) values ('smestaj1','srbija','novi sad','detelinara1',0,'pera1',100);
insert into sbnz.accommodation(name,country,city,address,zone,owner,price_per_day) values ('smestaj2','srbija','novi sad','detelinara2',0,'pera1',150);
insert into sbnz.accommodation(name,country,city,address,zone,owner,price_per_day) values ('smestaj3','srbija','novi sad','detelinara3',0,'pera1',200);
insert into sbnz.accommodation(name,country,city,address,zone,owner,price_per_day) values ('smestaj4','srbija','novi sad','detelinara4',0,'pera2',250);
insert into sbnz.accommodation(name,country,city,address,zone,owner,price_per_day) values ('smestaj5','srbija','novi sad','detelinara5',0,'pera2',120);
insert into sbnz.accommodation(name,country,city,address,zone,owner,price_per_day) values ('smestaj6','srbija','novi sad','detelinara6',0,'pera2',130);


insert into sbnz.reservation(user_id,accommodation_id,start_date,end_date,discount,total_price) values (1,1,'2020-1-1','2020-2-1',0,0);
insert into sbnz.reservation(user_id,accommodation_id,start_date,end_date,discount,total_price) values (1,2,'2020-1-1','2020-2-1',0,0);
insert into sbnz.reservation(user_id,accommodation_id,start_date,end_date,discount,total_price) values (1,3,'2020-1-1','2020-2-1',0,0);
insert into sbnz.reservation(user_id,accommodation_id,start_date,end_date,discount,total_price) values (1,4,'2020-1-1','2020-2-1',0,0);
insert into sbnz.reservation(user_id,accommodation_id,start_date,end_date,discount,total_price) values (1,5,'2020-1-1','2020-2-1',0,0);
insert into sbnz.reservation(user_id,accommodation_id,start_date,end_date,discount,total_price) values (1,6,'2020-1-1','2020-2-1',0,0);
