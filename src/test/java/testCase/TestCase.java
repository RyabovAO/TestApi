package testCase;

import data.PostPojo;
import data.UserPojo;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import responseUtils.ResponseUtils;
import utils.PropertyUtils;

public class TestCase {

    private ResponseUtils responseUtils = new ResponseUtils();
    private final static String CONTENT_TYPE = "json";
    private final static String ALL_VALUE = "all";
    private final static String POST_VALUE = "99";
    private final static String NON__EXIST_POST = "150";
    private final static String USER_VALUE = "5";
    private final static String URI = "URL";
    private final static String POSTS = "/posts/";
    private final static String POST = "posts/";
    private final static String USERS = "/users/";
    private final static int STATUS_CODE_200 = 200;
    private final static int STATUS_CODE_404 = 404;
    private final static int STATUS_CODE_201 = 201;
    private final static int CREATE_USER_ID = 1;
    private final static String CREATE_BODY = "testBody";
    private final static String CREATE_TITLE = "testTitle";
    private final static String USER_DATA_TEST = "src/main/resources/userDataTest.json";
    private final static String POST_DATA_TEST = "src/main/resources/postDataTest.json";

    @Test
    public void apiTestGetPostRequest() {
       Response allPost = responseUtils.getRequestResult(PropertyUtils.getPropertyValue(URI), ALL_VALUE, POSTS);
       Assert.assertEquals(allPost.getStatusCode(), STATUS_CODE_200, "Status code is 200");
       Assert.assertTrue(allPost.contentType().contains(CONTENT_TYPE),  "List response body isn't json");
       Assert.assertTrue(responseUtils.isSorted(allPost), "Post aren't sorted ascending");

       Response specificPost = responseUtils.getRequestResult(PropertyUtils.getPropertyValue(URI), POST_VALUE, POSTS);
       Assert.assertEquals(specificPost.getStatusCode(), STATUS_CODE_200, "Status code is 200");
       Assert.assertTrue(responseUtils.dataRequestIsCorrect(specificPost, POST_DATA_TEST, PostPojo.class),
               "Post information isn't correct");

       Response nonExistPost = responseUtils.getRequestResult(PropertyUtils.getPropertyValue(URI), NON__EXIST_POST,
               POSTS);
       Assert.assertEquals(nonExistPost.getStatusCode(), STATUS_CODE_404, "Status code is 404");
       Assert.assertTrue(responseUtils.postBodyIsEmpty(nonExistPost), "Response body isn't empty");

        Response res = responseUtils.getPostRequestResult(PropertyUtils.getPropertyValue(URI), POST,
                responseUtils.createPost(CREATE_USER_ID, CREATE_TITLE, CREATE_BODY));
        Assert.assertEquals(res.getStatusCode(), STATUS_CODE_201, "Status code is 201");
        Assert.assertTrue(responseUtils.requestIsCorrect(res, CREATE_USER_ID, CREATE_TITLE, CREATE_BODY),
                "Post information is correct");

        Response allUser = responseUtils.getRequestResult(PropertyUtils.getPropertyValue(URI), ALL_VALUE, USERS);
        Assert.assertEquals(allUser.getStatusCode(), STATUS_CODE_200, "Status code is 200");
        Assert.assertTrue(allUser.contentType().contains(CONTENT_TYPE), "List response body is json");
        Assert.assertTrue(responseUtils.responseToPojoList(allUser, UserPojo.class).stream().anyMatch(
                userTest -> userTest.equals(responseUtils.getDataFromJson(USER_DATA_TEST, UserPojo.class))),
                "User id = 5 data equals");

        Response specificUsers = responseUtils.getRequestResult(PropertyUtils.getPropertyValue(URI), USER_VALUE, USERS);
        Assert.assertEquals(specificUsers.getStatusCode(), STATUS_CODE_200, "Status code is 200");
        Assert.assertTrue(responseUtils.dataRequestIsCorrect(specificUsers, USER_DATA_TEST, UserPojo.class),
                "User data matches");
    }
}
