echo "BUILDING IMAGE"
docker build -t eathan-hello-world . -f ./ops/dockerFiles/Dockerfile.build
docker tag eathan-hello-world:latest 945367126992.dkr.ecr.ap-southeast-2.amazonaws.com/eathan-hello-world
docker push 945367126992.dkr.ecr.ap-southeast-2.amazonaws.com/eathan-hello-world:latest