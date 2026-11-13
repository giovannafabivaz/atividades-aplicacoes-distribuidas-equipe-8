#!/usr/bin/env bash
# Faz N requisições ao Service e mostra qual pod respondeu cada uma.
set -euo pipefail
N="${1:-8}"
URL="$(minikube service hello --url)"
echo "Service em: $URL"
for i in $(seq "$N"); do
  curl -s "$URL"
  sleep 0.3
done
