package curtin.edu.assignment1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ProgressBar;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import curtin.edu.assignment1.data.BackgroundTaskHandler;
import curtin.edu.assignment1.models.SearchValues;

public class MainActivity extends AppCompatActivity {

    private ExecutorService executorService = Executors.newSingleThreadExecutor();
    private ProgressBar progressIndicator;
    private RecyclerView userRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressIndicator = findViewById(R.id.progressBarUser);
        userRecyclerView = findViewById(R.id.userRecyclerView);

        String getUsersStr = SearchValues.searchUsers;
        // might need to send the recycler view also into this
        BackgroundTaskHandler backgroundTaskHandler = new BackgroundTaskHandler(MainActivity.this, getUsersStr, progressIndicator, userRecyclerView);
        executorService.execute(backgroundTaskHandler);
    }
}