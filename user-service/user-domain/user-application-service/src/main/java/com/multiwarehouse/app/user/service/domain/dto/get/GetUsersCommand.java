package com.multiwarehouse.app.user.service.domain.dto.get;

import com.multiwarehouse.app.domain.valueobject.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class GetUsersCommand {
    private final Boolean withInactive;
    private final Boolean withTrashed;
    private final UserRole userRole;
    private final String search;
}
