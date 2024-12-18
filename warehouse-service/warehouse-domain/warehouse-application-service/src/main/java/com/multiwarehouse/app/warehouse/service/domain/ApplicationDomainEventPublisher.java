package com.multiwarehouse.app.warehouse.service.domain;

import com.multiwarehouse.app.domain.event.publisher.DomainEventPublisher;
import com.multiwarehouse.app.warehouse.service.domain.event.WarehouseEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

//@Slf4j
//@Component
public class ApplicationDomainEventPublisher  { //implements ApplicationEventPublisherAware, DomainEventPublisher<WarehouseEvent> {

//    private ApplicationEventPublisher applicationEventPublisher;
//
//    @Override
//    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
//        this.applicationEventPublisher = applicationEventPublisher;
//    }
//
//    @Override
//    public void publish(WarehouseEvent domainEvent) {
//        this.applicationEventPublisher.publishEvent(domainEvent);
//        log.info("WarehouseEvent is published for warehouse id: {}", domainEvent.getWarehouse().getId().getValue());
//    }
}
