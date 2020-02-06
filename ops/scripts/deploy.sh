echo "-----------------DEPLOYING TO JUPITER-----------------"
ktmpl ./ops/deploy/deployment.yml -f ./ops/deploy/default.yml -p imageTag ${BUILDKITE_BUILD_NUMBER} | kubectl apply -f -
