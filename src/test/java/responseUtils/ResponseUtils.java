package responseUtils;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import data.PostPojo;
import io.restassured.response.Response;
import requests.Requests;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class ResponseUtils {

    private final static String ALL_DATA = ".";

    public Requests requests = new Requests();

    public Response getRequestResult(String Uri, String value, String resource) {
        Response responseMain = requests.getRequest(Uri, value, resource);
        return responseMain;
    }

    public <T> boolean dataRequestIsCorrect(Response res, String data, Class<T> classType) {
        T pojo = responseToPojo(res, classType);
        return pojo.equals(getDataFromJson(data, classType));
    }

    public boolean postBodyIsEmpty(Response res) {
        PostPojo pojo = responseToPojo(res, PostPojo.class);
        return pojo.getBody() == null;
    }

    public <T> Object getDataFromJson(String data, Class<T> classType) {
        Object userData = null;
        try (JsonReader reader = new JsonReader(new FileReader(data))) {
            userData = new Gson().fromJson(reader, classType);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userData;
    }

    public <T> T responseToPojo(Response res, Class<T> classType) {
        return res.body().as(classType);
    }

    public <T> List<T> responseToPojoList(Response res, Class<T> classType) {
        return res.jsonPath().getList(ALL_DATA, classType);
    }

    public boolean isSorted(Response res) {
        List<PostPojo> pojoList = responseToPojoList(res, PostPojo.class);
        List<Integer> idList = pojoList.stream().map(PostPojo::getId).collect(Collectors.toList());
        List<Integer> sortedIdList = idList.stream().sorted().collect(Collectors.toList());
        return sortedIdList.equals(idList);
    }

    public Response getPostRequestResult(String Uri, String resource, Object register) {
        return requests.postRequest(Uri, resource, register);
    }

    public PostPojo createPost(int userId, String title, String body) {
        return new PostPojo(userId, title, body);
    }

    public boolean requestIsCorrect(Response res, int userId, String title, String body) {
        PostPojo pojo = responseToPojo(res, PostPojo.class);
        return (pojo.getUserId() == userId && pojo.getTitle().equals(title))
                && pojo.getBody().equals(body);
    }
}
