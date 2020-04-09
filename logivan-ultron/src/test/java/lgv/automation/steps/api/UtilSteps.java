package lgv.automation.steps.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lgv.automation.util.Log;
import lgv.automation.util.api.Config;
import lgv.automation.web.util.SerenityUtil;
import net.serenitybdd.rest.SerenityRest;

public class UtilSteps {

    private static String getAPIURLOfPR(){
        return SerenityUtil.getEnv("api.pr.url");
    }

    private static String getAPIURLOfTestBed(){
        return SerenityUtil.getEnv("api.testbed.url");
    }

    private static String getAPIURLOfProduction(){
        return SerenityUtil.getEnv("api.production.url");
    }

    private static String getAPIURLOfMockServer(){
        return SerenityUtil.getEnv("api.postman.mock.url");
    }

    public static String getApiBaseURL(String environment) {

        String baseURl;

        switch (environment) {
            case "pr" :
                baseURl = getAPIURLOfPR();
                break;

            case "testbed" :
                baseURl = getAPIURLOfTestBed();
                break;

            case "production" :
                baseURl = getAPIURLOfProduction();
                break;

            case "mock" :
                baseURl = getAPIURLOfMockServer();
                break;

            default:
                Log.error("Wrong environment input! Init base url of testbed!");
                baseURl = getAPIURLOfTestBed();
                break;
        }

        return baseURl;
    }

    public static String getApiLgtK8Url(){
        return SerenityUtil.getEnv("api.logistic.k8.staging.url");
    }

    public static Response post(String authorization, String body, String url) {

        Log.debug("Authorization: " + authorization);
        Log.debug("Body: " + body);
        Log.debug("Url: " + url);
        Log.debug("Method: POST");

        Response response = SerenityRest.rest()
                .given()
                .contentType(ContentType.JSON.withCharset("UTF-8"))
                .header("DeviceId", Config.deviceID)
                .header("Platform", Config.platform)
                .header("Authorization", authorization)
                .body(body)
                .when()
                .post(url);

        return response;
    }

    public static Response post(String body, String url) {

        Log.debug("Body: " + body);
        Log.debug("Url: " + url);
        Log.debug("Method: POST");

        Response response = SerenityRest.rest()
                .given()
                .contentType(ContentType.JSON.withCharset("UTF-8"))
                .header("DeviceId", Config.deviceID)
                .header("Platform", Config.platform)
                .body(body)
                .when()
                .post(url);

        return response;
    }

    public static Response postWithoutBody(String authorization, String url) {

        Log.debug("Authorization: " + authorization);
        Log.debug("Url: " + url);
        Log.debug("Method: POST");

        Response response = SerenityRest.rest()
                .given()
                .contentType(ContentType.JSON.withCharset("UTF-8"))
                .header("DeviceId", Config.deviceID)
                .header("Platform", Config.platform)
                .header("Authorization", authorization)
                .when()
                .post(url);

        return response;
    }

    public static Response patch(String authorization, String url) {

        Log.debug("Url: " + url);
        Log.debug("Method: PATCH");

        Response response = SerenityRest.rest()
                .given()
                .contentType(ContentType.JSON.withCharset("UTF-8"))
                .header("DeviceId", Config.deviceID)
                .header("Platform", Config.platform)
                .header("Authorization", authorization)
                .when()
                .patch(url);

        return response;
    }

    public static Response patch(String authorization, String body, String url) {

        Log.debug("Authorization: " + authorization);
        Log.debug("Body: " + body);
        Log.debug("Url: " + url);
        Log.debug("Method: PATCH");

        Response response = SerenityRest.rest()
                .given()
                .contentType(ContentType.JSON.withCharset("UTF-8"))
                .header("DeviceId", Config.deviceID)
                .header("Platform", Config.platform)
                .header("Authorization", authorization)
                .body(body)
                .when()
                .patch(url);

        return response;
    }

    public static Response put(String authorization, String body, String url) {

        Log.debug("Authorization: " + authorization);
        Log.debug("Body: " + body);
        Log.debug("Url: " + url);
        Log.debug("Method: PUT");

        Response response = SerenityRest.rest()
                .given()
                .contentType(ContentType.JSON.withCharset("UTF-8"))
                .header("DeviceId", Config.deviceID)
                .header("Platform", Config.platform)
                .header("Authorization", authorization)
                .body(body)
                .when()
                .put(url);

        return response;
    }

    public static Response put(String body, String url) {

        Log.debug("Body: " + body);
        Log.debug("Url: " + url);
        Log.debug("Method: PUT");

        Response response = SerenityRest.rest()
                .given()
                .contentType(ContentType.JSON.withCharset("UTF-8"))
                .header("DeviceId", Config.deviceID)
                .header("Platform", Config.platform)
                .body(body)
                .when()
                .put(url);

        return response;
    }

    public static Response put(String url) {

        Log.debug("Url: " + url);
        Log.debug("Method: PUT");

        Response response = SerenityRest.rest()
                .given()
                .contentType(ContentType.JSON.withCharset("UTF-8"))
                .header("DeviceId", Config.deviceID)
                .header("Platform", Config.platform)
                .when()
                .put(url);

        return response;
    }

    public static Response putWithoutBody(String authorization, String url) {

        Log.debug("Authorization: " + authorization);
        Log.debug("Url: " + url);
        Log.debug("Method: PUT");

        Response response = SerenityRest.rest()
                .given()
                .contentType(ContentType.JSON.withCharset("UTF-8"))
                .header("DeviceId", Config.deviceID)
                .header("Platform", Config.platform)
                .header("Authorization", authorization)
                .when()
                .put(url);

        return response;
    }

    public static Response delete(String authorization, String body, String url) {

        Log.debug("Authorization: " + authorization);
        Log.debug("Body: " + body);
        Log.debug("Url: " + url);
        Log.debug("Method: DELETE");

        Response response = SerenityRest.rest()
                .given()
                .contentType(ContentType.JSON.withCharset("UTF-8"))
                .header("DeviceId", Config.deviceID)
                .header("Platform", Config.platform)
                .header("Authorization", authorization)
                .body(body)
                .when()
                .delete(url);

        return response;
    }

    public static Response delete(String body, String url) {

        Log.debug("Body: " + body);
        Log.debug("Url: " + url);
        Log.debug("Method: DELETE");

        Response response = SerenityRest.rest()
                .given()
                .contentType(ContentType.JSON.withCharset("UTF-8"))
                .header("DeviceId", Config.deviceID)
                .header("Platform", Config.platform)
                .body(body)
                .when()
                .delete(url);

        return response;
    }

    public static Response deleteWithoutBody(String authorization, String url) {

        Log.debug("Authorization: " + authorization);
        Log.debug("Url: " + url);
        Log.debug("Method: DELETE");

        Response response = SerenityRest.rest()
                .given()
                .contentType(ContentType.JSON.withCharset("UTF-8"))
                .header("DeviceId", Config.deviceID)
                .header("Platform", Config.platform)
                .header("Authorization", authorization)
                .when()
                .delete(url);

        return response;
    }

    public static Response delete(String url) {

        Log.debug("Url: " + url);
        Log.debug("Method: DELETE");

        Response response = SerenityRest.rest()
                .given()
                .contentType(ContentType.JSON.withCharset("UTF-8"))
                .header("DeviceId", Config.deviceID)
                .header("Platform", Config.platform)
                .delete(url);

        return response;
    }

    public static Response get(String authorization, String url) {

        Log.debug("Authorization: " + authorization);
        Log.debug("Url: " + url);
        Log.debug("Method: GET");

        Response response = SerenityRest.rest()
                .given()
                .contentType(ContentType.JSON.withCharset("UTF-8"))
                .header("DeviceId", Config.deviceID)
                .header("Platform", Config.platform)
                .header("Authorization", authorization)
                .when()
                .get(url);

        return response;
    }

    public static Response get(String url) {

        Log.debug("Url: " + url);
        Log.debug("Method: GET");

        Response response = SerenityRest.rest()
                .given()
                .contentType(ContentType.JSON.withCharset("UTF-8"))
                .header("DeviceId", Config.deviceID)
                .header("Platform", Config.platform)
                .get(url);

        return response;
    }

//    public static void restAssuredConfig(Boolean allowAllCert) {
//        if (allowAllCert) {
//            //Allow all https certificates
//            RestAssured.config = new com.jayway.restassured.config.RestAssuredConfig().encoderConfig(
//                    encoderConfig().defaultContentCharset("UTF-8")).sslConfig(sslConfig().allowAllHostnames()).
//                    sslConfig(sslConfig().relaxedHTTPSValidation());
//        }
//
//    }

    public static boolean isStatusCodeFail(int statusCode) {

        Log.debug("Status code: " + statusCode);
        if (statusCode < 200 || statusCode > 299)
            return true;
        else
            return false;
    }

    public static boolean isServerError(int statusCode) {

        Log.debug("Status code: " + statusCode);
        if (statusCode >= 500 && statusCode <= 599)
            return true;
        else
            return false;
    }

    public static Integer getStatusCode(Response response) {

        Integer statusCode = response.statusCode();
        Log.info("Status code: " + statusCode);

        return statusCode;
    }

    public static String getBodyAsString(Response response) {
        return response.getBody().asString();
    }
}
