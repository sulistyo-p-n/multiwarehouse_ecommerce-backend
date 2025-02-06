#!/bin/bash

#ANSI color codes
GREEN='\033[0;32m'
NC='\033[0m'

#cd infrastructure/docker-compose

echo -e "${GREEN}Start Docker Compose...${NC}"

echo -e "${GREEN}Start Zookeeper...${NC}"
docker-compose -f common.yml -f zookeeper.yml up

echo -e "${GREEN}Start Kafka Cluster...${NC}"
docker-compose -f common.yml -f kafka_cluster.yml up

echo -e "${GREEN}Start Kafka Init...${NC}"
docker-compose -f common.yml -f init_kafka.yml up
