package br.com.mrocigno.estalagemnerd.main.data;

import android.app.Activity;

import br.com.mrocigno.estalagemnerd.localdata.LocalData;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class MainDataModule {

    @Provides
    MainModel providesModel(LocalData localData, Retrofit retrofit){return new MainModel(localData, retrofit);}

}
