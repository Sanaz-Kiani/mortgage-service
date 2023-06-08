# How to Run

## Simplest

You can simply click run button in the Intellij to run the application or use

```
mvn spring-boot:run
```

## Using Docker Image

### Build and Run

Build and Run DockerFile in "mortgage" folder by creating an image:

```
docker build -f Dockerfile -t mortgage .
```

### to see images:

```
docker images
```

### to run the image:

```
docker run -p 8080:8080 mortgage
```

### to see all container:

```
docker container ls
```

## Using Maven

If you have cloned the project source code, you can use Maven to build and run the application:

### Navigate to the project directory

```
 cd mortgage
```

### Clean the previous build artifacts and compile the application

```
 mvn clean compile
```

### Build the application JAR file

```
 mvn package
```

### and then run:

````
java -jar /target/mortgage-0.0.1-SNAPSHOT.jar
````
