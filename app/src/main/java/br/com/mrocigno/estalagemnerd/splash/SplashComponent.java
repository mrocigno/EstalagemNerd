package br.com.mrocigno.estalagemnerd.splash;

import dagger.Component;

@Component(modules = {SplashPresenterModule.class})
public interface SplashComponent {

    void inject(SplashActivity activity);
}
