INSERT INTO `role` (`role_id`,`user_id`,`role`) VALUES (1,1,'ROLE_ADMIN');
INSERT INTO `role` (`role_id`,`user_id`,`role`) VALUES (2,2,'ROLE_USER');

INSERT INTO `route` (`route_id`,`train_id`,`station_id`,`order_station`,`arrival_time`,`departure_time`) VALUES (1,1,6,1,'2018-01-01 23:25:00','2018-01-01 23:25:00');
INSERT INTO `route` (`route_id`,`train_id`,`station_id`,`order_station`,`arrival_time`,`departure_time`) VALUES (2,1,7,2,'2018-01-02 05:01:00','2018-01-02 05:02:00');
INSERT INTO `route` (`route_id`,`train_id`,`station_id`,`order_station`,`arrival_time`,`departure_time`) VALUES (3,1,2,3,'2018-01-02 09:40:00','2018-01-02 09:45:00');
INSERT INTO `route` (`route_id`,`train_id`,`station_id`,`order_station`,`arrival_time`,`departure_time`) VALUES (4,1,5,4,'2018-01-02 13:50:00','2018-01-02 13:52:00');
INSERT INTO `route` (`route_id`,`train_id`,`station_id`,`order_station`,`arrival_time`,`departure_time`) VALUES (5,1,1,5,'2018-01-02 15:18:00','2018-01-02 15:58:00');
INSERT INTO `route` (`route_id`,`train_id`,`station_id`,`order_station`,`arrival_time`,`departure_time`) VALUES (6,1,3,6,'2018-01-03 02:11:00','2018-01-03 02:28:00');
INSERT INTO `route` (`route_id`,`train_id`,`station_id`,`order_station`,`arrival_time`,`departure_time`) VALUES (7,1,4,7,'2018-01-03 07:14:00','2018-01-03 07:19:00');
INSERT INTO `route` (`route_id`,`train_id`,`station_id`,`order_station`,`arrival_time`,`departure_time`) VALUES (8,1,9,8,'2018-01-03 12:55:00','2018-01-03 12:55:00');

INSERT INTO `user` (`user_id`,`user_name`,`password`,`email`,`first_name`,`last_name`,`birthday`,`enabled`) VALUES (1,'admin','$2a$10$Udj67/IPUjVLQomqARnRAuAWcp/LA31Mi1CXchIDPg2H9CEh95/TC','aleksandrkorzinov@gmail.com','Aleksandr','Korzinov','1990-12-14',1);
INSERT INTO `user` (`user_id`,`user_name`,`password`,`email`,`first_name`,`last_name`,`birthday`,`enabled`) VALUES (2,'user','$2a$10$2Jtbrs0UnagtHdL624j8A.gr9v1nQW1pYTbqWJMtML.2.r8ZGwspa','test123@gmail.com','User','Test','1999-10-10',1);

INSERT INTO `schedule` (`schedule_id`,`train_id`,`date`,`free_seats`) VALUES (1,1,'2018-11-27',25);

INSERT INTO `station` (`station_id`,`station_name`) VALUES (8,'Adler');
INSERT INTO `station` (`station_id`,`station_name`) VALUES (9,'Kazan');
INSERT INTO `station` (`station_id`,`station_name`) VALUES (4,'Krasnodar');
INSERT INTO `station` (`station_id`,`station_name`) VALUES (5,'Lipetsk');
INSERT INTO `station` (`station_id`,`station_name`) VALUES (2,'Moscow');
INSERT INTO `station` (`station_id`,`station_name`) VALUES (3,'Rostov-on-Don');
INSERT INTO `station` (`station_id`,`station_name`) VALUES (6,'Saint-Petersburg');
INSERT INTO `station` (`station_id`,`station_name`) VALUES (7,'Tver');
INSERT INTO `station` (`station_id`,`station_name`) VALUES (10,'Volgograd');
INSERT INTO `station` (`station_id`,`station_name`) VALUES (1,'Voronezh');

INSERT INTO `train` (`train_id`,`train_name`,`quantity_seats`) VALUES (1,'035A',25);
INSERT INTO `train` (`train_id`,`train_name`,`quantity_seats`) VALUES (2,'739',20);
INSERT INTO `train` (`train_id`,`train_name`,`quantity_seats`) VALUES (3,'738',20);
INSERT INTO `train` (`train_id`,`train_name`,`quantity_seats`) VALUES (4,'036C',30);
INSERT INTO `train` (`train_id`,`train_name`,`quantity_seats`) VALUES (5,'020C',20);
INSERT INTO `train` (`train_id`,`train_name`,`quantity_seats`) VALUES (6,'019C',20);
INSERT INTO `train` (`train_id`,`train_name`,`quantity_seats`) VALUES (7,'998',20);
INSERT INTO `train` (`train_id`,`train_name`,`quantity_seats`) VALUES (8,'999',20);
INSERT INTO `train` (`train_id`,`train_name`,`quantity_seats`) VALUES (9,'1000',25);