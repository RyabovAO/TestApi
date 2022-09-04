package data;

public class RegisterPostData {

    private int userId;
    private String title;
    private String body;

    public RegisterPostData(int userId, String title, String body) {
        this.userId = userId;
        this.title = title;
        this.body = body;
    }
}
