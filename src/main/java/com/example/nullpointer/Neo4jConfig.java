package com.example.nullpointer;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.aspects.support.node.Neo4jNodeBacking;
import org.springframework.data.neo4j.aspects.support.relationship.Neo4jRelationshipBacking;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.support.node.NodeEntityStateFactory;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableNeo4jRepositories("com.example.nullpointer.repositories")
@EnableTransactionManagement
public class Neo4jConfig extends Neo4jConfiguration {

    protected static final String DB_NAME = "test.db";

    public Neo4jConfig() {
        setBasePackage("com.example.nullpointer.domain");
    }

    @Bean(destroyMethod = "shutdown")
    public GraphDatabaseService graphDatabaseService() {
        return new GraphDatabaseFactory().newEmbeddedDatabase("target/" + DB_NAME);
    }

    @Bean
    public Neo4jRelationshipBacking neo4jRelationshipBacking() throws Exception {
        Neo4jRelationshipBacking aspect = Neo4jRelationshipBacking.aspectOf();
        aspect.setTemplate(neo4jTemplate());
        aspect.setRelationshipEntityStateFactory(relationshipEntityStateFactory());
        return aspect;
    }

    @Bean
    public Neo4jNodeBacking neo4jNodeBacking() throws Exception {
        Neo4jNodeBacking aspect = Neo4jNodeBacking.aspectOf();
        aspect.setTemplate(neo4jTemplate());
        NodeEntityStateFactory entityStateFactory = nodeEntityStateFactory();
        aspect.setNodeEntityStateFactory(entityStateFactory);
        return aspect;
    }
}
