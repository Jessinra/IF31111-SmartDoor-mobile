package com.knockknock.dragonra.smartdoor.view.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.knockknock.dragonra.smartdoor.R;

import java.util.LinkedList;

public class RegisterUserPageAdapter extends RecyclerView.Adapter<RegisterUserPageAdapter.RegViewHolder> {

    private final LinkedList<String> mMemList;
    private LayoutInflater mInflater;
    private static final String TAG = "RegisterUserPageAdapter";
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

    class RegViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final TextView wordItemView;
        final RegisterUserPageAdapter mAdapter;

        RegViewHolder(View itemView, RegisterUserPageAdapter adapter) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.word);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.wtf(TAG, "onClick: testing");
            int mPosition = getLayoutPosition();
            String element = mMemList.get(mPosition);
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
            Query removeQuery = ref.child("member").orderByChild("name").equalTo(element);
            removeQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot ds: dataSnapshot.getChildren()) {
                        ds.getRef().removeValue();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.e(TAG, "onCancelled", databaseError.toException());
                }
            });
        }
    }
}
