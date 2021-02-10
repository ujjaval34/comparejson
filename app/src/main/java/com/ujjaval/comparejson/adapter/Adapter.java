package com.ujjaval.comparejson.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.ujjaval.comparejson.R;
import com.ujjaval.comparejson.model.FinalList;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private List<FinalList> finalList;
    private Context context;

    public Adapter(List<FinalList> finalList, Context context) {
        this.finalList = finalList;
        this.context = context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.final_list, parent, false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holders, int position) {
        final MyViewHolder holder = holders;
        FinalList model = finalList.get(position);

        holder.tvcompany.setText(model.getCompanyName());
        holder.tvtitle.setText(model.getTitle());
        holder.tvpost.setText(model.getPost());


    }

    @Override
    public int getItemCount() {
        return finalList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvcompany, tvtitle, tvpost;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvcompany=itemView.findViewById(R.id.tvcompany);
            tvtitle=itemView.findViewById(R.id.tvtitle);
            tvpost=itemView.findViewById(R.id.tvpost);




        }


    }
}
