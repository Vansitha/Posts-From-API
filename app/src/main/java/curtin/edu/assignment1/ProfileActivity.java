package curtin.edu.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import curtin.edu.assignment1.data.PostList;
import curtin.edu.assignment1.models.User;

public class ProfileActivity extends AppCompatActivity {

    User selectedUser;
    TextView username;
    TextView phone;
    TextView email;
    TextView website;
    TextView street;
    TextView suite;
    TextView city;
    TextView zipcode;
    TextView company;
    TextView catchPhrase;
    TextView business;
    Button showPostsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        // getting a handle on all necessary UI elements
        getUIComponents();

        // get the selected user object from the main activity
        Intent intent = getIntent();
        selectedUser = (User) intent.getSerializableExtra(User.USER_OBJECT);

        // display selected user data
        setUIComponentsData();

        showPostsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // trigger next activity
                Intent intent = new Intent(ProfileActivity.this, PostsActivity.class);
                PostList.setUserIDofOwner(selectedUser.getUserId());
                PostList.setPostOwnerUsername(selectedUser.getUsername());
                startActivity(intent);
            }
        });


    }

    private void getUIComponents() {
        username = findViewById(R.id.profileUserName);
        phone = findViewById(R.id.profilePhoneNum);
        email = findViewById(R.id.profileEmail);
        website = findViewById(R.id.profileWebsite);
        street = findViewById(R.id.profileStreet);
        suite = findViewById(R.id.profileSuite);
        city = findViewById(R.id.profileCity);
        zipcode = findViewById(R.id.profileZipCode);
        company = findViewById(R.id.profileCompany);
        catchPhrase = findViewById(R.id.profileCatchPhrase);
        business = findViewById(R.id.profileBusiness);

        // show posts button to trigger next activity
        showPostsBtn = findViewById(R.id.showPostsBtn);

    }

    private void setUIComponentsData() {
        username.setText(selectedUser.getUsername());
        phone.setText(selectedUser.getPhone());
        email.setText(selectedUser.getEmail());
        website.setText(selectedUser.getWebsite());
        street.setText(selectedUser.getStreet());
        suite.setText(selectedUser.getSuite());
        city.setText(selectedUser.getCity());
        zipcode.setText(selectedUser.getZipcode());
        company.setText(selectedUser.getCompanyName());
        catchPhrase.setText(selectedUser.getCompanyCatchPhrase());
        business.setText(selectedUser.getBusiness());
    }
}