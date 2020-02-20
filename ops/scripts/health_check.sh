#!/usr/bin/env bash
status_code=$(curl -s -o /dev/null -w "%{http_code}" https://eathan-hello-world.svc.platform.myobdev.com/)
#---------TEST EXIT CODE----------
if [[ "$status_code" -ne 200 ]] ; then
echo "RECEIVED HTTP STATUS CODE $status_code"
  exit 1
else
echo "SUCCESSFULLY RECEIVED 200"
  exit 0
fi