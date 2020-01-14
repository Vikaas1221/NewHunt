package com.hrvikas.dailynews;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Myadapter extends RecyclerView.Adapter<Myadapter.ViewHolder>
{
    Context context;
    Context ctx;
    ArrayList<CatergoryModel> arrayList;
    public Myadapter(Context context,ArrayList<CatergoryModel> arrayList)
    {
        this.context=context;
        this.arrayList=arrayList;
    }

    @NonNull
    @Override
    public Myadapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        ctx=parent.getContext();
        View view= LayoutInflater.from(context)
                .inflate(R.layout.single_category_layout,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Myadapter.ViewHolder holder, final int position)
    {
        final CatergoryModel catergoryModel=arrayList.get(position);
        holder.textView.setText(catergoryModel.getCategoryText());
        holder.layout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (position==0)
                {
                    Intent intent=new Intent(ctx,CategoryContent.class);
                    intent.putExtra("pos",position);
                    ctx.startActivity(intent);

                }
                if (position==1)
                {
                    Intent intent=new Intent(ctx,CategoryContent.class);
                    intent.putExtra("pos",position);
                    ctx.startActivity(intent);

                }
                if (position==2)
                {
                    Intent intent=new Intent(ctx,CategoryContent.class);
                    intent.putExtra("pos",position);
                    ctx.startActivity(intent);

                }
                if (position==3)
                {
                    Intent intent=new Intent(ctx,CategoryContent.class);
                    intent.putExtra("pos",position);
                    ctx.startActivity(intent);

                }
                if (position==4)
                {
                    Intent intent=new Intent(ctx,CategoryContent.class);
                    intent.putExtra("pos",position);
                    ctx.startActivity(intent);

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView textView;
        LinearLayout layout;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            textView=itemView.findViewById(R.id.categoryText);
            layout=itemView.findViewById(R.id.a2);
        }
    }
}
