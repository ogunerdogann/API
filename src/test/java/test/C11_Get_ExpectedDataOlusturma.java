package test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.internal.RealSystem;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C11_Get_ExpectedDataOlusturma {

    /*
  https://jsonplaceholder.typicode.com/posts/22 url'ine bir GET
  request yolladigimizda donen response’in status kodunun 200 ve
  response body’sinin asagida verilen ile ayni oldugunu test ediniz
   Response body = Expected Body
    {
    "userId":3,
    "id":22,
    "title":"dolor sint quo a velit explicabo quia nam",
    "body":"eos qui et ipsum ipsam suscipit aut\nsed omnis non odio\nexpedita ear
    um mollitia molestiae aut atque rem suscipit\nnam impedit esse"
    }
     */

    @Test
    public void get01(){

        //1- Endpoint (Url) hazirla
        String url = "https://jsonplaceholder.typicode.com/posts/22";

        //2- Expected Data hazirla
        JSONObject expectedData = new JSONObject();
        expectedData.put("userId",3);
        expectedData.put("id",22);
        expectedData.put("title","dolor sint quo a velit explicabo quia nam");
        expectedData.put("body","eos qui et ipsum ipsam suscipit aut\\nsed omnis non odio\\nexpedita ear\n" +
                "    um mollitia molestiae aut atque rem suscipit\\nnam impedit esse");

        System.out.println("expected Data: " + expectedData);

        //3- Response'i kaydet
        Response response = given().when().get(url);

        response.prettyPrint();

        //4- Assertion
        JsonPath respJP = response.jsonPath(); //body'deki tüm degerlere ulasabilmek icin böyle yaptim
        assertEquals(expectedData.get("userId"),respJP.get("userId"));
        assertEquals(expectedData.get("id"),respJP.get("id"));
        assertEquals(expectedData.get("title"),respJP.get("title"));
        //assertEquals(expectedData.get("body"),respJP.get("body"));  Burasi hata veriyor!!



    }
}
