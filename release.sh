#!/bin/bash

dn=$(dirname $0)
cd $dn
PROJECT_NAME="$(basename $(pwd))"

git clean -fdx

echo $PROJECT_NAME

DATE=$(date +%Y%m%d_%H%M%S)
PKG_NAME="${PROJECT_NAME}-${DATE}"

cd ..

tar -zcvf "$PKG_NAME.tgz" "$PROJECT_NAME"

cd - > /dev/null

