package br.com.mrocigno.estalagemnerd.splash;

import javax.inject.Inject;

public class SplashPresenter implements Splash.Presenter {

    Splash.View view;

    @Inject
    public SplashPresenter(Splash.View view) {
        this.view = view;
    }

    @Override
    public String teste() {
        if(view == null)
            return "chorei";
        return "hehe boy";
    }

    @Override
    public void setupListeners() {
        view.setPresenter(this);
    }
}
