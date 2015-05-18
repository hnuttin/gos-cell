#!/usr/bin/env bash

docker stop a1
docker stop a2
docker stop a3
docker stop a4
docker stop b1
docker stop b2
docker stop b3
docker stop b4
docker stop c1
docker stop c2
docker stop c3
docker stop c4
docker stop d1
docker stop d2
docker stop d3
docker stop d4

sleep 3

docker rm -f a1
docker rm -f a2
docker rm -f a3
docker rm -f a4
docker rm -f b1
docker rm -f b2
docker rm -f b3
docker rm -f b4
docker rm -f c1
docker rm -f c2
docker rm -f c3
docker rm -f c4
docker rm -f d1
docker rm -f d2
docker rm -f d3
docker rm -f d4