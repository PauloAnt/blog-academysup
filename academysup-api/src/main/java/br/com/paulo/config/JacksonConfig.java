package br.com.paulo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.security.core.GrantedAuthority;

import br.com.paulo.entities.Permission;
import br.com.paulo.entities.mixin.GrantedAuthorityMixin;
import br.com.paulo.entities.mixin.PermissionMixin;

@Configuration
public class JacksonConfig {

    @Bean
    Jackson2ObjectMapperBuilder jacksonBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.mixIn(GrantedAuthority.class, GrantedAuthorityMixin.class);
        builder.mixIn(Permission.class, PermissionMixin.class);
        return builder;
    }
}
