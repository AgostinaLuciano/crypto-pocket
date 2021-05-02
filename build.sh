#!/bin/bash
mvn package
docker build -t agostinaluciano/crypto-pocket .
