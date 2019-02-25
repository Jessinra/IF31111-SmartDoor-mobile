package com.knockknock.dragonra.smartdoor.view.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.knockknock.dragonra.smartdoor.R;

import java.util.LinkedList;

public class RegisterUserPageAdapter extends RecyclerView.Adapter<RegisterUserPageAdapter.RegViewHolder> {

    private final LinkedList<String> mMemList;
    private LayoutInflater mInflater;

    public RegisterUserPageAdapter(Context context,
                                   LinkedList<String> memList) {
        mInflater = LayoutInflater.from(context);
        this.mMemList = memList;
    }
    @NonNull
    @Override
    public RegisterUserPageAdapter.RegViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.component_user_record, parent, false);
        return new RegViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull RegisterUserPageAdapter.RegViewHolder holder, int position) {
        String mCurrent = mMemList.get(position);
        holder.wordItemView.setText(mCurrent);
    }

    @Override
    public int getItemCount() {
        return mMemList.size();
    }

    class RegViewHolder extends RecyclerView.ViewHolder {
        final TextView wordItemView;
        final RegisterUserPageAdapter mAdapter;

        RegViewHolder(View itemView, RegisterUserPageAdapter adapter) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.word);
            this.mAdapter = adapter;
        }
    }
}
