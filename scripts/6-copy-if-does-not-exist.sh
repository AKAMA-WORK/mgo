#!/bin/sh
# This script is used by gitlab ci/cd

user=$1
password=$2
host=$3
port=$4
source_path=$5
dest_path=$6 # relative to user home directory (~)

if sshpass -p ${password} ssh -o StrictHostKeyChecking=no -o PreferredAuthentications=password -o PubkeyAuthentication=no ${user}@${host} "test -f ~/$dest_path"
then
    echo "File $dest_path exists."
else
    echo "File $dest_path does not exist."
    sshpass -p ${password} scp -P ${port} -o StrictHostKeyChecking=no -o PreferredAuthentications=password -o PubkeyAuthentication=no $source_path ${user}@${host}:~/$dest_path
fi