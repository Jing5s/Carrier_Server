package org.example.carrier.global.config;

import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;

@ConfigurationPropertiesScan(basePackages = {"org.example.carrier.global.config.properties"})
@Configuration
public class PropertiesConfig {
}
