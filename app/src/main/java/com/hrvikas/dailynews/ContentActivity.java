package com.hrvikas.dailynews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.squareup.picasso.Picasso;

public class ContentActivity extends AppCompatActivity {
    ImageView imageView;
    TextView t1,t2,t3,t4,t5;
    String s;
    Button shareButton;
     String cImageurl;
     String cUrl;
     String cTitle;
     AdView adView;
    char c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        MobileAds.initialize(this,"ca-app-pub-1155294506749467~3407513346");
        adView=findViewById(R.id.adView);
        AdRequest adRequest=new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        imageView=findViewById(R.id.im);
        t1=findViewById(R.id.Title);
        t2=findViewById(R.id.publisher);
        t3=findViewById(R.id.Content1);
        t4=findViewById(R.id.Date);
        t5=findViewById(R.id.t5);
        shareButton=findViewById(R.id.share);
        Bundle bundle=getIntent().getExtras();
        cTitle=bundle.getString("title");
        String cAuthor=bundle.getString("author");
        final String cContent=bundle.getString("content");
        String cDate=bundle.getString("date");
        cImageurl=bundle.getString("imgurl");
         cUrl=bundle.getString("url");
        Picasso.with(this).load(cImageurl).into(imageView);
        t1.setText(cTitle);
        if (cAuthor.equals("null"))
        {
            t2.setText("");
        }
        else {
            t2.setText(cAuthor);
        }
        t3.setText("");

        char[] ar=cContent.toCharArray();
        for (int i=0;i<ar.length;i++)
        {

            t3.setText(t3.getText()+""+ar[i]);

        }


        t4.setText(cDate);
        t5.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(ContentActivity.this,webViewActivity.class);
                intent.putExtra("URL",cUrl);
                startActivity(intent);
            }
        });
        shareButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                share();
            }
        });

    }
    public void share()
    {
        Intent intent=new Intent();
        intent.setAction(Intent.ACTION_SEND);

        intent.putExtra(Intent.EXTRA_TITLE,"News Hunt");
        intent.putExtra(Intent.EXTRA_TEXT,cTitle);
        intent.putExtra(Intent.EXTRA_TEXT,cUrl);
        intent.setType("text/plain");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);


    }
}
