package com.knockknock.dragonra.smartdoor.activity.DashboardFragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.knockknock.dragonra.smartdoor.R;
import com.knockknock.dragonra.smartdoor.controller.Services.RegisterUserService;
import com.knockknock.dragonra.smartdoor.model.UserMember;
import com.knockknock.dragonra.smartdoor.view.Adapter.RegisterUserPageAdapter;

import java.util.LinkedList;


public class RegisterFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RegisterUserPageAdapter mAdapter;
    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    private static LinkedList<String> mWordList = new LinkedList<>();
    private int count;
    private static final String TAG = "RegisterFragment";

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.dashboard_user, container, false);

        // Get a handle to the RecyclerView.
        mRecyclerView = rootView.findViewById(R.id.register_recycler_view);

        // Give the RecyclerView a default layout manager.
        if (getActivity() != null) {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));
        }

        DatabaseReference myRef = mDatabase.getReference();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                count = 0;
                mWordList.clear();
                for (DataSnapshot ds : dataSnapshot.child("member").getChildren()) {
                    count++;

//                    UserMember user = new UserMember();
//                    user.setName(ds.getValue(UserMember.class).getName());
                    mWordList.addLast(ds.getValue(UserMember.class).getName());
                }

                mAdapter = new RegisterUserPageAdapter(getActivity(), mWordList);
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
//                count++;
//                UserMember user = new UserMember("user"+count, "1");
//                DatabaseReference myRef = mDatabase.getReference();
//                myRef.child("member").child(""+count).setValue(user);
                Intent intent = new Intent(getContext(), RegisterUserService.class);

                startActivity(intent);
            }
        });

        return rootView;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


}
