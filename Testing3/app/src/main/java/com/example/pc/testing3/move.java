package com.example.pc.testing3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;


public class move extends AppCompatActivity {
Button btnMove;
ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move);
        Toolbar toolbar=findViewById(R.id.toolbar);
        btnMove=(Button)findViewById(R.id.btn_move);
        image=(ImageView) findViewById(R.id.img);
         btnMove.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                moveImage();
             }

             private void moveImage() {
                 Animation img=new TranslateAnimation(Animation.ABSOLUTE,150,Animation.ABSOLUTE,Animation.ABSOLUTE);
                 img.setDuration(3000);
                 img.setFillAfter(true);
                 img.setRepeatCount(5);
                 image.startAnimation(img);
             }
         });

    }
}
