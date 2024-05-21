package com.axeweb.parentorganizr.model;

import com.jayway.jsonpath.JsonPath;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PupilJSONTest {

    @Test
    void shouldCompareJson() throws JSONException {
        var data = getRestData();
        var expected = """
                {
            "id": 1,
            "birthdate": "2021-09-01",
            "lastName": "Doe",
            "firstName": "John",
            "parents": [
                {
                    "id": 1,
                    "firstName": "Jane",
                    "lastName": "Doe"
                }
            ]
        }
        """;
        JSONAssert.assertEquals(expected, data, false);
    }

    @Test
    void shouldCompareJsonPath() {
        var json = """
                {
            "id": 19,
            "firstName": "John",
            "lastName": "Doe",
            "birthdate": "2021-09-01",
            "parents": [
                {
                    "id": 1,
                    "firstName": "Jane",
                    "lastName": "Doe"
                }
            ]
        }
        """;
        Integer id = JsonPath.read(json, "$.id");
        String name = JsonPath.read(json, "$.parents[0].firstName");
        assertEquals(19, id);
        assertEquals("Jane", name);
    }

    private String getRestData() {
        return """
        {
            "id": 1,
            "firstName": "John",
            "lastName": "Doe",
            "birthdate": "2021-09-01",
            "parents": [
                {
                    "id": 1,
                    "firstName": "Jane",
                    "lastName": "Doe"
                }
            ]
        }
        """;
    }

}
