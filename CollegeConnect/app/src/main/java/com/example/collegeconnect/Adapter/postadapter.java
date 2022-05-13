package com.example.collegeconnect.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collegeconnect.Model.Post;
import com.example.collegeconnect.Model.User;
import com.example.collegeconnect.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class postadapter extends RecyclerView.Adapter<postadapter.Viewholder> {

    private Context mContext;
    private ArrayList<Post> mposts;


    public postadapter(Context mContext, ArrayList<Post> mposts) {
        this.mContext = mContext;
        this.mposts = mposts;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.postitem,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {

//       FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        Post post = mposts.get(position);
        Picasso.get().load(post.getImageadd()).into(holder.postimage);
        holder.description.setText(post.getDescription());

//        FirebaseDatabase.getInstance().getReference().child("users").child(post.getUserID()).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                User user = snapshot.getValue(User.class);
//                holder.username.setText(user.getfullName());
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });


    }

    @Override
    public int getItemCount() {
        return mposts.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{

        public ImageView postimage;
        public TextView description,username;


        public Viewholder(@NonNull View itemView) {
            super(itemView);

            postimage = itemView.findViewById(R.id.postimage);
            description = itemView.findViewById(R.id.posttext);
            username = itemView.findViewById(R.id.username);


        }
    }

}
