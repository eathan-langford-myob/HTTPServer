tag=$1
ECR_URL="945367126992.dkr.ecr.ap-southeast-2.amazonaws.com"
ECR_Name="eathan-hello-world"
ECR="${ECR_URL}/${ECR_Name}"
Dockerfile="./ops/dockerFiles/Dockerfile.build"

echo "---------------------GETTING AWS AUTH---------------------"
$(aws ecr get-login --no-include-email --region ap-southeast-2)

echo "---------------------BUILDING IMAGE---------------------"
docker build -t "${ECR_Name}" . -f "${Dockerfile}"

echo "---------------------TAGGING IMAGE---------------------"
docker tag "${ECR}:${tag}" "${ECR_Name}"

echo "---------------------PUSHING IMAGE---------------------"
docker push "${ECR}:${tag}"