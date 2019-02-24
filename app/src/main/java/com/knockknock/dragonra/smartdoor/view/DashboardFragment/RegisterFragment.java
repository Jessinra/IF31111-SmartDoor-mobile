package com.knockknock.dragonra.smartdoor.view.DashboardFragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.auth.data.model.User;
import com.google.api.LogDescriptor;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.knockknock.dragonra.smartdoor.R;
import com.knockknock.dragonra.smartdoor.UserMember;

import java.util.LinkedList;

/**
 * A fragment with a Google +1 button.
 * Activities that contain this fragment must implement the
 * {@link RegisterFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RegMemAdapter mAdapter;
    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    private static LinkedList<String> mWordList = new LinkedList<>();
    private int count;
    private static final String TAG = "RegisterFragment";

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.wtf(TAG, "onCreateView: HALO");

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_register, container, false);

        // Get a handle to the RecyclerView.
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview);

        // Give the RecyclerView a default layout manager.

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));


        DatabaseReference myRef = mDatabase.getReference();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.wtf(TAG, "Hi im here");
                count = 0;
                mWordList.clear();
                for (DataSnapshot ds : dataSnapshot.child("member").getChildren()){
                    count++;
                    Log.wtf(TAG, "total"+count);

//                    UserMember user = new UserMember();
//                    user.setName(ds.getValue(UserMember.class).getName());
                    mWordList.addLast(ds.getValue(UserMember.class).getName());
                }
                for (int i = 0; i < count; i++) {
                    Log.wtf(TAG, "string"+mWordList.get(i));
                }
                mAdapter = new RegMemAdapter(getActivity(), mWordList);
                // Connect the adapter with the RecyclerView.
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        // Connect the adapter with the RecyclerView.
        mRecyclerView.setAdapter(mAdapter);

        FloatingActionButton fab = rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                UserMember user = new UserMember("user"+count, "1");
                DatabaseReference myRef = mDatabase.getReference();
                myRef.child("member").child(""+count).setValue(user);
            }
        });

        return rootView;
    }
    
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


}
