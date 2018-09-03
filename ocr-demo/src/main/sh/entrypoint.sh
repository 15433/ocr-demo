#!/bin/bash

sysctl -w fs.aio-max-nr=1048576
sysctl -w fs.file-max=6815744
sysctl -w kernel.shmall=6815744
sysctl -w kernel.shmmax=6815744
sysctl -w kernel.shmmni=6815744
sysctl -w kernel.sem=250 32000 100 128
sysctl -w net.ipv4.ip_local_port_range=9000 65500
sysctl -w net.core.rmem_default=262144
sysctl -w net.core.rmem_max=6815744
sysctl -w net.core.wmem_default=262144
sysctl -w net.core.wmem_max=6815744
sysctl -w net.ipv4.tcp_keepalive_time=30
sysctl -w net.ipv4.tcp_keepalive_intvl=1
sysctl -w net.ipv4.tcp_keepalive_probes=10