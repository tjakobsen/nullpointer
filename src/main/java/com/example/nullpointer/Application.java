package com.example.nullpointer;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.kernel.impl.util.FileUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.*;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.config.Neo4jConfiguration;

import java.io.File;

public class Application extends SpringBootServletInitializer {

    protected static final String DB_NAME = "test.db";

    public static void main(String[] args) throws Exception {
        FileUtils.deleteRecursively(new File("target/" + DB_NAME));

        SpringApplication.run(WebConfig.class, args);
    }

    @Configuration
    @EnableAutoConfiguration
    @EnableNeo4jRepositories("com.example.nullpointer.repositories")
    static class Neo4jConfig extends Neo4jConfiguration {

        public Neo4jConfig() {
            setBasePackage("com.example.nullpointer.domain");
        }

        @Bean(destroyMethod = "shutdown")
        public GraphDatabaseService graphDatabaseService() {
            return new GraphDatabaseFactory().newEmbeddedDatabase("target/" + DB_NAME);
        }
    }

    @Configuration
    @ComponentScan(basePackages = "com.example.nullpointer")
    @Import({ Neo4jConfig.class })
    static class WebConfig {

    }

}
