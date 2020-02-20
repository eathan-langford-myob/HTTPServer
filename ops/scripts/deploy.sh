#!/usr/bin/env bash
echo "-----------------DEPLOYING TO JUPITER-----------------"
ktmpl ./ops/deploy/deployment.yml -f ./ops/deploy/default.yml -p imageTag ${BUILDKITE_BUILD_NUMBER} | kubectl apply -f -
echo "-----------------ROLLOUT STATUS CONFIRMATION----------------"
kubectl rollout status deployment.v1.apps/eathan-hello-world-deploy --timeout=60s -n fma