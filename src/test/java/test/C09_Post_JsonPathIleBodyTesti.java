package test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C09_Post_JsonPathIleBodyTesti {

    /*
            https://restful-booker.herokuapp.com/booking
            url’ine asagidaki body'ye sahip
            bir POST request gonderdigimizde
                       {
                            "firstname" : "Ali",
                            "lastname" : "Bak",
                            "totalprice" : 500,
                            "depositpaid" : false,
                            "bookingdates" : {
                                            "checkin" : "2021-06-01",
                                            "checkout" : "2021-06-10"
                                             },
                            "additionalneeds" : "wi-fi"
                        }
            donen Response’un,
            status code’unun 200,
            ve content type’inin application/json; charset=utf-8,
            ve response body’sindeki
                "firstname“in,"Ali",
                ve "lastname“in, "Bak",
                ve "totalprice“in,500,
                ve "depositpaid“in,false,
                ve "checkin" tarihinin 2021-06-01
                ve "checkout" tarihinin 2021-06-10
                ve "additionalneeds“in,"wi-fi"
            oldugunu test edin
     */

    @Test
    public void post01(){

        // 1- Url ve Request Body hazirla
        String url = "https://restful-booker.herokuapp.com/booking";

        /*
        {
                            "firstname" : "Ali",
                            "lastname" : "Bak",
                            "totalprice" : 500,
                            "depositpaid" : false,
                            "bookingdates" : {
                                            "checkin" : "2021-06-01",
                                            "checkout" : "2021-06-10"
                                             },
                            "additionalneeds" : "wi-fi"
                        }
         */

        JSONObject bookingdates = new JSONObject();

        bookingdates.put("checkin", "2021-06-01");
        bookingdates.put("checkout", "2021-06-10");

        JSONObject reqBody = new JSONObject();

        reqBody.put("firstname","Ali");
        reqBody.put("lastname","Bak");
        reqBody.put("totalprice",500);
        reqBody.put("depositpaid",false);
        reqBody.put("additionalneeds","wi-fi");
        reqBody.put("bookingdates",bookingdates);

        System.out.println(reqBody);
        System.out.println("Isim: "+reqBody.get("firstname"));

        //2- Expected data hazirla

        //3-Response'i kaydet
        Response response = given()
                                .contentType(ContentType.JSON)
                             .when()
                                .body(reqBody.toString())
                                .post(url);
        response.prettyPrint();

        //4- Assertion
        response
                .then()
                    .assertThat()
                    .statusCode(200)
                    .contentType("application/json; charset=utf-8")
                    .body("booking.firstname", Matchers.equalTo("Ali"))
                    .body("booking.lastname",Matchers.equalTo("Bak"))
                    .body("booking.totalprice",Matchers.equalTo(500))
                    .body("booking.depositpaid",Matchers.equalTo(false))
                    .body("booking.additionalneeds",Matchers.equalTo("wi-fi"))
                    .body("booking.bookingdates.checkin",Matchers.equalTo("2021-06-01"))
                    .body("booking.bookingdates.checkout",Matchers.equalTo("2021-06-10"));


    }
}
