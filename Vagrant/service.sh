#!/bin/bash

sudo docker volume create myvolume

sudo mkdir /var/lib/docker/volumes/myvolume

sudo mkdir /var/lib/docker/volumes/myvolume/_data

sudo touch /var/lib/docker/volumes/myvolume/_data/index.php

sudo cat > /var/lib/docker/volumes/myvolume/_data/index.php << EOF
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8"/>
    <title>EXEMPLO PHP</title>
  </head>
  <body>
    <h1>OK !!! BEM VINDOS AO APACHE !!! </h1>
    <?php 
        phpinfo();
    ?>
  </body>
</html>
EOF

echo "Updating Volume myvolume ..."
sudo docker volume update --availability active myvolume

sudo apt install nfs-kernel-server nfs-client -y
echo "Sending MyVolume values to the VMs"
sudo echo "/var/lib/docker/volumes/myvolume/_data *(rw,sync,subtree_check)" >> /etc/exports
sudo exportfs -ar

echo "Verifying if the NFS is stablished with showmount and mount ..."
sudo showmount -e 192.168.1.100
echo "Mounting the volume ... and repeating to the nodes ..."
sudo mount 192.168.1.100:/var/lib/docker/volumes/myvolume/_data /var/lib/docker/volumes/myvolume/_data

echo "Ending of service.sh ..."
