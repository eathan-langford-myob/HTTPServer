echo "-----------------DEPLOYING TO JUPITER-----------------"
ktmpl ./ops/deploy/deployment.yml -f ./ops/deploy/default.yml -p imageTag ${BUILDKITE_BUILD_NUMBER}
echo "-----------------READINESS-----------------"
kubectl rollout status /eathan-hello-world-deploy timeout--60s