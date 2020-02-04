BUILD_NUMBER=$1

echo "-----------------DEPLOYING TO JUPITER-----------------"
ktmpl ./ops/deploy/deployment.yaml -f ./ops/deploy/defaults.yaml --parameter imageTag $BUILD_NUMBER | kubectl apply -f -
