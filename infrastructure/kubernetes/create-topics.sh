#local: sh create-topics.sh local-confluent-kafka-cp-zookeeper-headless
#GCloud: sh create-topics.sh gke-confluent-kafka-cp-zookeeper-headless

kafka-topics --zookeeper $1:2181 --topic warehouse-change --delete --if-exists
kafka-topics --zookeeper $1:2181 --topic product-change --delete --if-exists
kafka-topics --zookeeper $1:2181 --topic user-change --delete --if-exists
kafka-topics --zookeeper $1:2181 --topic inventory-change --delete --if-exists

kafka-topics --zookeeper $1:2181 --topic warehouse-change --create --partitions 3 --replication-factor 3 --if-not-exists
kafka-topics --zookeeper $1:2181 --topic product-change --create --partitions 3 --replication-factor 3 --if-not-exists
kafka-topics --zookeeper $1:2181 --topic user-change --create --partitions 3 --replication-factor 3 --if-not-exists
kafka-topics --zookeeper $1:2181 --topic inventory-change --create --partitions 3 --replication-factor 3 --if-not-exists

kafka-topics --zookeeper $1:2181 --list
