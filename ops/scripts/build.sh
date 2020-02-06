echo "---------------------GETTING AWS AUTH---------------------"
$(aws ecr get-login --no-include-email --region ap-southeast-2)
echo "---------------------BUILDING IMAGE---------------------"
docker build -t eathan-hello-world:${BUILDKITE_BUILD_NUMBER} . -f ./ops/dockerFiles/Dockerfile.build
echo "---------------------TAGGING IMAGE---------------------"
docker tag eathan-hello-world:${BUILDKITE_BUILD_NUMBER} 945367126992.dkr.ecr.ap-southeast-2.amazonaws.com/eathan-hello-world
echo "---------------------PUSHING IMAGE---------------------"
docker push 945367126992.dkr.ecr.ap-southeast-2.amazonaws.com/eathan-hello-world:${BUILDKITE_BUILD_NUMBER}