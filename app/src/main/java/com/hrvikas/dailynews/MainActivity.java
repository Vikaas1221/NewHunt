package com.hrvikas.dailynews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static String url=" https://newsapi.org/v2/top-headlines?country=in&category=technology&apiKey=3b29f2f4b09a4171915b87cc2be01628";
    RecyclerView CatergoryrecyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView homeScreenRecycler;
    ArrayList<CatergoryModel> arrayList;
    ArrayList<homeScreenModel> screenModelArrayList;
    ImageView refreshbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CatergoryrecyclerView=findViewById(R.id.categoryRecycler);
        refreshbutton=findViewById(R.id.refresh);
        homeScreenRecycler=findViewById(R.id.homeScrrenView);
        arrayList=new ArrayList<>();
        screenModelArrayList=new ArrayList<>();
        CatergoryrecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        CatergoryrecyclerView.setHasFixedSize(true);

        Resources resources=getResources();
        String[] tempStrings=resources.getStringArray(R.array.category);
        for (int i=0;i<tempStrings.length;i++)
        {
            CatergoryModel model=new CatergoryModel(tempStrings[i]);
            arrayList.add(model);
        }
        adapter=new Myadapter(this,arrayList);
        CatergoryrecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        homeScreenRecycler.setLayoutManager(new LinearLayoutManager(this));
        homeScreenRecycler.setHasFixedSize(true);
        ParseJsonData();
        refreshbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(MainActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
    public void ParseJsonData()
    {
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading..");
        progressDialog.show();
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        try
                        {
                            JSONArray jsonArray=response.getJSONArray("articles");
                            for (int i=0;i<jsonArray.length();i++)
                            {
                                JSONObject jsonObject= (JSONObject) jsonArray.get(i);
                                String jTitle=jsonObject.getString("title");
                                String jAuthor=jsonObject.getString("author");
                                String jDate=jsonObject.getString("publishedAt");
                                String jImageurl=jsonObject.getString("urlToImage");
                                String jUrl=jsonObject.getString("url");
                                String jContent=jsonObject.getString("content");
                                homeScreenModel screenModel1=new homeScreenModel(jTitle,jAuthor,jDate,jImageurl,jUrl,jContent);
                                screenModelArrayList.add(screenModel1);
                                adapter=new homeAdapter(getApplicationContext(),screenModelArrayList);
                                homeScreenRecycler.setAdapter(adapter);


                            }
                            progressDialog.dismiss();
                        } catch (JSONException e)
                        {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);


    }
}
