#!/bin/sh
docker run -p 8084:8084 -e spring.profiles.active=dev -d  service-c:1.0