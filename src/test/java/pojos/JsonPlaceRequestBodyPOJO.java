package pojos;

public class JsonPlaceRequestBodyPOJO {

    /*
     Request Body

    {
    "title":"Ahmet",
    "body":"Merhaba",
    "userId":10,
    "id":70
    }
     */
    //1- Obje icerisindeki tüm key degerlerini class level'da private variable olarak hazirla
    private String title;
    private String body;
    private int userId;
    private int id;

    //2- Getter ve Setter'lari hazirla

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
