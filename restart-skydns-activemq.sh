#!/usr/bin/env bash

docker stop activemq
docker stop skydns
docker stop skydock

docker rm activemq
docker rm skydns
docker rm skydock

docker run -d -p 172.17.42.1:53:53/udp --name skydns crosbymichael/skydns -nameserver 8.8.8.8:53 -domain gos
docker run -d -v /var/run/docker.sock:/docker.sock --name skydock crosbymichael/skydock -ttl 30 -environment dev -s /docker.sock -domain gos -name skydns

docker run -p 8161:8161 -p 61616:61616 -d --name activemq rmohr/activemq
