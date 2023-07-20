package pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data                //Getter, Setter ve toString() 'leri olusturur
@NoArgsConstructor   //Parametresiz Constructor olusturur
@AllArgsConstructor  //Tüm parametreleri iceren Constructor olusturur
public class BookingDatesPOJO {

    /*
    "bookingdates" : {
    	          "checkin" : "2021-06-01",
    	          "checkout" : "2021-06-10"
    	       },
     */

    private String checkin;
    private String checkout;
}
