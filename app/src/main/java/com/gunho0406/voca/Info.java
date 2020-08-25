package com.gunho0406.voca;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import com.gunho0406.voca.R;

public class Info extends AppCompatActivity {

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        ImageView profileimg = (ImageView) findViewById(R.id.profileimg);
        profileimg.setBackground(new ShapeDrawable(new OvalShape()));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            profileimg.setClipToOutline(true);
        }
        ImageButton mailtobtn = (ImageButton) findViewById(R.id.mailto);
        mailtobtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent emailIntent = new Intent(Intent.ACTION_SEND);

                try {
                    emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"gunho0406@gmail.com"});

                    emailIntent.setType("text/html");
                    emailIntent.setPackage("com.google.android.gm");
                    if(emailIntent.resolveActivity(getPackageManager())!=null)
                        startActivity(emailIntent);

                    startActivity(emailIntent);
                } catch (Exception e) {
                    e.printStackTrace();

                    emailIntent.setType("text/html");
                    emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"gunho0406@gmail.com"});

                    startActivity(Intent.createChooser(emailIntent, "Send Email"));
                }
            }
        });

        ImageButton fbbtn = (ImageButton) findViewById(R.id.facebook);
        fbbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/profile.php?id=100008992359170"));
                startActivity(intent);
            }
        });
        //dddd
    }
}
