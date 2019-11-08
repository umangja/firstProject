LOCK TABLES `Address` WRITE;
INSERT INTO `Address` VALUES (1,12,0,'Bishan Swaroop Colony','Panipat','Haryana',132103),(2,0,9,'housing society','jalgaon','maharashtra',425001),(3,45,0,'Bishan Swarrop Colony','PANIPAT','Haryana',132103),(4,89,0,'outside swach gate','panpat','Haryana',132103),(5,0,1087,'Bishan Swarrop Colony','PANIPAT','Haryana',132103),(6,56,0,'Bishan Swarrop Colony','PANIPAT','Haryana',132103),(7,0,55,'vihar nagar','delhi','haryana',132103),(8,0,1087,'Bishan Swarrop Colony','PANIPAT','Haryana',132103),(9,0,5,'yo','Uk07','Uttrakhand',248198),(10,0,1087,'Bishan Swaroop Colony','Panipat','Haryana',132103);
UNLOCK TABLES;


LOCK TABLES `Users` WRITE;
INSERT INTO `Users` VALUES ('ashok','$2a$10$hGVFMAa.eRLUoClIklv7gOFK7yndrneCCN/zA2/RL3IaKEc7nmjcu','Ashok','menna','M','1998-10-30',NULL,0,NULL,7,1,1,'umang.jain.cse17@itbhu.ac.in','100'),('dhruva','$2a$10$ZaiifZuQL/26k6o6GVrCmu6GFJpAFQhDwub6B55r6XpdrZl.qJcA6','dhruva','mahajan','M','1999-06-02',NULL,0,NULL,2,1,1,'umang.jain.cse17@itbhu.ac.in','100'),('dhruva0206','$2a$10$ZaiifZuQL/26k6o6GVrCmu6GFJpAFQhDwub6B55r6XpdrZl.qJcA6','dhruva','mahajan','M','1999-06-02','employee',25000,'2015-12-21',NULL,1,1,'umang.jain.cse17@itbhu.ac.in','100'),('dhruvg','$2a$10$s6vAbJNZDK8D8AzmSQc8FuBVJcaPd9Ev3NoLsgCmIpZs4oFRivMLO','Dhruv','Gupta','M','2016-02-10','manager',150000,'2018-05-08',NULL,1,1,'umang.jain.cse17@itbhu.ac.in','dc4480aa-1b4e-433a-aed1-62f62cba44bf'),('neha','$2a$10$PvV0B3bdeAOQv.0kcqivT.jy6Fwl0FtWOyfqq/YdP0rxPuknyPyNS','neha','jain','F','1990-10-30',NULL,0,NULL,5,1,1,'umang.jain.cse17@itbhu.ac.in','100'),('sakshi','$2a$10$8./ZgCJbVZH0oAiF6iTuP.AqqIz.jgY17mXNxDt//oJ8EqnlDKRUm','Sakshi','Jain','F','2019-10-01',NULL,0,NULL,10,1,1,'umang.jain.cse17@itbhu.ac.in','100'),('Shivam','$2a$10$hGVFMAa.eRLUoClIklv7gOFK7yndrneCCN/zA2/RL3IaKEc7nmjcu','Shivam','Tomar','M','1999-05-04','peon',10000,'2011-05-04',NULL,1,1,'umang.jain.cse17@itbhu.ac.in','100'),('shivam99','$2a$10$UcDJNeUyR23WrLnMpow0A..u8nepz5QoYBTpydCoCrQUL0ZU4VG3q','Shivam','Tomar','M','2019-10-13',NULL,0,NULL,9,1,1,'umang.jain.cse17@itbhu.ac.in','100'),('Umang','$2a$10$dW5cPoUadwKGYyRF6b7T/eEYHmZFRwTovD1mJKwA8538lXON/xxFK','Umang','Jain','M','1998-04-25','manager',150000,'2011-04-25',5,1,1,'umang.jain.cse17@itbhu.ac.in','79154b4e-5354-4d0f-b512-ef2687a7dda9');
UNLOCK TABLES;


LOCK TABLES `Bills` WRITE;
INSERT INTO `Bills` VALUES (1,'2019-10-11 17:08:11',561,'Cash',0,12,'Umang',1,NULL),(2,'2019-10-11 17:29:16',1403,'Cash',0,12,'Umang',1,NULL),(3,'2019-10-11 17:48:14',472,'Online_Payment',123456789,12,'Umang',NULL,1),(4,'2019-10-11 17:55:15',543,'Online_Payment',123,10,'Umang',1,NULL),(5,'2019-10-11 18:00:00',543,'Cash',123,10,'Umang',1,NULL),(6,'2019-10-11 18:07:51',513,'Cash',123,10,'Umang',1,NULL),(7,'2019-10-12 10:23:19',394,'Cash',0,2,'Umang',NULL,1),(8,'2019-10-12 10:35:12',526,'Cash',0,2,'Umang',NULL,1),(9,'2019-10-12 15:30:13',58,'Online_Payment',1234567890,12,'Umang',1,NULL),(10,'2019-10-13 11:03:39',815,'Cash',0,20,'Umang',1,NULL),(11,'2019-10-13 23:20:56',58,'Cash',0,12,'Umang',NULL,1);
UNLOCK TABLES;


LOCK TABLES `Contains` WRITE;
INSERT INTO `Contains` VALUES (2,'cetrizine',10),(2,'paracetamol',85),(2,'Lisinopril',95),(3,'Lisinopril',90);
UNLOCK TABLES;


LOCK TABLES `Diseases` WRITE;
INSERT INTO `Diseases` VALUES (1,'Diabetes mellitus',1,5),(2,'cold',9,1),(3,'high BP',8,5);
UNLOCK TABLES;


LOCK TABLES `Feedbacks` WRITE;
INSERT INTO `Feedbacks` VALUES (1,1,'ui should be improved',1);
UNLOCK TABLES;

LOCK TABLES `Locations` WRITE;
INSERT INTO `Locations` VALUES (1,0,1,1,1),(2,0,1,1,2),(3,0,1,2,1),(4,1,1,1,1);
UNLOCK TABLES;



LOCK TABLES `Manages` WRITE;
INSERT INTO `Manages` VALUES ('Umang','dhruva0206');
UNLOCK TABLES;


LOCK TABLES `Medicine_ordered` WRITE;
INSERT INTO `Medicine_ordered` VALUES (2,1,5),(3,2,3),(3,3,2),(3,4,1),(3,4,1);
UNLOCK TABLES;


LOCK TABLES `Medicine_purchased` WRITE;
INSERT INTO `Medicine_purchased` VALUES (2,1,3),(2,1,3),(2,2,2),(2,2,2),(3,3,1),(3,4,1),(3,4,1),(2,5,2),(2,5,2),(3,6,1),(2,6,1),(2,7,12),(3,8,1),(2,9,2),(3,10,2),(2,11,2);
UNLOCK TABLES;


LOCK TABLES `Medicines` WRITE;
INSERT INTO `Medicines` VALUES (2,'Cheston cold','cipla Ltd',32,24,'2022-10-30',3,2),(3,'Lisinopril','Prinivil',512,49,'2022-04-30',4,1),(4,'med1','med1',2,0,'2018-12-30',NULL,NULL),(5,'med2','med3',2,0,'2018-12-30',NULL,NULL);
UNLOCK TABLES;


LOCK TABLES `Orders` WRITE;
INSERT INTO `Orders` VALUES (1,'2019-10-11 17:04:08',1,NULL,1),(2,'2019-10-11 17:04:23',1,NULL,1),(3,'2019-10-13 23:37:58',0,NULL,1),(4,'2019-10-13 23:39:00',0,NULL,1);
UNLOCK TABLES;


LOCK TABLES `Partners` WRITE;
INSERT INTO `Partners` VALUES (1,'verma Medicals','6064325869',6);
UNLOCK TABLES;


LOCK TABLES `Patients` WRITE;
INSERT INTO `Patients` VALUES (1,'sudhir','8896554823');
UNLOCK TABLES;


LOCK TABLES `user_roles` WRITE;
INSERT INTO `user_roles` VALUES (1,'admin','ROLE_ADMIN'),(2,'dhruva0206','ROLE_EMP'),(3,'dhruva','ROLE_USER'),(4,'Umang','ROLE_EMP'),(5,'Shivam','ROLE_EMP'),(6,'ashok','ROLE_USER'),(7,'neha','ROLE_USER'),(8,'shivam99','ROLE_USER'),(9,'dhruvg','ROLE_EMP'),(10,'sakshi','ROLE_USER');
UNLOCK TABLES;


LOCK TABLES `Users` WRITE;
INSERT INTO `Users` VALUES ('admin','$2a$10$z5zvX/nXeJANt/CBsA5ZWu7mI5k3AFrZfFKwRBczsDze1A6QzuAO2','Anil','Jain','M','1985-10-30','OWNER',NULL,'2010-08-25',1,1,1,'umang.jain.cse17@itbhu.ac.in','100'),('ashok','$2a$10$hGVFMAa.eRLUoClIklv7gOFK7yndrneCCN/zA2/RL3IaKEc7nmjcu','Ashok','menna','M','1998-10-30',NULL,0,NULL,7,1,1,'umang.jain.cse17@itbhu.ac.in','100'),('dhruva','$2a$10$ZaiifZuQL/26k6o6GVrCmu6GFJpAFQhDwub6B55r6XpdrZl.qJcA6','dhruva','mahajan','M','1999-06-02',NULL,0,NULL,2,1,1,'umang.jain.cse17@itbhu.ac.in','100'),('dhruva0206','$2a$10$ZaiifZuQL/26k6o6GVrCmu6GFJpAFQhDwub6B55r6XpdrZl.qJcA6','dhruva','mahajan','M','1999-06-02','employee',25000,'2015-12-21',NULL,1,1,'umang.jain.cse17@itbhu.ac.in','100'),('dhruvg','$2a$10$s6vAbJNZDK8D8AzmSQc8FuBVJcaPd9Ev3NoLsgCmIpZs4oFRivMLO','Dhruv','Gupta','M','2016-02-10','manager',150000,'2018-05-08',NULL,1,1,'umang.jain.cse17@itbhu.ac.in','dc4480aa-1b4e-433a-aed1-62f62cba44bf'),('neha','$2a$10$PvV0B3bdeAOQv.0kcqivT.jy6Fwl0FtWOyfqq/YdP0rxPuknyPyNS','neha','jain','F','1990-10-30',NULL,0,NULL,5,1,1,'umang.jain.cse17@itbhu.ac.in','100'),('sakshi','$2a$10$8./ZgCJbVZH0oAiF6iTuP.AqqIz.jgY17mXNxDt//oJ8EqnlDKRUm','Sakshi','Jain','F','2019-10-01',NULL,0,NULL,10,1,1,'umang.jain.cse17@itbhu.ac.in','100'),('Shivam','$2a$10$hGVFMAa.eRLUoClIklv7gOFK7yndrneCCN/zA2/RL3IaKEc7nmjcu','Shivam','Tomar','M','1999-05-04','peon',10000,'2011-05-04',NULL,1,1,'umang.jain.cse17@itbhu.ac.in','100'),('shivam99','$2a$10$UcDJNeUyR23WrLnMpow0A..u8nepz5QoYBTpydCoCrQUL0ZU4VG3q','Shivam','Tomar','M','2019-10-13',NULL,0,NULL,9,1,1,'umang.jain.cse17@itbhu.ac.in','100'),('Umang','$2a$10$dW5cPoUadwKGYyRF6b7T/eEYHmZFRwTovD1mJKwA8538lXON/xxFK','Umang','Jain','M','1998-04-25','manager',150000,'2011-04-25',5,1,1,'umang.jain.cse17@itbhu.ac.in','79154b4e-5354-4d0f-b512-ef2687a7dda9');
UNLOCK TABLES;


LOCK TABLES `Suppliers` WRITE;
INSERT INTO `Suppliers` VALUES (1,'khan medicines','9671392726',3),(2,'cipla factories','9984028843',4);
UNLOCK TABLES;
