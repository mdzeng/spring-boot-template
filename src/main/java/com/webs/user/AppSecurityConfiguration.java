package com.webs.user;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Generic configuration for the application
 */
@Component
@ConfigurationProperties(prefix = "app.security")
public class AppSecurityConfiguration {
    private HashMap<String, String> partners;

    private List<String> secureRoutes;

    public List<String> getSecureRoutes() {
        return secureRoutes;
    }

    public void setSecureRoutes(List<String> secureRoutes) {
        this.secureRoutes = secureRoutes;
    }

    public boolean hasPartner(String partnerName) {
        return partners.containsKey(partnerName);
    }

    public Set<String> getPartnerKeys() {
        return partners.keySet();
    }

    public String getPartnerSecret(String partnerName) {
        return partners.get(partnerName);
    }

    public HashMap<String, String> getPartners() {
        return partners;
    }

    public void setPartners(HashMap<String, String> partners) {
        this.partners = partners;
    }
}
