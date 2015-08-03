# spring-boot-template
Template for Spring Boot with Gradle and Docker on Java 8

## Requirements
1. Java 8
2. Docker (and [boot2docker](https://github.com/boot2docker/boot2docker))
3. VirtualBox

## Start up App with Docker

#### Build Java project with Gradle wrapper
```console
$ ./gradlew build
```

#### Initialize boot2docker

```console
$ boot2docker init
```

#### Start VM

```console
$ boot2docker up
```

#### Set up DOCKER_HOST on shell

```console
$ eval "$(boot2docker shellinit)"
```

#### Build Docker image

(On project root directory: '.' indicates Dockerfile in current dir)
```console
$ docker build .
```

In the console output, you should see the last line:
"Successfully built ____" <- That is the image we want to run


#### Create container from image
```console
$ docker run -p 8080:8080 <imageId>
```
-p 8080:8080 binds port 8080 on the host 8080 in this container
You can add '-d' to run as background

#### Hit endpoints!
```console
$ curl `boot2docker ip`:8080/<whatever you need to hit>
```
