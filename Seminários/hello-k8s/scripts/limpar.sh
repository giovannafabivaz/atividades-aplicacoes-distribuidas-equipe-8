#!/usr/bin/env bash
# Desfaz tudo depois da apresentação.
kubectl delete -f k8s/ --ignore-not-found
docker rm -f hello 2>/dev/null || true
minikube stop
