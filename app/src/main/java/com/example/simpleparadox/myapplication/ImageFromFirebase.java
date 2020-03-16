package com.example.simpleparadox.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class ImageFromFirebase extends AppCompatActivity {

    ImageView firebaseImage;
    DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_from_firebase);

        firebaseImage = findViewById( R.id.firebase_image );

        reff = FirebaseDatabase.getInstance().getReference().child("Image Uploads").child("Bye");

        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String url = dataSnapshot.child("imageUrl").getValue().toString();





//                Uri myUri = Uri.parse( url );



//                Drawable imageToDraw = LoadImageFromWebOperations( url );
//                String url = "https://firebasestorage.googleapis.com/v0/b/project-demo-70f7b.appspot.com/o/Image%20Uploads%2F1584129100555.jpg?alt=media&token=92736b9f-1633-45f7-b160-7da35d66937f";
                Log.d("Firebase", url);

//                mImageView.setImageDrawable( imageToDraw );

                Picasso.get()
                        .load( url )
                        .into( firebaseImage );
//                firebaseImage.setImageURI( myUri );
//                firebaseImage.setImageDrawable( LoadImageFromWebOperations("https://firebasestorage.googleapis.com/v0/b/project-demo-70f7b.appspot.com/o/Image%20Uploads%2F1584396487619.jpg?alt=media&token=40c5d671-5bc0-4acd-9a1b-0c5d296519a0") );
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


    private Bitmap getImageBitmap(String url) {
        Bitmap bm = null;
        try {
            URL aURL = new URL(url);
            URLConnection conn = aURL.openConnection();
            conn.connect();
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            bm = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();
        } catch (IOException e) {
            Log.e("Uri bitmap: ", "Error getting bitmap", e);
        }
        return bm;
    }


}
