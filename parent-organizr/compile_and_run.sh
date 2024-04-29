#!/bin/bash
./mvnw clean package
docker build --no-cache -t parent_organizr:latest .

# Check if the container exists
if [ $(docker ps -a -f name=parent_organizr | grep -w parent_organizr | wc -l) -eq 1 ]; then
   # If the container exists, remove it
    docker rm parent_organizr
    echo "Container exists, removing it"
fi

# Run a new container with the new image
docker run --name parent_organizr -p 8080:8080 parent_organizr:latest
echo "Running new container with new image"