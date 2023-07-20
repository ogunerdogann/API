package pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data                //Getter, Setter ve toString() 'leri olusturur
@NoArgsConstructor   //Parametresiz Constructor olusturur
@AllArgsConstructor  //Tüm parametreleri iceren Constructor olusturur
public class DummyPOJO {

    /*
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

    private String status;
    private DummyDataPOJO data;
    private String message;
}
