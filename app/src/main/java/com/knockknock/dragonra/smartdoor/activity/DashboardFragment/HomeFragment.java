package com.knockknock.dragonra.smartdoor.activity.DashboardFragment;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.knockknock.dragonra.smartdoor.R;
import com.knockknock.dragonra.smartdoor.activity.LogoutActivity;
import com.knockknock.dragonra.smartdoor.controller.Manager.DashboardManager;
import com.knockknock.dragonra.smartdoor.controller.Manager.HistoryManager;

import java.util.concurrent.Callable;

public class HomeFragment extends Fragment implements View.OnClickListener, HomeDialogFragment.NoticeDialogListener {

    private HomeViewModel mViewModel;
    private View parentView;
    private String userToken;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dashboard_home, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.d("ACTIVITY_START", "onActivityCreated HomeFragment");
        super.onActivityCreated(savedInstanceState);

        mViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        parentView = view;
        userToken = getString(R.string.userToken);

        setupHomeViewOnClickListener(view);
        DashboardManager.fetchDashboard(view, userToken);
    }

    private void setupHomeViewOnClickListener(@NonNull View view) {
        view.findViewById(R.id.button_sign_out).setOnClickListener(this);
        view.findViewById(R.id.card_view01).setOnClickListener(this);
        view.findViewById(R.id.card_view02).setOnClickListener(this);
        view.findViewById(R.id.card_view03).setOnClickListener(this);
        view.findViewById(R.id.card_view04).setOnClickListener(this);
        view.findViewById(R.id.card_view05).setOnClickListener(this);
        view.findViewById(R.id.card_view06).setOnClickListener(this);
        view.findViewById(R.id.card_view07).setOnClickListener(this);
        view.findViewById(R.id.card_view08).setOnClickListener(this);
        view.findViewById(R.id.card_view09).setOnClickListener(this);
        view.findViewById(R.id.card_view10).setOnClickListener(this);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onClick(View v) {

        // For all cardView
        if (v.getId() >= R.id.card_view01 && v.getId() <= R.id.card_view10) {
            int card_num = v.getId() - R.id.card_view01 + 1;
            showNoticeDialog(card_num);
        }

        switch (v.getId()) {
            case R.id.button_sign_out:
                redirectGoogleSignOut();
                break;
            default:
                break;
        }
    }

    void showNoticeDialog(int cardNumber) {
        Log.d("DIALOG_FRAGMENT", "showNoticeDialog");

        HomeDialogFragment newFragment = HomeDialogFragment.newInstance(cardNumber);
        newFragment.setTargetFragment(this, 0);
        if (getFragmentManager() != null) {
            newFragment.show(getFragmentManager(), "showNoticeDialog");
        }
    }

    @Override
    public void onDialogClick(DialogFragment dialog, int cardNumber, boolean isLocked) {
        Log.d("HOME_FRAGMENT", "onDialogClick"
                + Integer.toString(cardNumber) + " locked : " + Boolean.toString(isLocked));

        final String buildingId = Integer.toString(cardNumber);
        final String lockState = (isLocked) ? "locked" : "unlocked";

        Log.d("POST_REQUEST", "calling changeLockState");

        DashboardManager.changeLockState(userToken, buildingId, lockState, new Callable<Void>() {
            public Void call() {
                Log.d("POST_REQUEST", "calling logHistory");

                HistoryManager.logHistory(userToken, buildingId, lockState, new Callable<Void>() {
                    public Void call() {

                        Log.d("POST_REQUEST", "calling fetchDashboard");
                        DashboardManager.fetchDashboard(parentView, userToken);
                        return null;
                    }
                });
                return null;
            }
        });

    }

    private void redirectGoogleSignOut() {
        Log.d("DASHBOARD_ACTIVITY", "redirectGoogleSignOut");

        Intent intent = new Intent(getActivity(), LogoutActivity.class);
        startActivity(intent);
    }
}
