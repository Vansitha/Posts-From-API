package curtin.edu.assignment1.data;

import java.util.ArrayList;

import curtin.edu.assignment1.models.Post;

public class PostList {

    private static int postsOwner;
    private static String postOwnerUsername;
    private ArrayList<Post> postList;

    public PostList() {
        this.postList = new ArrayList<>();
    }

    public  void addPost(Post post) {
        postList.add(post);
    }

    public Post getPost(int position) {
        return postList.get(position);
    }

    public int getSize() {
        return postList.size();
    }

    public static void setUserIDofOwner(int userId) {
        postsOwner =  userId;
    }

    public static int getPostsOwner() {
        return postsOwner;
    }

    public static void setPostOwnerUsername(String username) {
        postOwnerUsername = username;
    }

    public static String  getPostOwnerUsername() {
        return postOwnerUsername;
    }
}
