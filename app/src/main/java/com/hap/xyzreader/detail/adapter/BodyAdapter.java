package com.hap.xyzreader.detail.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hap.xyzreader.R;
import com.hap.xyzreader.detail.holder.BodyHolder;
import com.hap.xyzreader.detail.holder.EmptyHolder;
import com.hap.xyzreader.detail.holder.PhotoHolder;
import com.hap.xyzreader.detail.model.BodyItem;
import com.hap.xyzreader.detail.model.BodyType;

import java.util.ArrayList;

/**
 * Created by luis on 6/7/18.
 */

public class BodyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<BodyItem> bodies = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        final View itemView;
        final RecyclerView.ViewHolder holder;
        final BodyType bodyType = BodyType.fromType(viewType);
        switch (bodyType) {
            case PHOTO_HEADER:
                itemView = layoutInflater.inflate(R.layout.photo_holder, parent, false);
                holder = new PhotoHolder(itemView);
                break;
            case MESSAGE:
                itemView = layoutInflater.inflate(R.layout.body_holder, parent, false);
                holder = new BodyHolder(itemView);
                break;
            default:
                itemView = layoutInflater.inflate(R.layout.empty_holder, parent, false);
                holder = new EmptyHolder(itemView);
                break;
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final BodyType bodyType = BodyType.fromType(getItemViewType(position));
        final BodyItem bodyItem = bodies.get(position);
        switch (bodyType) {
            case PHOTO_HEADER:
                ((PhotoHolder) holder).setupView(bodyItem.getPhoto(), bodyItem.getTitle(), bodyItem.getDate(), bodyItem.getAuthor());
                break;
            case MESSAGE:
                ((BodyHolder) holder).setupBody(bodyItem.getMessage());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return bodies.size();
    }

    @Override
    public int getItemViewType(int position) {
        final BodyItem bodyItem = bodies.get(position);
        return bodyItem.getBodyType().getType();
    }

    public void addAll(final ArrayList<BodyItem> bodies) {
        this.bodies.clear();
        this.bodies.addAll(bodies);
        notifyDataSetChanged();
    }
}
