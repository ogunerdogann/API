package pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data                //Getter, Setter ve toString() 'leri olusturur
@NoArgsConstructor   //Parametresiz Constructor olusturur
@AllArgsConstructor  //Tüm parametreleri iceren Constructor olusturur
public class BookingPOJO {

    /*
     {
         "firstname" : "Ali",
         "lastname" : “Bak",
         "totalprice" : 500,
         "depositpaid" : false,
         "bookingdates" : {
                  "checkin" : "2021-06-01",
                  "checkout" : "2021-06-10"
                           },
         "additionalneeds" : "wi-fi"
        }
     */

    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;
    private BookingDatesPOJO bookingdates;
    private String additionalneeds;
}
