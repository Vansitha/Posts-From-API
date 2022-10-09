package curtin.edu.assignment1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import curtin.edu.assignment1.data.UsersList;
import curtin.edu.assignment1.models.User;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserCardViewHolder> {

    private Context context;
    private UsersList usersList;

    public UsersAdapter(Context context, UsersList usersList) {
        this.context = context;
        this.usersList = usersList;
    }

    @NonNull
    @Override
    public UserCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_card, parent, false);
        return new UsersAdapter.UserCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserCardViewHolder holder, int position) {
        User user = usersList.getUser(position);
        String username = user.getUsername();
        holder.displayUsername.setText(username);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProfileActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(User.USER_OBJECT, user);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return usersList.getSize();
    }

    public static class UserCardViewHolder extends RecyclerView.ViewHolder {

        TextView displayUsername;

        public UserCardViewHolder(@NonNull View itemView) {
            super(itemView);
            displayUsername = itemView.findViewById(R.id.postCardTitle);
        }
    }
}
