package io.github.julianrueck.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("multitenancy")
public class MultitenancyProperties {

    public enum implementationType {
        DATABASE_PER_TENANT,
        SHARED_SCHEMA
    }

    public enum interceptorType {
        HEADER,
        JWT
    }

    private implementationType implementation;
    private interceptorType interceptor;

    public implementationType getImplementation(){
        return implementation;
    }

    public void setImplementation(implementationType implementation){
        this.implementation = implementation;
    }

    public interceptorType getInterceptor(){
        return interceptor;
    }

    public void setInterceptor(interceptorType interceptor){
        this.interceptor = interceptor;
    }
}
