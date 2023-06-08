# How to Run

## Simplest
You can simply click run buttion in the ide to run the application or use
```
mvn spring-boot:run
```
## Using Docker Image

### Build and Run
Build and Run DockerFile in "mortgage" folder by create an image:
```
docker build -f Dockerfile -t mortgage .
```
### to see images:
```
docker run -p 8080:8080 mortgage
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
### Run the application using Maven
````
mvn exec:java -Dexec.mainClass="com.ing.mortgage"
````
