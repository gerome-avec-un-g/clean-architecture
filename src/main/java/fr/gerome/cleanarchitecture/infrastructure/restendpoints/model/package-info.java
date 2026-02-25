@GenerateRecordsFromJsonSchemas(
        schemaRootFileLocations =
        @JsonSchemaFileLocation(
                moduleAndPackage = "schema.taxpayer",
                relativeName = "taxpayer2.json"
        )
)
package fr.gerome.cleanarchitecture.infrastructure.restendpoints.model;

import com.cosium.json_schema_to_java_record_api.GenerateRecordsFromJsonSchemas;
import com.cosium.json_schema_to_java_record_api.JsonSchemaConfiguration;
import com.cosium.json_schema_to_java_record_api.JsonSchemaFileLocation;