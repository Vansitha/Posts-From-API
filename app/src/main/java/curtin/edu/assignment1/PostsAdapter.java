package curtin.edu.assignment1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import curtin.edu.assignment1.data.PostList;
import curtin.edu.assignment1.models.Post;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostCardViewHolder> {

    private Context context;
    private PostList postList;

    public PostsAdapter(Context context, PostList postList) {
        this.context = context;
        this.postList = postList;
    }

    @NonNull
    @Override
    public PostCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.post_card, parent, false);
        return new PostsAdapter.PostCardViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull PostCardViewHolder holder, int position) {
        Post post = postList.getPost(position);
        holder.title.setText(post.getTitle());
        holder.bodyText.setText(post.getBody());;
        holder.userName.setText("Posted by: " + PostList.getPostOwnerUsername());

    }

    @Override
    public int getItemCount() {
        return postList.getSize();
    }

    public static class PostCardViewHolder extends RecyclerView.ViewHolder {

        TextView userName;
        TextView title;
        TextView bodyText;

        public PostCardViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.postCardUsername);
            title = itemView.findViewById(R.id.postCardTitle);
            bodyText = itemView.findViewById(R.id.postCardBodyText);
        }
    }


}
