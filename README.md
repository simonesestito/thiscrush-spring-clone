# Thiscrush Spring Clone
A Spring RESTful service similar to the popular thiscrush website

## How to build/run
- Create a localhost MySQL/MariaDB database
- Execute <a href="init.sql">this script</a> on the database
- <code>git clone https://github.com/simonesestito/thiscrush-spring-clone</code>
- <code>cd thiscrush-spring-clone</code>
- <code>./mvnw spring-boot:run</code>

## API Endpoints
Base URL: http://localhost:8080/thiscrush

### POST /register
Request: SignUpUser
- username: string, min. length 5, max 50
- password: string, min. length 8, max 100

Response: SimpleResult
- success: boolean
- message: string, it contains the error, if success is false

### POST /login
Request: LoginUser
- username: your username
- password: your password

Response: LoginResult
- username
- token: JWT needed in /my/\*\* endpoints
- expireDate: token expiration date in millis

**Note: the following endpoints need a valid JWT in header X-API-TOKEN.**
If the JWT has expired, the response will contain a "error" field, with "jwt\_expired" value.

### GET /my/me
Response: UserInfo
- id: database user ID
- username

### POST /my/send
Send an anonymous message

Request: MessageInputDto
- text
- to: addressee's username
- secret: boolean, false if the message can be public

Response: SimpleResult
- success: true if message has sent successfully
- message

### GET /my/inbox
List messages you received

Response: MessageResult
- username: your username
- messages
	- id: message ID
	- text
	- date: sending date
	- addresseeUsername: in this case, your username
	- secret: true if only you can see this message

### GET /my/inbox/{username}
Path variables:
- username of the inbox's owner

Response: MessageResult
- username: as in the url
- messages: as above

### GET /my/sent
List the messages you've sent

Response: MessageResult
- username: your username
- messages: list of messages you've sent, as above


