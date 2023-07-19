package test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class C14_Put_SoftAssertIleExpectedDataTesti {

    /*
        https://dummy.restapiexample.com/api/v1/update/21 url’ine asagidaki body’ye sahip bir PUT
request gonderdigimizde donen response’un asagidaki gibi oldugunu test edin.
    Request Body
     {
    "status": "success",
    "data":
    {
            "name": “Ahmet",
            "salary": "1230",
            "age": "44",
            "id": 40
             }
      }
Response Body
{
    "status": "success",
    "data": {
        "data": {
            "name": "Ahmet",
            "id": 40,
            "salary": "1230",
            "age": "44"
        },
        "status": "success"
    },
    "message": "Successfully! Record has been updated."
}
     */

    @Test
    public void put01(){

        //1- Url ve request body kaydet
        String url = "https://dummy.restapiexample.com/api/v1/update/21";

        /*
         {
    "status": "success",
    "data":
    {
            "name": “Ahmet",
            "salary": "1230",
            "age": "44",
            "id": 40
             }
      }
         */
        JSONObject reqBodyInner = new JSONObject();
        reqBodyInner.put("name","Ahmet");
        reqBodyInner.put("salary",1230);
        reqBodyInner.put("age",44);
        reqBodyInner.put("id",40);

        JSONObject reqBodyOuter = new JSONObject();
        reqBodyOuter.put("status","success");
        reqBodyOuter.put("data",reqBodyInner);

        //2- Expected Data kaydet

        /*
        Response Body
{
    "status": "success",
    "data": {
        "data": {
            "name": "Ahmet",
            "id": 40,
            "salary": "1230",
            "age": "44"
        },
        "status": "success"
        "message": "Successfully! Record has been updated."
         */
        JSONObject expectedData = new JSONObject();
        expectedData.put("status","success");
        expectedData.put("data",reqBodyOuter);  //Hepsini teker teker yazmaya gerek yok!!
        expectedData.put("message","Successfully! Record has been updated.");

        //3- Response'yi kaydet
        Response response = given()    // bir request body'si göndermeden önce content type belirtilmeli!!
                    .contentType(ContentType.JSON)
                .when()
                    .body(reqBodyOuter.toString())
                    .put(url);

        //4- Assertionlari yap
        SoftAssert softAssert = new SoftAssert();
        JsonPath respJP = response.jsonPath();
        softAssert.assertEquals(respJP.get("status"),expectedData.get("status"));
        softAssert.assertEquals(respJP.get("message"),expectedData.get("message"));
        softAssert.assertEquals(respJP.get("data.data.name"),expectedData.getJSONObject("data").getJSONObject("data").get("name"));
        softAssert.assertEquals(respJP.get("data.data.id"),expectedData.getJSONObject("data").getJSONObject("data").get("id"));
        softAssert.assertEquals(respJP.get("data.data.salary"),expectedData.getJSONObject("data").getJSONObject("data").get("salary"));
        softAssert.assertEquals(respJP.get("data.data.age"),expectedData.getJSONObject("data").getJSONObject("data").get("age"));


        softAssert.assertAll();
    }
}
