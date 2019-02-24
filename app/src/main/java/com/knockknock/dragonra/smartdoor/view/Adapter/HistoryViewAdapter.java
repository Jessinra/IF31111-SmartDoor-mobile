package com.knockknock.dragonra.smartdoor.view.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.knockknock.dragonra.smartdoor.R;

import java.util.ArrayList;

public class HistoryViewAdapter extends RecyclerView.Adapter<HistoryViewAdapter.HistoryViewHolder> {

    private ArrayList<String> historyData;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder

    public HistoryViewAdapter(ArrayList<String> historyData) {
        this.historyData = historyData;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view

        TextView textView = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.history_text_layout, parent, false);

        return new HistoryViewHolder(textView);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewAdapter.HistoryViewHolder historyViewHolder, int position) {

        // - get element from your history data at this position
        // - replace the contents of the view with that element
        historyViewHolder.textView.setText(historyData.get(position));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return historyData.size();
    }


    /* ============================================================= */
    /*                      History view holder                      */
    /* ============================================================= */

    class HistoryViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case

        TextView textView;

        HistoryViewHolder(TextView v) {
            super(v);
            textView = v;
        }
    }
}
