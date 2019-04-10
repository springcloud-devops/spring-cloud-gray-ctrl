#!/bin/sh
docker run -p 8083:8083 -e spring.profiles.active=dev -d  service-b:1.0