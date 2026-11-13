#!/usr/bin/env bash
# Rode ANTES da apresentação (leva 2 a 4 minutos).
set -euo pipefail
cd "$(dirname "$0")/.."

echo "==> 1/4 Subindo o cluster minikube"
minikube status >/dev/null 2>&1 || minikube start --driver=docker --cpus=2 --memory=3g

echo "==> 2/4 Construindo a imagem Docker"
docker build -t hello-k8s:1.0 .

echo "==> 3/4 Carregando a imagem no minikube"
minikube image load hello-k8s:1.0

echo "==> 4/4 Limpando execuções anteriores"
kubectl delete -f k8s/ --ignore-not-found >/dev/null
docker rm -f hello >/dev/null 2>&1 || true

echo
echo "Pronto! Cluster no ar, imagem carregada, nenhum pod rodando ainda."
kubectl get nodes
