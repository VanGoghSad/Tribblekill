package com.example.lw_pc.tribblekill.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.lw_pc.tribblekill.App;
import com.example.lw_pc.tribblekill.R;
import com.example.lw_pc.tribblekill.core.Api;
import com.example.lw_pc.tribblekill.core.DribbbleApi;
import com.example.lw_pc.tribblekill.model.Shot;
import com.example.lw_pc.tribblekill.ui.adapter.ShotsAdapter;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class FollowingShotsFragment extends ListFragment {
    private App mApp;
    private String token;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mApp = (App) getActivity().getApplication();
        token = mApp.sharedPreferences.getString("access_token", "");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void loadData() {
        Api api = DribbbleApi.getDribbbleApi();
        api.getFollowingShots(page, token).enqueue(new Callback<List<Shot>>() {
            @Override
            public void onResponse(Response<List<Shot>> response, Retrofit retrofit) {
                if (response.body().size() == 0) {
                    avLoadingIndicatorView.setVisibility(View.GONE);
                    Toast.makeText(getActivity(), "You had not followed anyone!", Toast.LENGTH_LONG).show();
                } else {
                    mShotsAdapter.addShots(response.body());
                    avLoadingIndicatorView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    @Override
    protected void loadMore() {
        Api api = DribbbleApi.getDribbbleApi();
        api.getFollowingShots(++page, token).enqueue(new Callback<List<Shot>>() {
            @Override
            public void onResponse(Response<List<Shot>> response, Retrofit retrofit) {
                mShotsAdapter.addShots(response.body());
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
}
