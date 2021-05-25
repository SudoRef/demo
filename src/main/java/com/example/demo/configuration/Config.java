package com.example.demo.configuration;


import com.datastax.driver.core.Session;
import com.example.demo.CassandraConnector;
import com.example.demo.repository.KeyspaceRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Value("${dbhost}")
    private String node;
    @Value("${dbport}")
    private Integer port;

    @Bean
    public Session session(CassandraConnector connector) {
        connector.connect(node, port);
        return connector.getSession();
    }

    @Bean
    public CassandraConnector connector() {
        return new CassandraConnector();
    }

    @Bean
    KeyspaceRepository keyspaceRepository(Session session){
        KeyspaceRepository keyspaceRepository = new KeyspaceRepository(session);
        keyspaceRepository.useKeyspace("users");
        return keyspaceRepository;
    }

}
