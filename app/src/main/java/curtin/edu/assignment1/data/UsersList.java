package curtin.edu.assignment1.data;

import java.util.ArrayList;
import curtin.edu.assignment1.models.User;

public class UsersList {

    private ArrayList<User> userList;

    public UsersList() {
        userList = new ArrayList<>();
    }

    public void addUser(User user) {
        userList.add(user);
    }

    public User getUser(int position) {
        return userList.get(position);
    }

    public int getSize() {
        return userList.size();
    }

}
