#!/bin/bash

if [[ -z $1 || -z $2 ]];then
    echo 'both arguments are needed'
    exit
fi

rest_args=("${all_args[@]:2}")
find $1 -name '*.less' | while read name; do
    FROM=$(echo $name)
    TO=$(echo $2'/'$name | sed "s|\.less|\.css|")
    TO_DIRNAME=$(dirname $TO)
    if [ ! -e $TO_DIRNAME ];then
        mkdir -p $TO_DIRNAME
    fi
    echo 'Compiling' $FROM '->' $TO
    lessc --js $FROM $TO
done