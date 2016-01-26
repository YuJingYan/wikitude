
use ar1;

create table target(
  targetId INT UNSIGNED NOT NULL AUTO_INCREMENT,
  targetName VARCHAR(255) NOT NULL,
  name VARCHAR(255) NOT NULL,
  openTime VARCHAR(255) NOT NULL,
  floorNumber INT NOT NULL,
  description VARCHAR(255),
  PRIMARY KEY (targetId));

Insert into target (targetName,name,openTime,floorNumber,description) values ('b3','教三','8:00-23:00’,10,'内有观光电梯两部，扶梯两部，一般电梯四部，旋转楼梯无数。其设计之大气，灯光之唯美，设备之齐全，让历届北邮学子仰慕。其立于北邮西门毛主席像南侧。');
Insert into target (targetName,name,openTime,floorNumber,description) values ('b32','教三','8:00-23:00',10,'内有观光电梯两部，扶梯两部，一般电梯四部，旋转楼梯无数。其设计之大气，灯光之唯美，设备之齐全，让历届北邮学子仰慕。其立于北邮西门毛主席像南侧。');
Insert into target (targetName,name,openTime,floorNumber,description) values ('b21','教二','8:00-22:30',5,'教二设有教室、实验室若干，也分布有众多行政办公室');
Insert into target (targetName,name,openTime,floorNumber,description) values ('b22','教二','8:00-22:30',5,'教二设有教室、实验室若干，也分布有众多行政办公室');
Insert into target (targetName,name,openTime,floorNumber,description) values ('b23','教二','8:00-22:30',5,'教二设有教室、实验室若干，也分布有众多行政办公室');
Insert into target (targetName,name,openTime,floorNumber,description) values ('h1','校医院','8:00-17:00',4,'校医院位于中门附近，设有内外科、皮肤科等各科室');



Commit;



