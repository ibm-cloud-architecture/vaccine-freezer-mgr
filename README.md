# Vaccine Freezer manager service

This is a basic quarkus - reactive messaging app to listen to Alerts on freezer and also manage the life cycle of each freezer devices. This is to complete the Cold Chain or order management use cases of the [vaccine solution demo]().

## Running the application in dev mode

To run locally in dev mode, you need to start the Kafka and Zookeeper so the app can connect to Kafka.  

```shell
docker-compose up -d
```

You can then run the application in dev mode that enables live coding using:

```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory and a set of openshift / kubernetes manifests.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

## Git Action

The .github folder has a git Action workflow to build the image and push to your selected registry. You need to define the following github secrets to make it working: For DOCKER_REGISTRY we are using `quay.io` and `ibmcase` DOCKER_REPOSITORY.

```shell
DOCKER_USERNAME 
DOCKER_PASSWORD
DOCKER_REPOSITORY 
DOCKER_IMAGE_NAME 
DOCKER_REGISTRY
```

Which means a docker image is already available for you to use if you want to deploy to OpenShift directly.

## Deploy to OpenShift

Once logged to the cluster.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/vaccine-reefer-mgr-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.html.
