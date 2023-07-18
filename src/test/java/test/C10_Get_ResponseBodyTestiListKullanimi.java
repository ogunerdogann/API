package test;

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C10_Get_ResponseBodyTestiListKullanimi {

    /*
       http://dummy.restapiexample.com/api/v1/employees url'ine bir GET request yolladigimizda
       dönen response'in
       status code'unun 200,
       ve content type'inin application/json,
       ve response body'sindeki
          employees sayfasinin 24
          ve employee'lerden birinin adinin "Ashton Cox"
          ve girilen yaslar icinde 61,40,30 degerlerinin oldugunu test edin
     */

    @Test
    public void get01(){

        //1- url hazirla
        String url = "http://dummy.restapiexample.com/api/v1/employees";

        //2- expected data hazirla (burada yok)

        //3- Response'i kaydet
        Response response= given().when().get(url);

        response.prettyPrint();

        //4-Assertion
        response
                .then()
                .assertThat()
                .statusCode(200)
                .contentType("application/json")
                .body("data.id", Matchers.hasSize(24)
                        ,"data.employee_name",Matchers.hasItem("Ashton Cox")
                        ,"data.employee_age",Matchers.hasItems(61,30,40));
    }
}
