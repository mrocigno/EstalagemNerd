package br.com.mrocigno.estalagemnerd.main.home;

import javax.inject.Inject;

public class HomePresenter implements Home.Presenter {

    Home.View view;

    @Inject
    public HomePresenter(Home.View view) {
        this.view = view;
    }

    @Inject
    @Override
    public void setupListeners() {
        view.setPresenter(this);
    }

    @Override
    public String teste() {
        if(view == null)
            return "chorei";
        return "hehe boy";
    }
}
