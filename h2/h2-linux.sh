#!/bin/bash

# ports
web_port=8082
db_port=9092

# location of H2 database files
dbhome="${HOME}/h2-dbs"

# escape spaces in home folder name
dbhome=$(echo "$dbhome" | tr " " "\\ ")

# create dbhome if it doesn't already exist
if [ ! -d "$dbhome" ]; then mkdir "$dbhome"; fi

# MacOS does not execute scripts from the location they reside, so CD into the correct directory
cd $(dirname "$0")

# start H2
java -cp h2-1.4.197.jar -Duser.home="$dbhome" -Dh2.baseDir="$dbhome" -Dh2.bindAddress=localhost org.h2.tools.Console -tcp -tcpPort $db_port -web -webPort $web_port -tool
