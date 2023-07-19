package test;

import baseUrl.JsonPlaceHolderBaseURL;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.nullValue;

public class C15_BaseUrlJsonPlaceHolder extends JsonPlaceHolderBaseURL {

    //Class icinde 3 Test metodu olusturun ve asagidaki testleri yapin

    /*
        1-  https://jsonplaceholder.typicode.com/posts endpointine bir GET
         request gonderdigimizde donen response’un status code’unun 200 oldugunu
         ve Response’ta 100 kayit oldugunu test edin.
        */



    /*
        2- https://jsonplaceholder.typicode.com/posts/44 endpointine bir GET
            request gonderdigimizde donen response’un status code’unun 200 oldugunu
            ve “title” degerinin “optio dolor molestias sit” oldugunu test edin
         */
    /*
        3- https://jsonplaceholder.typicode.com/posts/50 endpointine bir DELETE
            request gonderdigimizde donen response’un status code’unun 200 oldugunu ve
            response body’sinin null oldugunu test edin
         */

    @Test
    public void get01(){
    /*
        1-  https://jsonplaceholder.typicode.com/posts endpointine bir GET
         request gonderdigimizde donen response’un status code’unun 200 oldugunu
         ve Response’ta 100 kayit oldugunu test edin.
        */

        //1- url hazirla
        specJsonPlace.pathParam("pp1","posts"); //1 tane path parametresi var o nedenle pathParam secildi

        //2- expected data hazirla

        //3-Response'i kaydet
        Response response = given().spec(specJsonPlace).when().get("/{pp1}");
        //spec'ten base url, pp1'den de path kismi alindi
        response.prettyPrint();

        //4- Assertion
        response.then().assertThat().statusCode(200).body("title", Matchers.hasSize(100));

    }

    @Test
    public void get02(){
    /*
        2- https://jsonplaceholder.typicode.com/posts/44 endpointine bir GET
            request gonderdigimizde donen response’un status code’unun 200 oldugunu
            ve “title” degerinin “optio dolor molestias sit” oldugunu test edin
         */

        //1- url hazirla
        specJsonPlace.pathParams("pp1","posts","pp2",44);
        // burada path parametrelerin 2 tane o nedenle params kullanildi

        //2- Expected Data hazirla

        //3- Response'i kaydet
        Response response = given().spec(specJsonPlace).when().get("/{pp1}/{pp2}");
        response.prettyPrint();

        //4- Assertion
        response.then().assertThat()
                .statusCode(200)
                .body("title", Matchers.equalTo("optio dolor molestias sit"));


    }

    @Test
    public void delete01(){
     /*
        3- https://jsonplaceholder.typicode.com/posts/50 endpointine bir DELETE
            request gonderdigimizde donen response’un status code’unun 200 oldugunu ve
            response body’sinin null oldugunu test edin
         */

        //1- Url Hazirla
        specJsonPlace.pathParams("pp1","posts","pp2",50);

        //2- Expected Data hazirla

        //3- Response'i kaydet
        Response response = given().spec(specJsonPlace).delete("/{pp1}/{pp2}");
        response.prettyPrint();

        //4- Assertion
        response.then().assertThat().statusCode(200).body("title",nullValue());

    }
}
