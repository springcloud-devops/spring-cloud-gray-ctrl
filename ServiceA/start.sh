#!/bin/sh
docker run -p 8081:8081  -e spring.profiles.active=dev -d  serviceA:1.0