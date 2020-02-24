package com.example.parseinstagram;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import org.w3c.dom.Text;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {
    Context context;
    List<Post> posts;

    public PostAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    // Clean all elements of the recycler
    public void clear(){
        posts.clear();
        notifyDataSetChanged();
    }

    // Add a list of items
    public void addAll(List<Post> postList){
        posts.addAll(postList);
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPost;
        TextView etUsernameDisplay;
        TextView etDescriptionDisplay;
        TextView etCreatedAt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPost = itemView.findViewById(R.id.ivPost);
            etDescriptionDisplay = itemView.findViewById(R.id.etDescriptionDisplay);
            etUsernameDisplay= itemView.findViewById(R.id.etUsernameDisplay);
            etCreatedAt = itemView.findViewById(R.id.etCreatedAt);
        }

        public void bind(Post post){
            etUsernameDisplay.setText(post.getUser().getUsername());
            etDescriptionDisplay.setText(post.getDescription());
            etCreatedAt.setText(post.getCreatedAt().toString());
            ParseFile image= post.getImage();
            if (image != null){
                Glide.with(context).load(image.getUrl()).into(ivPost);
            }
        }
    }

}
