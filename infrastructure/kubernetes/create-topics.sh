#local: sh create-topics.sh local-confluent-kafka-cp-zookeeper-headless
#GCloud: sh create-topics.sh gke-confluent-kafka-cp-zookeeper-headless

kafka-topics --zookeeper $1:2181 --topic warehouse-create --delete --if-exists
kafka-topics --zookeeper $1:2181 --topic user-create --delete --if-exists
kafka-topics --zookeeper $1:2181 --topic user-assign-request --delete --if-exists
kafka-topics --zookeeper $1:2181 --topic user-assign-response --delete --if-exists

kafka-topics --zookeeper $1:2181 --topic warehouse-create --create --partitions 3 --replication-factor 3 --if-not-exists
kafka-topics --zookeeper $1:2181 --topic user-create --create --partitions 3 --replication-factor 3 --if-not-exists
kafka-topics --zookeeper $1:2181 --topic user-assign-request --create --partitions 3 --replication-factor 3 --if-not-exists
kafka-topics --zookeeper $1:2181 --topic user-assign-response --create --partitions 3 --replication-factor 3 --if-not-exists

kafka-topics --zookeeper gke-confluent-kafka-cp-zookeeper-headless:2181 --list
