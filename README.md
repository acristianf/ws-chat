# Websocket Chat in Java

This is a simple project, the idea is to create a Java application that lets 
you join chatrooms and chat.

## What does it need to do?

1. Let users join a chatroom
2. Let users send messages to it
3. Let users leave the chatroom

## What do we need for it?

1. A server(chatrooms) - not done yet
2. A client that can join and leave servers - Maybe done?

## Basic usage

``` 
mvn clean package 
```
To initialize a server use(8082 only port supported):
```
java -jar /target/ws-chat-1.0-SNAPSHOT.jar server
```
To start a client use:
```
java -jar /target/ws-chat-1.0-SNAPSHOT.jar client hostname port
```
