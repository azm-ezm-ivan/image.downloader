#!/usr/bin/env bash

mvn clean package
sudo docker-compose up
