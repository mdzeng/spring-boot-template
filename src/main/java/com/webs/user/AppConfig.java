package com.webs.user;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Set;

/**
 * Generic configuration for the application
 */
@Component
@ConfigurationProperties(prefix = "service")
public class AppConfig {
    private HashMap<String, String> partners;

    public Set<String> getPartners() {
        return partners.keySet();
    }

    public String getPartnerSecret(String partnerName) {
        return partners.get(partnerName);
    }
}
