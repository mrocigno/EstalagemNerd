package br.com.mrocigno.estalagemnerd.main.home;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import br.com.mrocigno.estalagemnerd.R;
import br.com.mrocigno.estalagemnerd.config.AbstractActivity;
import br.com.mrocigno.estalagemnerd.config.AbstractFragment;
import br.com.mrocigno.estalagemnerd.details.DetailsActivity;
import br.com.mrocigno.estalagemnerd.main.data.PodcastsModelVO;

public class HomeFragment extends AbstractFragment<Home.Presenter> implements Home.View, HomeAdapter.HomeAdapterCallback {

    RecyclerView rcvHome;

    public HomeFragment() {
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_home;
    }

    @Override
    public void initVars(View view) {
        rcvHome = view.findViewById(R.id.rcvHome);
    }

    @Override
    public View customCreateView(View view, @NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if(presenter != null)
            presenter.loadData();

        return view;
    }

    @Override
    public void onClickPlayButton(String url, String title) {
        ((AbstractActivity) Objects.requireNonNull(getActivity())).playerService.prepareSong(url, title);
    }

    @Override
    public void onCardClick(ActivityOptions options, Intent intent) {

        Objects.requireNonNull(getActivity()).startActivity(intent, options.toBundle());
    }

    @Override
    public void showPodcasts(List<PodcastsModelVO> list) {
        rcvHome.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rcvHome.setAdapter(new HomeAdapter(list, getContext(), this));
    }

}
