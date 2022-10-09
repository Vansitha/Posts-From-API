package curtin.edu.assignment1.data;

import android.app.Activity;
import android.view.View;
import android.widget.ProgressBar;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import curtin.edu.assignment1.PostsAdapter;
import curtin.edu.assignment1.UsersAdapter;
import curtin.edu.assignment1.models.JSONPostsKeys;
import curtin.edu.assignment1.models.JSONUserKeys;
import curtin.edu.assignment1.models.Post;
import curtin.edu.assignment1.models.SearchValues;
import curtin.edu.assignment1.models.User;

public class BackgroundTaskHandler implements Runnable {

    Activity uiActivity;
    String searchData;
    ProgressBar progressBar;
    RecyclerView recyclerView;
    UsersList usersList;
    PostList postsList;

    public BackgroundTaskHandler(Activity uiActivity, String searchData, ProgressBar progressBar, RecyclerView recyclerView) {
        this.uiActivity = uiActivity;
        this.searchData = searchData;
        this.progressBar = progressBar;
        this.recyclerView = recyclerView;
    }

    @Override
    public void run() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        GetData getData = new GetData(uiActivity);
        getData.setSearchKey(searchData);
        Future<String> searchResponsePlaceHolder = executorService.submit(getData);
        String searchResult = waitingForSearch(searchResponsePlaceHolder);

        if (searchResult != null) {
            uiActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (searchData.equals(SearchValues.searchUsers)) {
                        try {
                            usersList = new UsersList();
                            generateUserObjects(searchResult);
                            displayUsers();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    if (searchData.equals(SearchValues.searchPosts)) {
                        try {
                            postsList = new PostList();
                            int userId = PostList.getPostsOwner();
                            generatePostObjects(searchResult, userId);
                            displayPosts();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }
            });

        }
    }

    private void generateUserObjects(String resultData) throws JSONException {
        JSONArray jsonArray = new JSONArray(resultData);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            int userId = jsonObject.getInt(JSONUserKeys.id);
            String name = jsonObject.getString(JSONUserKeys.name);
            String username = jsonObject.getString(JSONUserKeys.username);
            String email = jsonObject.getString(JSONUserKeys.email);

            // address object
            JSONObject jsonAddressObject = jsonObject.getJSONObject(JSONUserKeys.address);
            String street = jsonAddressObject.getString(JSONUserKeys.street);
            String suite = jsonAddressObject.getString(JSONUserKeys.suite);
            String city = jsonAddressObject.getString(JSONUserKeys.city);
            String zipcode = jsonAddressObject.getString(JSONUserKeys.zipcode);

            String phoneNumber = jsonObject.getString(JSONUserKeys.phone);
            String website = jsonObject.getString(JSONUserKeys.website);

            // company object
            JSONObject jsonCompanyObject = jsonObject.getJSONObject(JSONUserKeys.company);
            String companyName = jsonCompanyObject.getString(JSONUserKeys.companyName);
            String companyCatchPhrase = jsonCompanyObject.getString(JSONUserKeys.companyCatchPhrase);
            String business = jsonCompanyObject.getString(JSONUserKeys.business);

            // create a user object
            User newUser = new User(userId, name, username, email, street, suite, city, zipcode, phoneNumber,
                                    website, companyName, companyCatchPhrase, business);

            // add users to the array list
            usersList.addUser(newUser);
        }

    }

    private void generatePostObjects(String resultData, int postOwnerId) throws JSONException {
        JSONArray jsonArray = new JSONArray(resultData);

        for(int i = 0; i < jsonArray.length(); i++) {

            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            int currentUserId = jsonObject.getInt(JSONPostsKeys.userId);

            if (currentUserId == postOwnerId) {
                int postId = jsonObject.getInt(JSONPostsKeys.postId);
                String title = jsonObject.getString(JSONPostsKeys.title);
                String body = jsonObject.getString(JSONPostsKeys.body);

                // creating a post object
                Post post = new Post(currentUserId, postId, title, body);
                // add post to list
                postsList.addPost(post);
            }
        }


    }

    private void displayUsers() {
        // setting up recycler view
        recyclerView.setLayoutManager(new LinearLayoutManager(uiActivity.getApplicationContext()));
        recyclerView.setHasFixedSize(true);
        UsersAdapter usersAdapter = new UsersAdapter(uiActivity.getApplicationContext(), usersList);
        recyclerView.setAdapter(usersAdapter);
        usersAdapter.notifyDataSetChanged();

    }

    private void displayPosts() {
        recyclerView.setLayoutManager(new LinearLayoutManager(uiActivity.getApplicationContext()));
        recyclerView.setHasFixedSize(true);
        PostsAdapter postsAdapter = new PostsAdapter(uiActivity.getApplicationContext(), postsList);
        recyclerView.setAdapter(postsAdapter);
        postsAdapter.notifyDataSetChanged();

    }

    public String waitingForSearch(Future<String> searchResponsePlaceholder){
        uiActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.VISIBLE);
            }
        });
        String searchResponseData =null;
        try {
            searchResponseData = searchResponsePlaceholder.get(6000, TimeUnit.MILLISECONDS);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        uiActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
        return  searchResponseData;
    }
}
