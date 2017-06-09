package broker;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.cloud.servicebroker.model.Catalog;
import org.springframework.cloud.servicebroker.model.Plan;
import org.springframework.cloud.servicebroker.model.Schemas;
import org.springframework.cloud.servicebroker.model.ServiceInstanceSchema;
import org.springframework.cloud.servicebroker.model.MethodSchema;
import org.springframework.cloud.servicebroker.model.ServiceDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CatalogConfig {

    @Bean
    public Catalog catalog() {
        final Plan plan = new Plan("spring-plan",
                "default",
                "This is a default broker plan.  All services are created equally.",
                new HashMap<>(),
                false,
                false,
                getSchemaFromModel());

        return new Catalog(Collections.singletonList(
                new ServiceDefinition(
                        "spring-service-broker",
                        "schema-broker",
                        "A spring broker with schemas",
                        true,
                        false,
                        Collections.singletonList(plan),
                        Arrays.asList("broker" ),
                        new HashMap<>(),
                        null,
                        null)
        ));
    }

    private Schemas getSchemaFromHash() {
        final Map<String,Object> schema = new HashMap<>();
        schema.put("$schema", "http://example.com/schema");
        schema.put("type", "object");

        final MethodSchema create = new MethodSchema(schema);
        final ServiceInstanceSchema instance = new ServiceInstanceSchema(create, null);
        final Schemas schemas = new Schemas(instance, null);
        return schemas;
    }

    private Schemas getSchemaFromModel() {
        final Schema schema = new Schema("http://example.com/schema", "object");

        final ObjectMapper mapper = new ObjectMapper();
        final Map<String,Object> schemaMap = mapper.convertValue(schema, HashMap.class);

        final MethodSchema create = new MethodSchema(schemaMap);
        final ServiceInstanceSchema instance = new ServiceInstanceSchema(create, null);
        final Schemas schemas = new Schemas(instance, null);
        return schemas;
    }

    private Schemas getSchemaFromFile() {
        final ObjectMapper mapper = new ObjectMapper();

        Map<String,Object> schemaMap = new HashMap<>();
        try {
            schemaMap = mapper.readValue(new File("schema.json"), HashMap.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        final MethodSchema create = new MethodSchema(schemaMap);
        final ServiceInstanceSchema instance = new ServiceInstanceSchema(create, null);
        final Schemas schemas = new Schemas(instance, null);
        return schemas;
    }

    @JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NONE)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Schema {

        @JsonSerialize()
        private final String schema;

        @JsonSerialize()
        private final String type;

        public Schema(String schema, String type) {
            this.schema = schema;
            this.type = type;
        }
    }
}