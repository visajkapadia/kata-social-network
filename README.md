SOCIAL NETWORKING KATA
-----------------------

Steps to build the project:
- Clone to the project to local computer and open it as a maven project by selecting pom.xml file.

Project Management Tool:
- Maven

Dependecies:
- jUnit (included in pom.xml)

This project mainly consists of 4 different classes.

1. KataUser
2. UserMessage
3. UserTimeline
4. TimeAgo

- KataUser is used to create a User object. It has userId, userName and set of userIds which are followed by that user.
- UserMessage class is used to create a post. We are required to pass User (who created this message) & message content inorder to initialize.
- UserTimeline is a class which contains all the mapping of users and their posts.
- TimeAgo is a helper class which helps converting timestamp to x seconds ago format.
