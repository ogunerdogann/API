package baseUrl;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;
import org.junit.runner.Request;

public class JsonPlaceHolderBaseURL {

   protected RequestSpecification specJsonPlace;

   @Before
    public void setUp(){

       specJsonPlace = new RequestSpecBuilder().setBaseUri("https://jsonplaceholder.typicode.com").build();
       // Tüm testlerden önce calismasi icin Before methoduyla url'nin ana kismini tanimladik
       //Bunu postman'de de yapiyorduk ama degisken olarak tanimliyorduk
   }
}
