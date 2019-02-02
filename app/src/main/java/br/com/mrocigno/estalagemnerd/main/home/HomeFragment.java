package br.com.mrocigno.estalagemnerd.main.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.mrocigno.estalagemnerd.R;
import br.com.mrocigno.estalagemnerd.config.AbstractFragment;

public class HomeFragment extends AbstractFragment<Home.Presenter> implements Home.View {

    public HomeFragment() {
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_home;
    }

    @Override
    public void initVars(View view) {

    }

    @Override
    public View customCreateView(View view, @NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(presenter == null){
            Log.e(TAG, "customCreateView: " + "null");
        }else{
            Log.e(TAG, "customCreateView: " + presenter.teste());
        }
        return view;
    }

}
