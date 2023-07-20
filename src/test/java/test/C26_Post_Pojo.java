package test;

import baseUrl.HerokuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.BookingDatesPOJO;
import pojos.BookingPOJO;
import pojos.HerokuAppExpectedBodyPOJO;

import static io.restassured.RestAssured.given;

public class C26_Post_Pojo extends HerokuAppBaseUrl {

   /*
    https://restful-booker.herokuapp.com/booking url’ine
    asagidaki body'ye sahip bir POST request gonderdigimizde
    donen response’un id disinda asagidaki gibi oldugunu test edin.
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

       	Response Body = Expected Data
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
                              }
              ,
              "additionalneeds":"wi-fi"
                    }
          }
         */

    @Test
    public void post01(){

        specHerokuApp.pathParam("pp1","booking");

        BookingDatesPOJO bookingDates = new BookingDatesPOJO("2021-06-01","2021-06-10");
        BookingPOJO reqBody = new BookingPOJO("Ali","Bak",500,false,bookingDates,"wi-fi");
        HerokuAppExpectedBodyPOJO expData = new HerokuAppExpectedBodyPOJO(24,reqBody);

        Response response = given()
                .spec(specHerokuApp)
                .contentType(ContentType.JSON)
                .when()
                .body(reqBody)
                .post("/{pp1}");
        response.prettyPrint();
        System.out.println(response.getStatusCode());
        HerokuAppExpectedBodyPOJO respPojo = response.as(HerokuAppExpectedBodyPOJO.class);
        Assert.assertEquals( expData.getBooking().getFirstname(), respPojo.getBooking().getFirstname() );
        Assert.assertEquals( expData.getBooking().getLastname(), respPojo.getBooking().getLastname() );
        Assert.assertEquals( expData.getBooking().getBookingdates().getCheckin(), respPojo.getBooking().getBookingdates().getCheckin() );
        Assert.assertEquals( expData.getBooking().getBookingdates().getCheckout(), respPojo.getBooking().getBookingdates().getCheckout() );
        Assert.assertEquals( expData.getBooking().isDepositpaid(), respPojo.getBooking().isDepositpaid() );
        Assert.assertEquals( expData.getBooking().getTotalprice(), respPojo.getBooking().getTotalprice() );
        Assert.assertEquals( expData.getBooking().getAdditionalneeds(), respPojo.getBooking().getAdditionalneeds() );
    }
}
