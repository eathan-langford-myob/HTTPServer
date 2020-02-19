#!/usr/bin/env bash
status_code=$(curl -s -o /dev/null -w "%{http_code}" https://eathan-hello-world.svc.platform.myobdev.com/)
#---------TEST EXIT CODE----------
if [[ "$status_code" -ne 200 ]] ; then
  exit 1
else
  exit 0
fi