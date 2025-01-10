#gcloud auth login
#Local: sh tag-and-push-images.sh multiwarehouse-447401

docker tag com.multiwarehouse.app/warehouse.service:1.0-SNAPSHOT asia-southeast2-docker.pkg.dev/$1/com-multiwarehouse-app-repository/warehouse.service:1.0-SNAPSHOT
docker push asia-southeast2-docker.pkg.dev/$1/com-multiwarehouse-app-repository/warehouse.service:1.0-SNAPSHOT

docker tag com.multiwarehouse.app/user.service:1.0-SNAPSHOT asia-southeast2-docker.pkg.dev/$1/com-multiwarehouse-app-repository/user.service:1.0-SNAPSHOT
docker push asia-southeast2-docker.pkg.dev/$1/com-multiwarehouse-app-repository/user.service:1.0-SNAPSHOT

docker tag com.multiwarehouse.app/product.service:1.0-SNAPSHOT asia-southeast2-docker.pkg.dev/$1/com-multiwarehouse-app-repository/product.service:1.0-SNAPSHOT
docker push asia-southeast2-docker.pkg.dev/$1/com-multiwarehouse-app-repository/product.service:1.0-SNAPSHOT

docker tag com.multiwarehouse.app/inventory.service:1.0-SNAPSHOT asia-southeast2-docker.pkg.dev/$1/com-multiwarehouse-app-repository/inventory.service:1.0-SNAPSHOT
docker push asia-southeast2-docker.pkg.dev/$1/com-multiwarehouse-app-repository/inventory.service:1.0-SNAPSHOT

docker tag com.multiwarehouse.app/authgateway.service:1.0-SNAPSHOT asia-southeast2-docker.pkg.dev/$1/com-multiwarehouse-app-repository/authgateway.service:1.0-SNAPSHOT
docker push asia-southeast2-docker.pkg.dev/$1/com-multiwarehouse-app-repository/authgateway.service:1.0-SNAPSHOT
