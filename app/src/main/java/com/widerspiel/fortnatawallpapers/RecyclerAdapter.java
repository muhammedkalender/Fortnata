package com.widerspiel.fortnatawallpapers;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    interface ItemListener {
        void onItemClick(View v, int position);
    }

    List<preview> list;
    ItemListener itemListener;

    public class ViewHolder extends RecyclerView.ViewHolder {


        public TextView name;
        public ImageView thumbnail;
        public RelativeLayout card_view;
        public TextView tvNew;

        public ViewHolder(View view) {
            super(view);

            card_view = (RelativeLayout) view.findViewById(R.id.rlPreview);
            name = (TextView) view.findViewById(R.id.ivItemName);
            thumbnail = (ImageView) view.findViewById(R.id.ivItemImage);
            tvNew = (TextView) view.findViewById(R.id.ivItemNew);
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.preview, parent, false);
        final ViewHolder view_holder = new ViewHolder(v);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemListener.onItemClick(v, view_holder.getPosition());
            }
        });

        return view_holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            holder.name.setText(list.get(position).name());
            //  holder.alt.setText(list_person.get(position).alt());

            if (list.get(position).isNew()) {
                holder.tvNew.setVisibility(View.VISIBLE);
            }

            Glide.with(lib.context).load(list.get(position).uri()).into(holder.thumbnail);
        } catch (IllegalArgumentException ex) {
            lib.err(450, ex);
        } catch (Exception ex) {
            lib.err(458, ex);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public RecyclerAdapter(List<preview> list, ItemListener ItemListener) {
        this.list = list;
        this.itemListener = ItemListener;
    }


}
