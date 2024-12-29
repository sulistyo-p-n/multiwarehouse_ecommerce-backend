package com.multiwarehouse.app.user.service.domain.ports.output.message.publsher.user;

import com.multiwarehouse.app.domain.event.publisher.DomainEventPublisher;
import com.multiwarehouse.app.user.service.domain.event.UserUpdatedEvent;

public interface UserUpdatedMessagePublisher extends DomainEventPublisher<UserUpdatedEvent> {

}
