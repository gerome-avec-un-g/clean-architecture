package fr.gerome.cleanarchitecture;

import com.networknt.schema.Error;
import com.networknt.schema.ExecutionConfig;
import com.networknt.schema.Schema;
import com.networknt.schema.SchemaLocation;
import com.networknt.schema.SchemaRegistry;
import com.networknt.schema.dialect.Dialects;
import org.junit.jupiter.api.Test;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.json.JsonMapper;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

public class JSONSchemaTest {

    private final JsonMapper mapper = new JsonMapper();

    @Test
    public void givenInvalidInput_whenValidating_thenInvalid() {
        SchemaRegistry schemaRegistry = SchemaRegistry.withDialect(Dialects.getDraft202012());
        Schema schema = schemaRegistry.getSchema(SchemaLocation.of("classpath:schema.json"));
        ExecutionConfig executionConfig = new ExecutionConfig.Builder().locale(Locale.ENGLISH).build();
        JsonNode jsonNode = mapper.readTree(
                JSONSchemaTest.class.getResourceAsStream("/product_invalid.json"));
        List<Error> errors = schema.validate(jsonNode, executionContext -> executionContext.setExecutionConfig(executionConfig));
        assertThat(errors).isNotEmpty().asString().contains("price: must have an exclusive minimum value of 0");
    }

    @Test
    public void givenValidInput_whenValidating_thenValid() {
        SchemaRegistry schemaRegistry = SchemaRegistry.withDialect(Dialects.getDraft202012());
        Schema schema = schemaRegistry.getSchema(SchemaLocation.of("classpath:schema.json"));
        ExecutionConfig executionConfig = new ExecutionConfig.Builder().locale(Locale.ENGLISH).build();
        JsonNode jsonNode = mapper.readTree(
                JSONSchemaTest.class.getResourceAsStream("/product_valid.json"));
        List<Error> errors = schema.validate(jsonNode, executionContext -> executionContext.setExecutionConfig(executionConfig));
        assertThat(errors).isEmpty();
    }

    // TODO json-schema to json
    // TODO json-schema to records/java
    // TODO json-schema to swagger
    // TODO validation with json schema

    @Test
    public void givenInvalidInput_whenValidating_thenInvalid_deep() {
        SchemaRegistry schemaRegistry = SchemaRegistry.withDialect(Dialects.getDraft202012());
        Schema schema = schemaRegistry.getSchema(SchemaLocation.of("classpath:schema/taxpayer/taxpayer.json"));
        ExecutionConfig executionConfig = new ExecutionConfig.Builder().locale(Locale.ENGLISH).build();
        JsonNode jsonNode = mapper.readTree(
                JSONSchemaTest.class.getResourceAsStream("/taxpayer_invalid.json"));
        List<Error> errors = schema.validate(jsonNode, executionContext -> executionContext.setExecutionConfig(executionConfig));
        assertThat(errors.stream().map(Objects::toString).toList()).isNotEmpty()
                .contains("/naturalPerson/placeOfBirth/country: does not match the regex pattern ^[A-Z]{2}$",
                        "/naturalPerson/nationalIdentifiers/0/type: does not have a value in the enumeration [\"FR_SPI\", \"TIN\", \"SSN\", \"NINO\", \"OTHER\"]",
                        "/naturalPerson/nationalIdentifiers/0/issuerCountry: does not match the regex pattern ^[A-Z]{2}$",
                        "/naturalPerson/postalAddress/country: does not match the regex pattern ^[A-Z]{2}$",
                        "/household/maritalStatus: does not have a value in the enumeration [\"SINGLE\", \"MARRIED\", \"PACS\", \"DIVORCED\", \"WIDOWED\"]");
    }

    @Test
    public void givenValidInput_whenValidating_thenValid_deep() {
        SchemaRegistry schemaRegistry = SchemaRegistry.withDialect(Dialects.getDraft202012());
        Schema schema = schemaRegistry.getSchema(SchemaLocation.of("classpath:schema/taxpayer/taxpayer.json"));
        ExecutionConfig executionConfig = new ExecutionConfig.Builder().locale(Locale.ENGLISH).build();
        JsonNode jsonNode = mapper.readTree(
                JSONSchemaTest.class.getResourceAsStream("/taxpayer_valid.json"));
        List<Error> errors = schema.validate(jsonNode, executionContext -> executionContext.setExecutionConfig(executionConfig));
        assertThat(errors).isEmpty();
    }

}
