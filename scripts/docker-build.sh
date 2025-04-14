#!/usr/bin/env bash
export DOCKER_CLI_EXPERIMENTAL=enabled

build () {
  if ! docker buildx build --build-arg JAR="${JAR}" --push --platform linux/arm64/v8,linux/amd64 --tag "${DOCKER_USER}/sic:${1}" .; then
    echo "Building tag ${1} failed miserably."
    exit 1
  fi
}

DATE=$( date '+%y%m%d.%H.%M.%S' )

echo "Pushing docker image version ${DATE} and tagging latest"
#Get the latest .jar
JAR="/tmp/workspace/target/sharing-is-caring-0.0.1-SNAPSHOT.jar"

#Login
echo "${DOCKER_PASSWORD}" | docker login --username $DOCKER_USER --password-stdin

#Build for all archs
docker context create buildx-build
docker buildx create --use buildx-build

build "latest"
build "${DATE}"
