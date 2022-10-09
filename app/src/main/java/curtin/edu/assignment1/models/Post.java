package curtin.edu.assignment1.models;

public class Post  {

    private int userId;
    private int postId;
    private String title;
    private String body;

    public Post(int userId, int postId, String title, String body) {
        this.userId = userId;
        this.postId = postId;
        this.title = title;
        this.body = body;
    }

    public int getUserId() {
        return userId;
    }

    public int getPostId() {
        return postId;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
}
