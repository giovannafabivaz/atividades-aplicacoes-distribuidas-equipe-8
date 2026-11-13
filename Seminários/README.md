# Demo: Docker + Kubernetes no GitHub Codespaces

App Flask mínima que responde com o nome do pod que atendeu a requisição.
Usada para mostrar **autocura**, **escala** e **balanceamento** no Kubernetes (minikube).

## Estrutura

```
.devcontainer/devcontainer.json   # Codespace com Docker, kubectl e minikube
app.py                            # a aplicação
requirements.txt
Dockerfile
k8s/deployment.yaml               # 3 réplicas (estado desejado)
k8s/service.yaml                  # endereço fixo + balanceamento
scripts/preparar.sh               # rodar ANTES de apresentar
scripts/curl-loop.sh              # várias requisições seguidas
scripts/matar-pod.sh              # apaga um pod (autocura)
scripts/limpar.sh                 # desfaz tudo no final
```

## 1. Configurar (uma vez só, dias antes)

1. Crie um repositório no GitHub e suba todos estes arquivos, **incluindo a pasta oculta `.devcontainer`**.
2. No repositório: **Code › Codespaces › ⋯ › New with options**, escolha a máquina de **4 cores** e crie.
3. Espere o Codespace terminar de montar (alguns minutos na primeira vez).
4. Faça um ensaio completo seguindo os passos 2 e 3 abaixo.

## 2. Preparar (10 a 15 minutos antes de apresentar)

Abra o Codespace e rode:

```bash
./scripts/preparar.sh
```

Isso sobe o minikube, constrói a imagem e a carrega no cluster. Deixe **dois terminais** abertos
(botão `+` no painel do terminal) e aumente a fonte (`Ctrl` + `+`).

No **terminal 2**, deixe rodando durante a demo:

```bash
kubectl get pods -w
```

## 3. Roteiro ao vivo (terminal 1)

**Slide 6 · Docker** (opcional, se tiver tempo):

```bash
docker run -d -p 5000:5000 --name hello hello-k8s:1.0
curl localhost:5000
docker rm -f hello
```

**Slide 7 · Kubernetes:**

```bash
# 1. Declarar o estado desejado: 3 pods + Service
kubectl apply -f k8s/

# 2. Autocura: apague um pod e veja um novo surgir no terminal 2
kubectl delete pod <nome-do-pod>        # ou: ./scripts/matar-pod.sh

# 3. Escala: de 3 para 5 réplicas
kubectl scale deployment hello --replicas=5

# 4. Balanceamento: cada resposta vem de um pod diferente
./scripts/curl-loop.sh
```

Dica: `kubectl get pods` lista os nomes; dê duplo clique para copiar um nome no passo 2.

## 4. Depois

```bash
./scripts/limpar.sh
```

E pare o Codespace (**Code › Codespaces › ⋯ › Stop**) para não consumir sua cota.

## Problemas comuns

| Sintoma | Solução |
|---|---|
| `minikube start` falha por CPU/memória | Recrie o Codespace com 4 cores, ou rode `minikube start --driver=docker --cpus=2 --memory=2g` |
| Pod em `ErrImagePull` / `ImagePullBackOff` | A imagem não foi carregada: rode `minikube image load hello-k8s:1.0` |
| Codespace parou sozinho | Ficou ocioso (padrão: 30 min). Reabra e rode `./scripts/preparar.sh` de novo |
| `minikube service` demora | Normal na primeira chamada; o `curl-loop.sh` guarda a URL e reaproveita |
| Sem internet no local | Tenha um vídeo curto gravado do ensaio como plano B |
