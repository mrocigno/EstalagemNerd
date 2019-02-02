package br.com.mrocigno.estalagemnerd.main.home;

import dagger.Module;
import dagger.Provides;

@Module
public class HomePresenterModule {

    Home.View view;

    public HomePresenterModule(Home.View view) {
        this.view = view;
    }

    @Provides
    Home.View provideView(){return view;}
}
