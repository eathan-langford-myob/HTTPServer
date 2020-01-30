echo "RUNNING TESTS"
docker build -t eathan-hello-world . -f ./ops/dockerFiles/Dockerfile.test
