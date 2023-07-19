package TestData;

import org.json.JSONObject;

public class TestDataHerokuApp {
    public int basariliStatusKodu = 200;

    public JSONObject bookingDatesOlusturJSON(){

        JSONObject bookingdates = new JSONObject();
        bookingdates.put("checkin","2021-06-01");
        bookingdates.put("checkout","2021-06-10");
        return bookingdates;
    }

    public JSONObject requestBodyOlusturJSON(){

        JSONObject reqBody = new JSONObject();
        reqBody.put("firstname","Ali");
        reqBody.put("lastname","Bak");
        reqBody.put("totalprice",500);
        reqBody.put("depositpaid",false);
        reqBody.put("bookingdates",bookingDatesOlusturJSON());
        reqBody.put("additionalneeds","wi-fi");
        return reqBody;
    }

    public JSONObject expectedDataOlusturJSON(){
        JSONObject expData = new JSONObject();
        expData.put("bookingid",24);
        expData.put("booking",requestBodyOlusturJSON());
        return expData;
    }
}
