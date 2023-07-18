package test;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

public class C08_JSONObjectVerileriDegistirme {

    /*
    {
    "firstName": "John",
    "lastName": "doe",
    "age": 26,
    "address": {
        "streetAddress": "naist street",
        "city": "Nara",
        "postalCode": "630-0192"
                },
    "phoneNumbers": [
                    {
                        "type": "iPhone",
                        "number": "0123-4567-8888"
                    },
                    {
                        "type": "home",
                        "number": "0123-4567-8910"
                    }
                    ]
    }
     */

    @Test
    public void jsonPath01(){

        //En ic olandan basliyoruz!!
        JSONObject cepTel = new JSONObject();
        JSONObject evTel = new JSONObject();

        cepTel.put("type","iPhone");
        cepTel.put("number","0123-4567-8888");

        evTel.put("type","home");
        evTel.put("number","0123-4567-8910");

        JSONArray phoneNumbers = new JSONArray();

        phoneNumbers.put(0,cepTel);
        phoneNumbers.put(1,evTel);

        JSONObject address = new JSONObject();
        address.put("street address","naist street");
        address.put("city","Nara");
        address.put("postalCode","630-0192");

        JSONObject kisiBilgisi = new JSONObject();
        kisiBilgisi.put("firstname","John");
        kisiBilgisi.put("lastname","Doe");
        kisiBilgisi.put("age",29);
        kisiBilgisi.put("address",address);
        kisiBilgisi.put("phoneNumbers",phoneNumbers);
        System.out.println("kisi Bilgileri: "+kisiBilgisi);

        //!! KArmasik bir JSON kodunda ulasman gerekn yerleri kontrol edebilmek icin
        //https://jsonpath.com/

        System.out.println("Isim: " + kisiBilgisi.get("firstname"));
        System.out.println("Soyisim: " + kisiBilgisi.get("lastname"));
        System.out.println("Yas: " + kisiBilgisi.get("age"));
        System.out.println("Sokak Adi: " + kisiBilgisi.getJSONObject("address").get("street " + "address"));
        System.out.println("Sehir: " + kisiBilgisi.getJSONObject("address").get("city"));
        System.out.println("Posta Kodu: " + kisiBilgisi.getJSONObject("address").get("postalCode"));
        System.out.println("Tel no: "+kisiBilgisi
                                             .getJSONArray("phoneNumbers")
                                             .getJSONObject(0)
                                             .get("number"));

        System.out.println("Tel türü: "+kisiBilgisi
                                              .getJSONArray("phoneNumbers")
                                              .getJSONObject(1)
                                              .get("type"));

    }
}
