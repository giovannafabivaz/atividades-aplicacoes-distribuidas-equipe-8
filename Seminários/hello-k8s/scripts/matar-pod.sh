#!/usr/bin/env bash
# Apaga um pod do Deployment para mostrar a autocura.
set -euo pipefail
POD="$(kubectl get pods -l app=hello -o jsonpath='{.items[0].metadata.name}')"
echo "Apagando o pod: $POD"
kubectl delete pod "$POD" --wait=false
