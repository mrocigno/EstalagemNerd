package br.com.mrocigno.estalagemnerd.main;

import br.com.mrocigno.estalagemnerd.main.home.HomePresenterModule;
import dagger.Component;

@Component(modules = {HomePresenterModule.class})
public interface MainComponent {

    void inject(MainActivity activity);

}
