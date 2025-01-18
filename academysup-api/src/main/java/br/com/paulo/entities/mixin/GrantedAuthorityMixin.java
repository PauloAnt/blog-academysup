package br.com.paulo.entities.mixin;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public abstract class GrantedAuthorityMixin implements GrantedAuthority {
}