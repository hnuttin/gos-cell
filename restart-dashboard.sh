#!/usr/bin/env bash

docker stop msd-gos

docker rm -f msd-gos

mvn -f ../gos-dashboard/pom.xml clean package

docker run -d --name msd-gos -p 8080:8080 msd-gos
#docker run -it -p 8080:8080 msd-gos