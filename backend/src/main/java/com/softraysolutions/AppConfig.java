package com.softraysolutions;

import com.softraysolutions.api.filters.CORSFilter;
import org.glassfish.jersey.CommonProperties;
import org.glassfish.jersey.server.ResourceConfig;
import com.softraysolutions.api.filters.AuthenticationFilter;

import org.glassfish.jersey.moxy.json.MoxyJsonConfig;
import org.glassfish.jersey.moxy.json.MoxyJsonFeature;
import javax.ws.rs.ext.ContextResolver;

import org.eclipse.jetty.servlets.CrossOriginFilter;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/")
public class AppConfig extends ResourceConfig {
    final MoxyJsonConfig moxyJsonConfig = new MoxyJsonConfig();
    final ContextResolver jsonConfigResolver = moxyJsonConfig.resolver();

    public AppConfig() {
        property(CommonProperties.FEATURE_AUTO_DISCOVERY_DISABLE, true);

        register(MoxyJsonFeature.class);
        register(jsonConfigResolver);

        register(CrossOriginFilter.class);

        register(CORSFilter.class);
        register(AuthenticationFilter.class);

        InitAdmin.init();
    }
}
