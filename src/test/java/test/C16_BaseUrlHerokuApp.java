package test;

import baseUrl.HerokuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C16_BaseUrlHerokuApp extends HerokuAppBaseUrl {
    /*
    Class icinde 2 Test metodu olusturun ve asagidaki testleri yapin
    1 https://restful-booker.herokuapp.com/booking endpointine bir GET request
    gonderdigimizde donen response’un status code’unun 200 oldugunu ve
    Response’ta 12 booking oldugunu test edin
             {
            "firstname" : "Ahmet
            "lastname" : “ Bulut
            "totalprice" :500
            "depositpaid" :false
            "bookingdates" :
                        "checkin" : "2021 06 01",
                        "checkout" : "2021 06 10"
            "additionalneeds" : "wi-fi"
          2 https://restful-booker.herokuapp.com/booking
        endpointine yukaridaki body’ye sahip bir POST
        request gonderdigimizde donen response’un
        status code’unun 200 oldugunu ve “firstname”
        degerinin “Ahmet” oldugunu test edin
     */

    @Test
    public void get01(){

        //1- url hazirla
        specJsonPlace.pathParam("pp1","booking");

        //2- Expected Data hazirla

        //3-Response'i kaydet
        Response response = given().spec(specJsonPlace).when().get("/{pp1}");
        response.prettyPrint();

        //4- Assertion
        response.then().assertThat().statusCode(200).body("bookingid", Matchers.hasSize(120));
    }

    @Test
    public void post01(){

        //1- Url ve responseBody'yi hazirla
        specJsonPlace.pathParam("pp1","booking");
        JSONObject bookingDates = new JSONObject();
        bookingDates.put("checkin","2021-06-01");
        bookingDates.put("checkout","2021-06-10");

        JSONObject reqBody = new JSONObject();
        reqBody.put("firstname","Ahmet");
        reqBody.put("lastname","Bulut");
        reqBody.put("totalprice",500);
        reqBody.put("depositpaid",false);
        reqBody.put("bookingdates",bookingDates);
        reqBody.put("additionalneeds","wi-fi");

        //2- Expected Data hazirla

        //3-Response'i kaydet
        Response response = given()
                    .spec(specJsonPlace)
                    .contentType(ContentType.JSON)
                .when()
                     .body(reqBody.toString())
                     .post("/{pp1}");
        response.prettyPrint();

        //4- Assertion
        response.then().assertThat().statusCode(200).body("booking.firstname",Matchers.equalTo("Ahmet"));
    }
}
