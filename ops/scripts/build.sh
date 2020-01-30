echo "BUILDING IMAGE"
docker build -t eathan-hello-world . -f ./ops/dockerFiles/Dockerfile.build
