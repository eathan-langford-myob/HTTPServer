echo "---------------------STARTING CONTAINER---------------------"
buildkite-agent artifact download *.jar tmp/
docker build -t eathan-hello-world . -f ./ops/dockerFiles/Dockerfile
