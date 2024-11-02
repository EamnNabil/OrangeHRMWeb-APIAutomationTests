package Tests.apis;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class OrangeHRMCandidateAPI {
    private final String baseUrl = "https://opensource-demo.orangehrmlive.com";
    private final String endpoint = "/web/index.php/api/v2/recruitment/candidates";
    private final String cookieValue = "orangehrm=ob7c3a0b7qp4c8ug5dlq898q5b";  // Replace with actual cookie value
    private Integer firstCandidateId = 0;

    @Test
    public void testGetCandidates() {
        Response response = RestAssured.given()
                .baseUri(baseUrl)
                .header("Accept", "application/json")
                .header("Cookie", cookieValue)  // Use actual cookie value if session-based authentication is needed
                .queryParam("limit", 50)
                .queryParam("offset", 0)
                .queryParam("model", "list")
                .queryParam("sortField", "candidate.dateOfApplication")
                .queryParam("sortOrder", "DESC")
                .when()
                .get(endpoint);

        // Verify status code
        assertThat(response.getStatusCode(), equalTo(200));

        // Validate response structure and types
        response.then().assertThat()
                .contentType(ContentType.JSON)
                .body("data", notNullValue())
                .body("data.size()", greaterThan(0))  // Ensures there are entries in the 'data' array
                .body("data[0].id", notNullValue())
                .body("data[0].firstName", notNullValue())
                .body("data[0].lastName", notNullValue())
                .body("meta.total", notNullValue());

        firstCandidateId = response.jsonPath().getInt("data[0].id");
    }

    @Test(dependsOnMethods = "testGetCandidates")
    public void testDeleteCandidate() {
        // Candidate ID to delete
        int candidateId = 80;

        // Send DELETE request to remove candidate
        Response response = RestAssured.given()
                .baseUri(baseUrl)
                .header("Accept", "application/json, text/plain, */*")
                .header("Content-Type", "application/json")
                .header("Cookie", cookieValue)  // Replace with actual cookie if necessary
                .body("{\"ids\":[" + firstCandidateId + "]}")
                .when()
                .delete(endpoint);

        // Verify response status is 200 OK
        assertThat(response.getStatusCode(), equalTo(200));

        // Verify that response contains the candidate ID in the "data" array
        response.then().assertThat()
                .contentType(ContentType.JSON)
                .body("data", hasItem(String.valueOf(firstCandidateId)));
    }

    @Test
    public void testAddCandidate() {
        String requestBody = getRequestBody();

        // Send POST request to add candidate
        Response response = RestAssured.given()
                .baseUri(baseUrl)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Cookie", cookieValue)
                .body(requestBody)
                .when()
                .post(endpoint);

        // Validate response
        assertThat(response.getStatusCode(), equalTo(200));

        // Validate the response body content
        String responseBody = response.getBody().asString();
        JsonPath jsonPath = new JsonPath(responseBody);

        // Validate that 'data' is not null
        assertThat(jsonPath.get("data"), is(notNullValue()));

        // Validate individual fields in 'data'
        assertThat(jsonPath.get("data.firstName"), equalTo("Tanmay"));
        assertThat(jsonPath.get("data.middleName"), is(nullValue())); // Expecting null for middleName
        assertThat(jsonPath.get("data.lastName"), equalTo("Eemo"));
        assertThat(jsonPath.get("data.email"), equalTo("e.com@c.com"));

        // Validate 'meta' and 'rels' are empty
        assertThat(jsonPath.get("meta"), is(empty()));
        assertThat(jsonPath.get("rels"), is(empty()));
    }

    private static String getRequestBody() {
        String firstName = "Tanmay";
        String lastName = "Eemo";
        String email = "e.com@c.com";
        String dateOfApplication = "2024-11-01";
        int vacancyId = 3;

        // Construct the JSON request body
        return String.format(
                "{\"firstName\":\"%s\", \"middleName\":%s, \"lastName\":\"%s\", \"email\":\"%s\", \"contactNumber\":null, \"keywords\":null, \"comment\":null, \"dateOfApplication\":\"%s\", \"consentToKeepData\":false, \"vacancyId\":%d}",
                firstName, "null", lastName, email, dateOfApplication, vacancyId
        );
    }
}
