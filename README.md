<h1 align="left"> Social Networking App Demonstration </h1>

<h2> Description </h2>
This is an effort to demonstrate a social networking application that has users, followers, posts and feeds. This repository is built to handle user follower and following related functionalities.

- For user auth, please refer to <a href="https://github.com/banerjee-ronitb/auth-service"> Auth Service </a>
- For user followers, please refer to <a href="https://github.com/banerjee-ronitb/graph-service"> Graph Service </a>
- For user posts, please refer to <a href="https://github.com/banerjee-ronitb/post-service"> Post Service </a>

<h2> Newsfeed Microservice </h2>

This service showcases Spring Boot integration with Apache Cassandra database and Apache Kafka. 
- User should send access token in Authorization header to access the APIs
- Newsfeed service consumes an event on every post creation
- FeedGenerator service updates the feeds of followers/friends. 
- It queries the followers by email id, which is part of event payload.
- Interservice communication is authenticated and authorized with <a href="https://developer.okta.com/blog/2021/05/05/client-credentials-spring-security"> OKTA Client Credentials Flow </a>


<h3> Getting Started </h3>

Follow the steps below:

<h4> 🖐 Requirements </h4>

- Java 11
- Maven 3.6.x
- OKTA developer account

<h4> ⏳ Installation </h4>

This application uses maven to build. Refer to <a href="https://maven.apache.org/install.html"> Installing Maven </a> to install maven.

```bash
Step 1: mvn clean install
```
```bash
Step 2: mvn spring-boot:run
```
<h4> Roadmap </h4>

Add an additional repository to showcase deployment using Kuberenetes.

