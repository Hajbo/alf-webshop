# ALF assignment

Info: You can find the logs in your /tmp/auth-server and /tmp/resource-server folders.

## Coding policies
1. Everyone should work on his own branch
2. Please commit regularly
3. At the end of your coding session, always push your changes
4. **Always** request a review for your changes before merging into the `master` branch
5. Use the GitHub Projects to track issues - E.g. create an issue if you find a problem that you are not going to fix in the current session

## Start the current skeleton
- Open two terminal windows:
 - terminal1: `cd auth-server`
 - terminal2: `cd resource-server`
- Create the jar files: `mvn package`
- Run them:
 - `java -jar target/name-of-the-jar-file.jar` -> currently it is **authserver/resourceserver-0.0.1-SNAPSHOT.jar**
 

## API usage

### Get an access token
Since there are two users added when the AuthorizationServer starts, you can use them:
`curl -i -X POST -u frontend:secret http://0.0.0.0:8081/oauth/token\?grant_type\=password\&username\=admin\&password\=admin`

### Access an endpoint
`curl -i -H "Authorization: Bearer your_access_token" http://localhost:8080/message`

### Register a new user
`curl -i -X POST -d '{"name":"test","password":"test"}' -H "Content-Type: application/json"  http://0.0.0.0:8081/register`
`curl -i -X POST -u frontend:secret http://0.0.0.0:8081/oauth/token\?grant_type\=password\&username\=test\&password\=test`