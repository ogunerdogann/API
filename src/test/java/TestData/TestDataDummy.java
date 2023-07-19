package TestData;

import org.json.JSONObject;

import java.util.HashMap;

public class TestDataDummy {

    public int basariliStatusCode = 200;
    public String contentType = "application/json";

    public JSONObject dataInnerOlusturmaJSON(){
        JSONObject dataInner = new JSONObject();
        dataInner.put("id",3);
        dataInner.put("employee_name","Ashton Cox");
        dataInner.put("employee_salary",86000);
        dataInner.put("employee_age",66);
        dataInner.put("profile_image","");
        return dataInner;
    }

    public JSONObject expectedDataOlusturJSON(){
        JSONObject expData = new JSONObject();
        expData.put("status","success");
        expData.put("data",dataInnerOlusturmaJSON());
        expData.put("message","Successfully! Record has been fetched.");
        return expData;
    }

    public HashMap<String,Object> expectedDataOlusturMAP(){

        HashMap<String,Object> expData = new HashMap<>();
        expData.put("status","success");
        expData.put("data",dataOlusturMAP());
        expData.put("message","Successfully! Record has been fetched.");
        return expData;
    }

    public HashMap<String,Object> dataOlusturMAP(){

        HashMap<String,Object> dataInner = new HashMap<>();
        dataInner.put("id",3.0);
        dataInner.put("employee_name","Ashton Cox");
        dataInner.put("employee_salary",86000.0);
        dataInner.put("employee_age",66.0);
        dataInner.put("profile_image","");
        return dataInner;
    }
}
