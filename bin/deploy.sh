#!/usr/bin/env bash

set -eu

java -jar build/libs/spring-boot-liquibase-template-0.1.0.jar "$@"
