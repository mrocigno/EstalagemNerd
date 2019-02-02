package br.com.mrocigno.estalagemnerd.splash;

import dagger.Module;
import dagger.Provides;

@Module
public class SplashPresenterModule {

    Splash.View view;

    public SplashPresenterModule(Splash.View view) {
        this.view = view;
    }

    @Provides
    Splash.View provideView(){return view;}
}
