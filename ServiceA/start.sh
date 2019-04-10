#!/bin/sh
docker run -p 8081:8081  -e spring.profiles.active=dev -d  service-a:1.0