minikube start --nodes 2 -p multinode
kubectl apply -f mysql-secret.yml
kubectl apply -f mysql-configmap.yml
kubectl apply -f service-registry.yml
kubectl apply -f comment-volume.yml
kubectl apply -f comment-db.yml
kubectl apply -f comment-app.yml
kubectl apply -f user-volume.yml
kubectl apply -f user-db.yml
kubectl apply -f user-app.yml
kubectl apply -f article-volume.yml
kubectl apply -f article-db.yml
kubectl apply -f article-app.yml
kubectl apply -f gateway.yml