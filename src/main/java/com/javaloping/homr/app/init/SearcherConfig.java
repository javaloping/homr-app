package com.javaloping.homr.app.init;

import com.javaloping.homr.app.search.ElasticConfig;
import com.javaloping.homr.app.search.ElasticPropertySearcher;
import com.javaloping.homr.app.search.PropertySearcher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author victormiranda@gmail.com
 */

@Configuration
public class SearcherConfig {

    @Value("${search.name}")
    private String indexName;

    @Value("${search.property}")
    private String propertyName;

    @Value("${search.host}")
    private String host;

    @Value("${search.port}")
    private Integer port;

    @Bean
    public PropertySearcher propertySearcher() {
        return new ElasticPropertySearcher(getElasticConfig());
    }

    private ElasticConfig getElasticConfig() {
        final ElasticConfig elasticConfig = new ElasticConfig();

        elasticConfig.setHost(host);
        elasticConfig.setPort(port);
        elasticConfig.setObjectName(propertyName);
        elasticConfig.setIndexName(indexName);

        return elasticConfig;
    }

}
