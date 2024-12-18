package com.multiwarehouse.app.user.service.messaging.listener.kafka.warehouse;

import com.multiwarehouse.app.kafka.consumer.KafkaConsumer;
import com.multiwarehouse.app.kafka.order.avro.model.WarehouseCreateAvroModel;
import com.multiwarehouse.app.user.service.messaging.mapper.UserMessagingDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class WarehouseCreateKafkaListener implements KafkaConsumer<WarehouseCreateAvroModel> {

//    private final WarehouseCreateMessageListener warehouseCreateMessageListener;
//    private final UserMessagingDataMapper userMessagingDataMapper;
//
//    public WarehouseCreateKafkaListener(WarehouseCreateMessageListener warehouseCreateMessageListener,
//                                        UserMessagingDataMapper userMessagingDataMapper) {
//        this.warehouseCreateMessageListener = warehouseCreateMessageListener;
//        this.userMessagingDataMapper = userMessagingDataMapper;
//    }

    public WarehouseCreateKafkaListener() {

    }

    @Override
    @KafkaListener(id = "${kafka-consumer-config.warehouse-create-consumer-group-id}", topics = "${user-service.warehouse-create-topic-name}")
    public void receive(
            @Payload List<WarehouseCreateAvroModel> messages,
            @Header(KafkaHeaders.RECEIVED_KEY) List<String> keys,
            @Header(KafkaHeaders.RECEIVED_PARTITION) List<Integer> partitions,
            @Header(KafkaHeaders.OFFSET) List<Long> offsets) {

        log.info("{} number of payment responses received with keys:{}, partitions:{} and offsets: {}",
                messages.size(),
                keys.toString(),
                partitions.toString(),
                offsets.toString());

        messages.forEach(warehouseCreateAvroModel -> {
            log.info("{} warehouseCreateAvroModel warehouseId:{}, name: {}, street: {}, city: {}, postal code: {}",
                    warehouseCreateAvroModel.getId(),
                    warehouseCreateAvroModel.getWarehouseId(),
                    warehouseCreateAvroModel.getName(),
                    warehouseCreateAvroModel.getAddressStreet(),
                    warehouseCreateAvroModel.getAddressCity(),
                    warehouseCreateAvroModel.getAddressPostalCode()
            );
//            if (PaymentStatus.COMPLETED == paymentResponseAvroModel.getPaymentStatus()) {
//                log.info("Processing successful payment for order id: {}", paymentResponseAvroModel.getOrderId());
//                paymentResponseMessageListener.paymentCompleted(orderMessagingDataMapper
//                        .paymentResponseAvroModelToPaymentResponse(paymentResponseAvroModel));
//            } else if (PaymentStatus.CANCELLED == paymentResponseAvroModel.getPaymentStatus() ||
//                    PaymentStatus.FAILED == paymentResponseAvroModel.getPaymentStatus()) {
//                log.info("Processing unsuccessful payment for order id: {}", paymentResponseAvroModel.getOrderId());
//                paymentResponseMessageListener.paymentCancelled(orderMessagingDataMapper
//                        .paymentResponseAvroModelToPaymentResponse(paymentResponseAvroModel));
//            }
        });
    }
}
