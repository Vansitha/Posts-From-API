package curtin.edu.assignment1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import curtin.edu.assignment1.data.BackgroundTaskHandler;
import curtin.edu.assignment1.models.SearchValues;
import curtin.edu.assignment1.models.User;

public class PostsActivity extends AppCompatActivity {

    private ExecutorService executorService = Executors.newSingleThreadExecutor();
    private ProgressBar progressIndicator;
    private RecyclerView postsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);

        progressIndicator = findViewById(R.id.postsProgressBar);
        postsRecyclerView = findViewById(R.id.postsRecyclerView);

        String getPostsStr = SearchValues.searchPosts;
        BackgroundTaskHandler backgroundTaskHandler = new BackgroundTaskHandler(PostsActivity.this, getPostsStr, progressIndicator, postsRecyclerView);
        executorService.execute(backgroundTaskHandler);

    }
}