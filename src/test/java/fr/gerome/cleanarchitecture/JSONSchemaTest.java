package fr.gerome.cleanarchitecture;

import com.networknt.schema.Error;
import com.networknt.schema.ExecutionConfig;
import com.networknt.schema.Schema;
import com.networknt.schema.SchemaLocation;
import com.networknt.schema.SchemaRegistry;
import com.networknt.schema.dialect.Dialects;
import jakarta.validation.ValidationException;
import org.junit.jupiter.api.Test;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.json.JsonMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class JSONSchemaTest {

    private final JsonMapper mapper = new JsonMapper();

    @Test
    public void givenInvalidInput_whenValidating_thenInvalid() throws IOException {
        String v1 = mapper.readTree(
                JSONSchemaTest.class.getResourceAsStream("/schema.json")).toString();
        SchemaRegistry schemaRegistry = SchemaRegistry.withDialect(Dialects.getDraft202012(),
                builder -> builder.schemas(Map.of("/schema.json", v1)));
        Schema schema = schemaRegistry.getSchema(SchemaLocation.of("/schema.json"));
        ExecutionConfig executionConfig = new ExecutionConfig.Builder().locale(Locale.ENGLISH).build();
        JsonNode jsonNode = mapper.readTree(
                JSONSchemaTest.class.getResourceAsStream("/product_invalid.json"));
        List<Error> errors = schema.validate(jsonNode, executionContext -> executionContext.setExecutionConfig(executionConfig));
        assertThat(errors).isNotEmpty().asString().contains("price: must have an exclusive minimum value of 0");
    }

    @Test
    public void givenValidInput_whenValidating_thenValid() throws ValidationException, IOException {
        String v1 = mapper.readTree(
                JSONSchemaTest.class.getResourceAsStream("/schema.json")).toString();
        SchemaRegistry schemaRegistry = SchemaRegistry.withDialect(Dialects.getDraft202012(),
                builder -> builder.schemas(Map.of("/schema.json", v1)));
        Schema schema = schemaRegistry.getSchema(SchemaLocation.of("/schema.json"));
        ExecutionConfig executionConfig = new ExecutionConfig.Builder().locale(Locale.ENGLISH).build();
        JsonNode jsonNode = mapper.readTree(
                JSONSchemaTest.class.getResourceAsStream("/product_valid.json"));
        List<Error> errors = schema.validate(jsonNode, executionContext -> executionContext.setExecutionConfig(executionConfig));
        assertThat(errors).isEmpty();
    }

}
