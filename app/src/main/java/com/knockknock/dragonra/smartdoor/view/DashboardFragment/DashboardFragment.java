package com.knockknock.dragonra.smartdoor.view.DashboardFragment;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.knockknock.dragonra.smartdoor.LogoutActivity;
import com.knockknock.dragonra.smartdoor.R;
import com.knockknock.dragonra.smartdoor.model.dummy.DummyBuilding;
import com.knockknock.dragonra.smartdoor.view.Component.CardViewAdaptor;

import java.util.ArrayList;

public class DashboardFragment extends Fragment implements View.OnClickListener {

    private DashboardViewModel mViewModel;

    public static DashboardFragment newInstance() {
        return new DashboardFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
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

        modifyCardView(view);
    }

    private void modifyCardView(@NonNull View view) {

        ViewGroup dashboardCard = view.findViewById(R.id.dashboard);
        View dashboardChildView;

        // TODO: example of handling data payload
        ArrayList<DummyBuilding> buildings = DummyBuilding.createMockBuildings(10);
        int j = 0;
        DummyBuilding building;

        // Iterate through all cardView
        for (int i = 0; i < dashboardCard.getChildCount(); i++) {
            dashboardChildView = dashboardCard.getChildAt(i);
            if (dashboardChildView instanceof CardView) {

                // Pick the dummy data
                building = buildings.get(j);
                j++;

                // TODO: do something with the card view !
                CardViewAdaptor.setText(dashboardChildView, building.getName());
                CardViewAdaptor.setLockState(dashboardChildView, building.isLocked());
//                CardViewAdaptor.setInvisible(dashboardChildView);

            }
        }
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
