package test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;

public class C12_Post_ExpectedDataVeJsonPathIleAssertion {

/*
    https://restful-booker.herokuapp.com/booking url’ine asagidaki body'ye sahip
    bir POST request gonderdigimizde donen response’un status kodunu ve id haric
    body'sinin asagidaki gibi oldugunu test edin.
    Request body
          {
          "firstname" : "Ali",
          "lastname" : “Bak",
          "totalprice" : 500,
          "depositpaid" : false,
          "bookingdates" : {
                      "checkin" : "2021-06-01",
                      "checkout" : "2021-06-10"
                        },
          "additionalneeds" : "wi-fi"
           }
    Expected Body
    {
    "bookingid":24,
    "booking":{
            "firstname":"Ali",
            "lastname":"Bak",
            "totalprice":500,
            "depositpaid":false,
            "bookingdates":{
                            "checkin":"2021-06-01",
                            "checkout":"2021-06-10"
                            },
            "additionalneeds":"wi-fi"
               }
    }
     */

    @Test
    public void post01(){

        //1- Endpoint(url) ve request body kaydet
        String url = "https://restful-booker.herokuapp.com/booking";

        /*
        {
          "firstname" : "Ahmet",
          "lastname" : “Bulut",
          "totalprice" : 500,
          "depositpaid" : false,
          "bookingdates" : {
                      "checkin" : "2021-06-01",
                      "checkout" : "2021-06-10"
                        },
          "additionalneeds" : "wi-fi"
           }
         */
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

        //Expected Data Hazirla
        /*
        Expected Body
    {
    "bookingid":24,
    "booking":{
            "firstname":"Ali",
            "lastname":"Bak",
            "totalprice":500,
            "depositpaid":false,
            "bookingdates":{
                            "checkin":"2021-06-01",
                            "checkout":"2021-06-10"
                            },
            "additionalneeds":"wi-fi"
               }
         */

        JSONObject expectedData = new JSONObject();
        expectedData.put("bookingid",24);
        expectedData.put("booking",reqBody); //tekrar teker teker yazmamiza gerek yok

        //3- Response'i kaydet
        Response response = given()
                    .contentType(ContentType.JSON)
                .when()
                    .body(reqBody.toString())
                    .post(url);

        //4- Assertion

        JsonPath respJP = response.jsonPath();
        assertEquals(expectedData.getJSONObject("booking").get("firstname"), respJP.get("booking.firstname"));
        // Karsilastirdigimiz seylerden birisi JsonPath birisi de JsonObject, o nedenle yazimlara dikkat!!
        assertEquals(expectedData.getJSONObject("booking").get("lastname"), respJP.get("booking.lastname"));
        assertEquals(expectedData.getJSONObject("booking").get("totalprice"), respJP.get("booking.totalprice"));
        assertEquals(expectedData.getJSONObject("booking").get("depositpaid"), respJP.get("booking.depositpaid"));
        assertEquals(expectedData.getJSONObject("booking").getJSONObject("bookingdates").get("checkin"), respJP.get("booking.bookingdates.checkin"));
        assertEquals(expectedData.getJSONObject("booking").getJSONObject("bookingdates").get("checkout"), respJP.get("booking.bookingdates.checkout"));
        assertEquals(expectedData.getJSONObject("booking").get("additionalneeds"), respJP.get("booking.additionalneeds"));

        //C13 ve C14'ü JUnit ile cöz!

   }
}
