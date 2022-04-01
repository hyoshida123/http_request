package com.hyoshida123;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Attributes {
    private JsonNode json;

    @JsonProperty("attributes")
    public JsonNode getJson() {
        return this.json;
    }
}
