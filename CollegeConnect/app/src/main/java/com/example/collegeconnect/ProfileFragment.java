package com.example.collegeconnect;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.auth.User;

import java.util.Objects;
import java.util.concurrent.Executor;


public class ProfileFragment extends Fragment {

    TextView name,email,phone,enrollment,department;
    FirebaseDatabase database;
    DatabaseReference userRef;
    FirebaseAuth fAuth;
    FirebaseUser fUser;
    FirebaseFirestore fStore;
    String userId;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        name = view.findViewById(R.id.Name1);
        email = view.findViewById(R.id.Email1);
        phone = view.findViewById(R.id.MobileNo1);
        enrollment = view.findViewById(R.id.Enrollment1);
        department = view.findViewById(R.id.Department1);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userId = fAuth.getCurrentUser().getUid();
        DocumentReference documentReference = fStore.collection("users").document(userId);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documetSnapshot, @Nullable FirebaseFirestoreException error) {
                phone.setText(documetSnapshot.getString("phone"));
                name.setText(documetSnapshot.getString("fullName"));
                enrollment.setText(documetSnapshot.getString("enrollment"));
                department.setText(documetSnapshot.getString("department"));
                email.setText(documetSnapshot.getString("email"));
            }
        });

        return view;
    }


}