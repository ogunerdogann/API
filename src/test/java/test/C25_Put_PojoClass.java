package test;

import baseUrl.JsonPlaceHolderBaseURL;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.JsonPlaceRequestBodyPOJO;

import static io.restassured.RestAssured.given;

public class C25_Put_PojoClass extends JsonPlaceHolderBaseURL {

     /*
    https://jsonplaceholder.typicode.com/posts/70 url'ine asagidaki
     body’e sahip bir PUT request yolladigimizda donen response’in
     response body’sinin asagida verilen ile ayni oldugunu test ediniz

     Request Body

    {
    "title":"Ahmet",
    "body":"Merhaba",
    "userId":10,
    "id":70
    }

    Expected Body

    {
    "title":"Ahmet",
    "body":"Merhaba",
    "userId":10,
    "id":70
    }
     */

    @Test
    public void put01(){

        //1-Url ve Request Body Hazirla
        specJsonPlace.pathParams("pp1","posts","pp2",70);

        JsonPlaceRequestBodyPOJO reqBody = new JsonPlaceRequestBodyPOJO("Ahmet","Merhaba",10,70);
        System.out.println("reqBody" + reqBody);

        //2- Expected Data Hazirla
        JsonPlaceRequestBodyPOJO expData = new JsonPlaceRequestBodyPOJO("Ahmet","Merhaba",10,70);

        //3- Response'i kaydet
        Response response = given()
                .spec(specJsonPlace)
                .contentType(ContentType.JSON)
                .when()
                .body(reqBody)
                .put("/{pp1}/{pp2}");
        response.prettyPrint();

        //4-Assertions
        JsonPlaceRequestBodyPOJO repPojo = response.as(JsonPlaceRequestBodyPOJO.class);
        Assert.assertEquals( expData.getTitle(),repPojo.getTitle() );
        Assert.assertEquals( expData.getBody(),repPojo.getBody());
        Assert.assertEquals( expData.getId(),repPojo.getId() );
        Assert.assertEquals( expData.getUserId(),repPojo.getUserId() );

    }
}
