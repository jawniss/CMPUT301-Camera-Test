package com.example.simpleparadox.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.URL;

public class ImageFromFirebase extends AppCompatActivity {

    ImageView firebaseImage;
    DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_from_firebase);

        firebaseImage = findViewById( R.id.firebase_image );

        reff = FirebaseDatabase.getInstance().getReference().child("Image Uploads").child("Hi");

        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                String url = dataSnapshot.child("imageUrl").getValue().toString();
//                Drawable imageToDraw = LoadImageFromWebOperations( url );
                String url = "https://firebasestorage.googleapis.com/v0/b/project-demo-70f7b.appspot.com/o/Image%20Uploads%2F1584129100555.jpg?alt=media&token=92736b9f-1633-45f7-b160-7da35d66937f";
                Log.d("Firebaseee", url);

//                mImageView.setImageDrawable( imageToDraw );

                Picasso.get()
                        .load( url )
                        .into( firebaseImage );
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }



    public static Drawable LoadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            return null;
        }
    }


}
