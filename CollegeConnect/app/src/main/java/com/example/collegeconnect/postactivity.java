package com.example.collegeconnect;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.collegeconnect.Model.Post;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class postactivity extends AppCompatActivity {

    private ImageView close,imageadd;
    private Uri imageuri;
    private String imageurl;
    private TextView post,description;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    Bitmap bitmap;
    private DatabaseReference reference;
    private StorageReference storageReference;
    ProgressDialog pd;
    String downloadurl = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        close = findViewById(R.id.postclose);
        imageadd = findViewById(R.id.imageadd);
        post = findViewById(R.id.postsend);
        description = findViewById(R.id.postdescription);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        reference = FirebaseDatabase.getInstance().getReference();
        storageReference = FirebaseStorage.getInstance().getReference();

        pd = new ProgressDialog(this);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(postactivity.this,MainActivity.class));
//                finish();
                Intent intent = new Intent(postactivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
//                finish();
            }
        });

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(description.getText().toString().isEmpty()){
                    description.setError("Empty !!!");
                }else if (bitmap == null){
                    uploaddata();
                }else{
                    uploadimage();
                }
            }
        });

        imageadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //open Gallary
                Intent openGallaryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(openGallaryIntent,1000);
            }
        });

    }

    private void uploadimage() {

        pd.setMessage("Uploading");
        pd.show();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,50,baos);
        byte[] finalimg = baos.toByteArray();
        final StorageReference filePath;
        filePath = storageReference.child("posts").child(finalimg+"jpg");
        final UploadTask uploadTask = filePath.putBytes(finalimg);
        uploadTask.addOnCompleteListener(postactivity.this, new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if(task.isSuccessful()){
                    uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    downloadurl = String.valueOf(uri);
                                    uploaddata();

                                }
                            });
                        }
                    });

                }else{
                    pd.dismiss();
                    Toast.makeText(postactivity.this, "Something went wrong! Try again", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void uploaddata() {
        reference = reference.child("posts");
        final String postid = reference.push().getKey();

        String title = description.getText().toString();
        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        Post postdata = new Post(title,downloadurl,postid,userID);
        reference.child(postid).setValue(postdata).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                pd.dismiss();
                Toast.makeText(postactivity.this, "Data Uploaded", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(postactivity.this,MainActivity.class));
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(postactivity.this, "something went wrong!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000){
            if(resultCode == Activity.RESULT_OK){
                imageuri = data.getData();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),imageuri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                imageadd.setImageURI(imageuri);
            }
        }else{
            Toast.makeText(this, "Try Again", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(postactivity.this,MainActivity.class));
            finish();
        }
    }


}