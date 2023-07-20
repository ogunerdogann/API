package test;

import baseUrl.DummyBaseUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.DummyDataPOJO;
import pojos.DummyPOJO;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;

public class C27_Get_PojoKapanis extends DummyBaseUrl {

    /*
    http://dummy.restapiexample.com/api/v1/employee/3 url’ine bir GET request
     gonderdigimizde donen response’un asagidaki gibi oldugunu test edin.

	Response Body
    {
    "status":"success",
    "data":{
            "id":3,
            "employee_name":"Ashton Cox",
            "employee_salary":86000,
            "employee_age":66,
            "profile_image":""
            },
    "message":"Successfully! Record has been fetched."
    }

     */

    @Test
    public void get01(){

        //1- Url hazirla
        specDummy.pathParams("pp1","api","pp2","v1","pp3","employee","pp4",3);

        //2- Expected Data Hazirla
        DummyDataPOJO data = new DummyDataPOJO(3,"Ashton Cox",86000,66,"");
        DummyPOJO expData = new DummyPOJO("success",data,"Successfully! Record has been fetched.");

        //3- Response'i kaydet
        Response response = given()
                .spec(specDummy)
                .when()
                .get("/{pp1}/{pp2}/{pp3}/{pp4}");

        //4-Assertions
        DummyPOJO resPojo = response.as(DummyPOJO.class);
        assertEquals(expData.getStatus(),resPojo.getStatus());
        assertEquals(expData.getData().getId(),resPojo.getData().getId());
        assertEquals(expData.getData().getEmployee_name(),resPojo.getData().getEmployee_name());
        assertEquals(expData.getData().getEmployee_salary(),resPojo.getData().getEmployee_salary());
        assertEquals(expData.getData().getEmployee_age(),resPojo.getData().getEmployee_age());
        assertEquals(expData.getData().getProfile_image(),resPojo.getData().getProfile_image());
        assertEquals(expData.getMessage(),resPojo.getMessage());
    }
}