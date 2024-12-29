package com.multiwarehouse.app.user.service.domain.dto.get;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class GetUsersResponse {
    private final List<GetUserResponse> users;
}
