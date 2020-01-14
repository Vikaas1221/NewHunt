package com.hrvikas.dailynews;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class homeAdapter extends RecyclerView.Adapter<homeAdapter.ViewHolder>
{
    Context context;
    ArrayList<homeScreenModel> arrayList;
    Context ctx;
    public homeAdapter(Context context,ArrayList<homeScreenModel> arrayList)
    {
        this.context=context;
        this.arrayList=arrayList;
    }

    @NonNull
    @Override
    public homeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        ctx=parent.getContext();
        View view= LayoutInflater.from(context)
                .inflate(R.layout.homescreenlayout,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull homeAdapter.ViewHolder holder, final int position)
    {
        homeScreenModel hmodel=arrayList.get(position);
        holder.mtitle.setText(hmodel.getTitle());
        holder.mdate.setText(hmodel.getDate());
        Picasso.with(context).load(hmodel.getImage()).into(holder.mImage);
        holder.layout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                homeScreenModel model=arrayList.get(position);
                Intent intent=new Intent(ctx,ContentActivity.class);
                intent.putExtra("imgurl",model.getImage());
                intent.putExtra("title",model.getTitle());
                intent.putExtra("author",model.getAuthor());
                intent.putExtra("date",model.getDate());
                intent.putExtra("content",model.getContent());
                intent.putExtra("url",model.getUrl());
                ctx.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView mtitle,mdate;
        ImageView mImage;
        LinearLayout layout;
        RelativeLayout mRelative;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            mtitle=itemView.findViewById(R.id.newsTitle);
            layout=itemView.findViewById(R.id.a1);
            mdate=itemView.findViewById(R.id.datePublished);
            mImage=itemView.findViewById(R.id.newsImage);
            mRelative=itemView.findViewById(R.id.homelayout);

        }
    }
}
