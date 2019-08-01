# PictureCloud
A small picture server springboot application

## notify
default port: 8081
### run
java -jar PictureCloud-0.0.1-SNAPSHOT.jar -u username -p password

##jar
###default database propties

spring.datasource.url= jdbc:mysql://127.0.0.1:3306/images

spring.datasource.username=users

spring.datasource.password=88888888

###you also can using command line properties to replace the default properties
####exp: 
java -jar PictureCloud-0.0.1-SNAPSHOT.jar -u username -p password --spring.datasource.url=you_database_URL --spring.datasource.username=users --spring.datasource.password=password

## image files saving location 

the difference of URL between windwos and linux 

see the code src/main/java/com/chicex/PictureCloud/ApplicationStartUpConfig.java

## DataBase
Using database Mysql5.1

### prepare the database 
build the database:

database named: images,

table named: imagesTable,

![Database](http://pic.chicexcode.com/image/getImage/447c1914ca8d9f90cb6e810db5cb176e93047a9c.png "table")

# PictureCloud
一个小型的图床服务器软件

## 注意
默认端口：8081
###运行

java -jar PictureCloud-0.0.1-SNAPSHOT.jar -u username -p password

##jar
###默认数据库配置

spring.datasource.url= jdbc:mysql://127.0.0.1:3306/images

spring.datasource.username=users

spring.datasource.password=88888888

## 图片保存路径

注意linux和windows下URL的不同

详见代码 src/main/java/com/chicex/PictureCloud/Utils/ImgUtils.java

## 数据库
采用数据库 Mysql5.1

### 准备数据库

创建数据库:

数据库名: images,

表名: imagesTable,

表结构:
![Database](http://pic.chicexcode.com/image/getImage/447c1914ca8d9f90cb6e810db5cb176e93047a9c.png "table")


# preview 预览

![preview](http://pic.chicexcode.com/image/getImage/dd3a7fb31d7b8ca1fbd8b1457042737024629a0e.png "preview")



