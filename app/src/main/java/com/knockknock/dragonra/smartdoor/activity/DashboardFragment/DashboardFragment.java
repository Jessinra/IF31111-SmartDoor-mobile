package com.knockknock.dragonra.smartdoor.activity.DashboardFragment;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.knockknock.dragonra.smartdoor.R;
import com.knockknock.dragonra.smartdoor.activity.LogoutActivity;
import com.knockknock.dragonra.smartdoor.controller.Manager.DashboardManager;

public class DashboardFragment extends Fragment implements View.OnClickListener {

    private DashboardViewModel mViewModel;

    public static DashboardFragment newInstance() {
        return new DashboardFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dashboard_home, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.d("ACTIVITY_START", "onActivityCreated DashboardFragment");
        super.onActivityCreated(savedInstanceState);

        mViewModel = ViewModelProviders.of(this).get(DashboardViewModel.class);
        // TODO: Use the ViewModel

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        view.findViewById(R.id.button_sign_out).setOnClickListener(this);
        DashboardManager.fetchDashboard(view, "1234512345");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.button_sign_out:
                redirectGoogleSignOut();
                break;

            default:
                break;
        }
    }

    private void redirectGoogleSignOut() {
        Log.d("DASHBOARD_ACTIVITY", "redirectGoogleSignOut");

        Intent intent = new Intent(getActivity(), LogoutActivity.class);
        startActivity(intent);
    }


}
