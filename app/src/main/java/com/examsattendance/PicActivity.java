package com.examsattendance;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

public class PicActivity extends AppCompatActivity {
ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic);
     //   img=findViewById(R.id.image_pic);
        me();
    }
    public void me(){
//        Picasso.get()
//                .load("https://cameraman.ng/wp-content/uploads/2018/01/rachael-aluko-300x300.jpg")
//                .networkPolicy(NetworkPolicy.OFFLINE)
//                .into(img);
        // Create glide request manager
        RequestManager requestManager = Glide.with(this);
// Create request builder and load image.
        RequestBuilder requestBuilder = requestManager.load("https://cameraman.ng/wp-content/uploads/2018/01/rachael-aluko-300x300.jpg");
// Show image into target imageview.
        requestBuilder.into(img);
    }
}
