package com.knockknock.dragonra.smartdoor.view.DashboardFragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.knockknock.dragonra.smartdoor.R;
import com.knockknock.dragonra.smartdoor.view.Adapter.HistoryViewAdapter;

import java.util.ArrayList;

public class HistoryFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private HistoryViewAdapter mAdapter;

    public HistoryFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static HistoryFragment newInstance(String param1, String param2) {
        HistoryFragment fragment = new HistoryFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.history_recycle_view);
        recyclerView.setHasFixedSize(true);

        /* ================================================================================== */
        /* ====================   Example to fill history page ============================== */
        /* ================================================================================== */

        // 1. dataset
        ArrayList<String> dummyDataset = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            dummyDataset.add("22 / 02 / 2019      04:30      Home " + Integer.toString(i));
        }

        // 2. Setup page view
        layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        mAdapter = new HistoryViewAdapter(dummyDataset);
        recyclerView.setAdapter(mAdapter);

        for (int i = 0; i < 50; i++) {
            dummyDataset.add("22 / 02 / 2019      04:30      Office " + Integer.toString(i));
        }
        mAdapter.notifyItemRangeChanged(50, 50);

        for (int i = 25; i < 50; i++) {
            dummyDataset.set(i, "06 / 02 / 2019      07:37      Laboratory " + Integer.toString(i));
        }
        mAdapter.notifyItemRangeChanged(25, 25);

        /* ================================================================================== */
        /* ================================================================================== */
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
