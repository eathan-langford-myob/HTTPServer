#BUILD_NUMBER=$1

echo "-----------------DEPLOYING TO JUPITER-----------------"
ktmpl ./ops/deploy/deployment.yml -f ./ops/deploy/default.yml -p imageTag latest | kubectl apply -f -
