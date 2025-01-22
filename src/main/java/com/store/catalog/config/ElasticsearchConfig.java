package com.store.catalog.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.SSLContext;

@Configuration
public class ElasticsearchConfig {

    @Value("${spring.elasticsearch.username}")
    private String elasticsearchUsername;

    @Value("${spring.elasticsearch.password}")
    private String elasticsearchPassword;

    @Bean
    public ElasticsearchClient elasticsearchClient() {
        try {
            // Trust all certificates
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new javax.net.ssl.TrustManager[]{new javax.net.ssl.X509TrustManager() {
                @Override
                public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                }

                @Override
                public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                }

                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return new java.security.cert.X509Certificate[0];
                }
            }}, new java.security.SecureRandom());

            // Set up basic authentication
            BasicCredentialsProvider credentialsProvider = new BasicCredentialsProvider();
            credentialsProvider.setCredentials(
                    new AuthScope("elasticsearch-master", 9200),
                    new UsernamePasswordCredentials(elasticsearchUsername, elasticsearchPassword)
            );

            // Build the RestClient
            RestClientBuilder builder = RestClient.builder(
                            new HttpHost("elasticsearch-master", 9200, "https"))
                    .setHttpClientConfigCallback(httpClientBuilder ->
                            httpClientBuilder
                                    .setSSLContext(sslContext)
                                    .setDefaultCredentialsProvider(credentialsProvider));

            // Wrap the RestClient with RestClientTransport
            RestClient restClient = builder.build();
            RestClientTransport transport = new RestClientTransport(
                    restClient,
                    new JacksonJsonpMapper()
            );

            // Return the ElasticsearchClient
            return new ElasticsearchClient(transport);

        } catch (Exception e) {
            throw new RuntimeException("Failed to create Elasticsearch client", e);
        }
    }
}
