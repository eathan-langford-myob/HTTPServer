$(aws ecr get-login --no-include-email --region ap-southeast-2)
docker pull 945367126992.dkr.ecr.ap-southeast-2.amazonaws.com/eathan-hello-world:latest
docker run -p 3333:8080 eathan-hello-world:latest