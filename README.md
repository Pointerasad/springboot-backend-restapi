# springboot-backend-restapi
1.Language -> Java 8 , Springboot
2.Build tools - > Maven 
3.DB -> Mysql 

-> Plz download or clone this project (two branch's , like master and development)
-> When you run this project , not need to create table individualy (just create schema based on application propertise file)
-> after run project then plz try to run sql query 
Below three controller control all api request
1. UserController (user related api request bind)
2. UserProgressController (user progress related api request bind)
3. LeaderBoardController (Top ten scorer api request bind)



For user (Sample rest api)
(GET)
1. http://localhost:9090/api/find-user/by/34
Output:
{
    "id": 34,
    "name": "Asadul Islam Asad",
    "country": "Bangladesh"
}
(POST)
2.http://localhost:9090/api/save-user-info
input sample : (id no need , its auto generated)

{

    "name": "Rafikul Islam",
    "country": "Bangladesh"
}
(PUT)
3.http://localhost:9090/api/update-user-info
input (depends on id , you can updated user info)
{
    "id":38,
    "name": "Rafikul Islam Rafiq",
    "country": "Bangladesh"
}

For user progress (Sample rest api)
(GET)
1. http://localhost:9090/api/find-userProgress/by/35
output (Sample)
{
    "id": 35,
    "level": 1,
    "score": 100.0,
    "userId": 34
}

(POST)
2.http://localhost:9090/api/save-user-progress-info
input (Sample) -> (not need to input id , its auto generated , UserId , its foreign key and should be user table id matched value need to input here)
{
    "level": 1,
    "score": "100",
    "userId":34
}
(PUT)
3.http://localhost:9090/api/update-user-progress-info
Input sample  -> here based on id , you can update
{
    "id":35,
    "level": 1,
    "score": "100",
    "userId":34
}

# Load Top Scorer (Api): 
http://localhost:9090/api/find-top-scorer

