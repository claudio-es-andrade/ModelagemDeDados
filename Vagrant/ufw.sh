#!/bin/bash

# sudo apt update && sudo apt install ufw -y
sudo ufw enable
sudo ufw allow 22/tcp
sudo ufw allow 80/tcp
sudo ufw allow 2377/tcp
sudo ufw allow 2377/udp
sudo ufw allow 3306/tcp
sudo ufw allow 8080/tcp

sudo ufw allow to 192.168.1.254 from 192.168.1.0/24 proto udp
sudo ufw allow nfs/tcp

