package com.hrvikas.dailynews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CategoryContent extends AppCompatActivity
{
    String sUrl;
    ArrayList<homeScreenModel> arrayList=new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    ImageView refreshButton;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_content);
        Bundle b=getIntent().getExtras();
        int position=b.getInt("pos");
        recyclerView=findViewById(R.id.homeScrrenView2);
        refreshButton=findViewById(R.id.refresh);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        switch (position)
        {
            case 0:
                jsonParsing("business");
                setTitle("Business");
                break;
            case 1:
                jsonParsing("entertainment");
                break;
            case 2:
                jsonParsing("health");
                break;
            case 3:
                jsonParsing("science");
                break;
            case 4:
                jsonParsing("sports");
                break;
        }
        refresh(position);



    }

    public void refresh(final int pos)
    {
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (pos==0)
                {
                    jsonParsing("business");

                }
                else if (pos==1)
                {
                    jsonParsing("entertainment");
                }
                else if (pos==2)
                {
                    jsonParsing("health");
                }
                else if (pos==3)
                {
                    jsonParsing("science");
                }
                else if (pos==4)
                {
                    jsonParsing("sports");
                }

            }
        });
    }
    public void jsonParsing(String s)
    {
          String CategoryUrl="https://newsapi.org/v2/top-headlines?country=in&category="+s+"&apiKey=3b29f2f4b09a4171915b87cc2be01628";
        final ProgressDialog dialog=new ProgressDialog(this);
        dialog.setMessage("Loading..");
        dialog.show();
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, CategoryUrl, null,
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
                                arrayList.add(screenModel1);
                                adapter=new homeAdapter(getApplicationContext(),arrayList);
                               recyclerView.setAdapter(adapter);
                               adapter.notifyDataSetChanged();


                            }
                            dialog.dismiss();
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
