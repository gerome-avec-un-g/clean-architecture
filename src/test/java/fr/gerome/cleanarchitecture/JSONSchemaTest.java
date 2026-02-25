package fr.gerome.cleanarchitecture;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.ExecutionConfig;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import jakarta.validation.ValidationException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Locale;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class JSONSchemaTest {

    private final ObjectMapper  mapper = new ObjectMapper();

    @Test
    public void givenInvalidInput_whenValidating_thenInvalid() throws IOException {
        JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
        JsonSchema jsonSchema = factory.getSchema(
                JSONSchemaTest.class.getResourceAsStream("/schema.json"));
        JsonNode jsonNode = mapper.readTree(
                JSONSchemaTest.class.getResourceAsStream("/product_invalid.json"));
            ExecutionConfig executionConfig = new ExecutionConfig();
            executionConfig.setLocale(Locale.ENGLISH);
        Set<ValidationMessage> errors = jsonSchema.validate(jsonNode,  executionContext -> {
            executionContext.setExecutionConfig(executionConfig);
        });
        assertThat(errors).isNotEmpty().asString().contains("price: must have an exclusive minimum value of 0");
    }

    @Test
    public void givenValidInput_whenValidating_thenValid() throws ValidationException, IOException {
        JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
        JsonSchema jsonSchema = factory.getSchema(
                JSONSchemaTest.class.getResourceAsStream("/schema.json"));
        JsonNode jsonNode = mapper.readTree(
                JSONSchemaTest.class.getResourceAsStream("/product_valid.json"));
        Set<ValidationMessage> errors = jsonSchema.validate(jsonNode);
        assertThat(errors).isEmpty();
    }

}
