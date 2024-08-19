#!/bin/bash

#sudo showmount -e 192.168.1.100
#sudo mount 192.168.1.100:/var/lib/docker/volumes/myvolume/_data /var/lib/docker/volumes/myvolume/_data

sudo docker swarm init --advertise-addr=192.168.1.100
sudo docker swarm join-token worker | grep docker > /vagrant/worker.sh

