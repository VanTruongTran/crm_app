/* ADD DATABASE */
CREATE DATABASE IF NOT EXISTS crm_app; -- DROP DATABASE crm_app
USE crm_app;

/* ADD TABLE */
CREATE TABLE IF NOT EXISTS roles (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(100),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS users (
    id INT NOT NULL AUTO_INCREMENT,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    fullname VARCHAR(100) NOT NULL,
    avatar VARCHAR(100),
    role_id INT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS status (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS jobs (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    start_date DATE,
    end_date DATE,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS tasks (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    start_date DATE,
    end_date DATE,
    user_id INT NOT NULL,
    job_id INT NOT NULL,
    status_id INT NOT NULL,
    PRIMARY KEY (id)
);

/* ADD CONSTRAINT */
ALTER TABLE users ADD FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE;
ALTER TABLE tasks ADD FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE;
ALTER TABLE tasks ADD FOREIGN KEY (job_id) REFERENCES jobs(id) ON DELETE CASCADE;
ALTER TABLE tasks ADD FOREIGN KEY (status_id) REFERENCES status(id) ON DELETE CASCADE;

ALTER TABLE jobs ADD CHECK (end_date - start_date >= 0);
ALTER TABLE tasks ADD CHECK (end_date - start_date >= 0);

/* ADD VALUES */
INSERT INTO roles(name, description) VALUES
("ADMIN", "Quản trị hệ thống"),
("LEADER", "Quản lý dự án"),
("MEMBER", "Nhân viên thường");

INSERT INTO users(email,password,fullname,avatar,role_id) VALUES
("nguyena@gmail.com","a12345","Nguyen Van A","plugins/images/users/nguyenvana.jpg",1),
("nguyenb@gmail.com","b12345","Nguyen Van B","plugins/images/users/nguyenvanb.jpg",2),
("nguyenc@gmail.com","c12345","Nguyen Van C","plugins/images/users/nguyenvanc.jpg",3),
("nguyend@gmail.com","d12345","Nguyen Van D","plugins/images/users/nguyenvand.jpg",3),
("nguyene@gmail.com","e12345","Nguyen Van E","plugins/images/users/nguyenvane.jpg",1),
("nguyenf@gmail.com","f12345","Nguyen Van F","plugins/images/users/nguyenvanf.jpg",2),
("nguyeng@gmail.com","g12345","Nguyen Van G","plugins/images/users/nguyenvang.jpg",3),
("nguyenh@gmail.com","h12345","Nguyen Van H","plugins/images/users/nguyenvanh.jpg",3);

INSERT INTO status(name) VALUES
("CHƯA THỰC HIỆN"),
("ĐANG THỰC HIỆN"),
("ĐÃ HOÀN THÀNH");

INSERT INTO jobs(name,start_date,end_date) VALUES
("PROJECT 01","2022-09-09","2022-09-30"),
("PROJECT 02","2022-11-10","2022-11-25"),
("PROJECT 03","2022-08-09","2022-12-10"),
("PROJECT 04","2022-05-09","2022-9-10");

INSERT INTO tasks(name,start_date,end_date,user_id,job_id,status_id) VALUES
("TASK 01","2022-09-09","2022-09-09",1,1,1),
("TASK 02","2022-09-09","2022-09-10",3,2,2),
("TASK 03","2022-08-12","2022-08-15",8,3,2),
("TASK 04","2022-06-09","2022-06-09",2,1,3),
("TASK 05","2022-07-11","2022-07-12",7,4,3),
("TASK 06","2022-10-10","2022-10-11",3,2,1),
("TASK 07","2022-10-10","2022-10-12",6,3,2),
("TASK 08","2022-12-03","2022-12-05",4,3,1),
("TASK 09","2022-06-01","2022-06-04",8,4,2),
("TASK 10","2022-11-25","2022-11-26",5,4,2),
("TASK 11","2022-09-09","2022-09-09",1,4,2),
("TASK 12","2022-09-09","2022-09-10",3,4,1),
("TASK 13","2022-08-12","2022-08-15",8,1,3),
("TASK 14","2022-06-09","2022-06-09",2,3,1),
("TASK 15","2022-07-11","2022-07-12",7,2,1),
("TASK 16","2022-10-10","2022-10-11",3,1,2),
("TASK 17","2022-10-10","2022-10-12",6,1,1),
("TASK 18","2022-12-03","2022-12-05",4,3,1),
("TASK 19","2022-06-01","2022-06-04",8,2,3),
("TASK 20","2022-11-25","2022-11-26",5,2,3),
("TASK 21","2022-09-09","2022-09-09",1,2,3),
("TASK 22","2022-09-09","2022-09-10",3,1,1),
("TASK 23","2022-08-12","2022-08-15",8,1,1),
("TASK 24","2022-06-09","2022-06-09",2,3,1),
("TASK 25","2022-07-11","2022-07-12",7,2,2),
("TASK 26","2022-10-10","2022-10-11",3,3,3),
("TASK 27","2022-10-10","2022-10-12",6,1,1),
("TASK 28","2022-12-03","2022-12-05",4,4,1),
("TASK 29","2022-06-01","2022-06-04",8,1,3),
("TASK 30","2022-11-25","2022-11-26",5,2,1);
