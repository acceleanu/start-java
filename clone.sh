#!/bin/bash

if [ $# -le 0 ]; then
    echo "Please provide new repo name"
    exit 1
fi

dn=$(dirname $0)
cd $dn
this_project_name="$(basename $(pwd))" 
new_project_path="$1"
 
if [ "$this_project_name" == "$new_project_path" ]; then
    echo "Can not clone to itself - please provide another name"
    exit 1
fi

if [ -f "$new_project_path" ]; then
    echo "A file already exists with name='${new_project_path}'; please choose another name" 
    exit 1
fi

if [ -d "$new_project_path" ]; then
    echo "A directory already exists with name='${new_project_path}'; please choose another name" 
    exit 1
fi

echo "Cloning to ${new_project_path}"


mkdir -p "$new_project_path"

shopt -s dotglob
cp -r * "$new_project_path"

