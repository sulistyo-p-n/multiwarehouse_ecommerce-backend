package com.multiwarehouse.app.warehouse.service.dataaccess.user.config;

import com.multiwarehouse.app.domain.valueobject.UserRole;
import com.multiwarehouse.app.warehouse.service.dataaccess.user.entity.UserEntity;
import com.multiwarehouse.app.warehouse.service.dataaccess.user.repository.UserJpaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.UUID;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner(
            UserJpaRepository userJpaRepository
    ) {
        return args -> {
            UserEntity user01 = UserEntity.builder()
                    .id(UUID.fromString("d403a5bf-7750-4dc4-bfea-ee80dffc3e01"))
                    .role(UserRole.SUPER_ADMIN)
                    .build();

            UserEntity user02 = UserEntity.builder()
                    .id(UUID.fromString("d403a5bf-7750-4dc4-bfea-ee80dffc3e02"))
                    .role(UserRole.WAREHOUSE_ADMIN)
                    .build();

            UserEntity user03 = UserEntity.builder()
                    .id(UUID.fromString("d403a5bf-7750-4dc4-bfea-ee80dffc3e03"))
                    .role(UserRole.CUSTOMER)
                    .build();

            userJpaRepository.saveAll(
                    List.of(user01, user02, user03)
            );
        };
    }
}
