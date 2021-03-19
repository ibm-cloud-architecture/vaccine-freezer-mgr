./mvnw clean package -DskipTests
docker build -f src/main/docker/Dockerfile.jvm -t quay.io/ibmcase/vaccine-freezer-mgr .
docker push quay.io/ibmcase/vaccine-freezer-mgr
