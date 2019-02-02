package br.com.mrocigno.estalagemnerd.config;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class AbstractFragment<T extends BasePresenter> extends Fragment {

    public String TAG = "TESTEEE";

    public T presenter;

    public void setPresenter(T presenter) {
        this.presenter = presenter;
    }

    View view;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(view == null)
            view = inflater.inflate(getLayoutRes(),container, false);

        initVars(view);

        return customCreateView(view, inflater, container, savedInstanceState);
    }

    public abstract int getLayoutRes();

    public abstract void initVars(View view);

    public abstract View customCreateView(View view, @NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);
}
