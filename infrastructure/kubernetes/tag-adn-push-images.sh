gcloud auth login

docker tag com.multiwarehouse.app/warehouse.service:1.0-SNAPSHOT asia-southeast2-docker.pkg.dev/long-canto-439612-d1/com-multiwarehouse-app-repository/warehouse.service:1.0-SNAPSHOT
docker push asia-southeast2-docker.pkg.dev/long-canto-439612-d1/com-multiwarehouse-app-repository/warehouse.service:1.0-SNAPSHOT

docker tag com.multiwarehouse.app/user.service:1.0-SNAPSHOT asia-southeast2-docker.pkg.dev/long-canto-439612-d1/com-multiwarehouse-app-repository/user.service:1.0-SNAPSHOT
docker push asia-southeast2-docker.pkg.dev/long-canto-439612-d1/com-multiwarehouse-app-repository/user.service:1.0-SNAPSHOT
