#!/bin/bash

echo "Begining of Swarm Service ... "

sudo docker service create --name meu-app --replicas 15 -dt -p 80:80 --mount type=volume,src=myvolume,dst=/myvolume/ webdevops/php-apache:alpine-php7

echo "Ending of Swarm Service ..."

