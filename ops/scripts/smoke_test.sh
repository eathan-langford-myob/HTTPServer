curl -s -o /dev/null https://eathan-hello-world.svc.platform.myobdev.com/
exit_code=$?
#---------TEST EXIT CODE----------
if test $exit_code -ne 0
then
    exit 1
else
    exit 0
fi