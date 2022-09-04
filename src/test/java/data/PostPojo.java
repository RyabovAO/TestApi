package data;

import lombok.Getter;
import lombok.Setter;
import java.util.Objects;

@Getter
@Setter
public class PostPojo {

    private int userId;
    private int id;
    private String title;
    private String body;

    public PostPojo(int userId, int id, String title, String body) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public PostPojo(int userId, String title, String body) {
        this.userId = userId;
        this.title = title;
        this.body = body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostPojo postPojo = (PostPojo) o;
        return userId == postPojo.userId && id == postPojo.id
                && Objects.equals(title, postPojo.title)
                && Objects.equals(body, postPojo.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, id, title, body);
    }
}
