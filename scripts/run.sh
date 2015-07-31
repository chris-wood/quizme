#!/bin/sh

if [ "$#" -eq 0 ] || [[ "$1" == "-h" ]]; then
    echo
    echo "Usage: runner.sh <command> <sleeptime>"
    echo
    echo "This will run <command> every <sleeptime> seconds."
    echo
    exit 2
fi

while [ true ]
do
    $1   
    sleep $2
done
