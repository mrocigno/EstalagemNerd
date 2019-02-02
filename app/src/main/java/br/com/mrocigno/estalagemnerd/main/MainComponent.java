package br.com.mrocigno.estalagemnerd.main;

import br.com.mrocigno.estalagemnerd.main.data.MainDataModule;
import br.com.mrocigno.estalagemnerd.main.home.HomePresenterModule;
import br.com.mrocigno.estalagemnerd.modules.DataModule;
import dagger.Component;

@Component(modules = {HomePresenterModule.class, MainDataModule.class, DataModule.class})
public interface MainComponent {

    void inject(MainActivity activity);

}
