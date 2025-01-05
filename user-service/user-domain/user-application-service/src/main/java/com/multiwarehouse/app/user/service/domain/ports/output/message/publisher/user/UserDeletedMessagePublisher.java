package com.multiwarehouse.app.user.service.domain.ports.output.message.publisher.user;

import com.multiwarehouse.app.domain.event.publisher.DomainEventPublisher;
import com.multiwarehouse.app.user.service.domain.event.UserDeletedEvent;

public interface UserDeletedMessagePublisher extends DomainEventPublisher<UserDeletedEvent> {

}
