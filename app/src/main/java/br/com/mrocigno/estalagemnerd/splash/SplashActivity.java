package br.com.mrocigno.estalagemnerd.splash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import javax.inject.Inject;

import br.com.mrocigno.estalagemnerd.main.MainActivity;

public class SplashActivity extends AppCompatActivity implements Splash.View {

    @Inject
    SplashPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerSplashComponent.builder()
                .splashPresenterModule(new SplashPresenterModule(this))
                .build()
                .inject(this);

        if(presenter == null){
            Log.e("TESTEEE", "onCreate: null");
        }else{
            Log.e("TESTEEE", "onCreate: " + presenter.teste());
        }

        startActivity(new Intent(SplashActivity.this, MainActivity.class));

    }

    @Override
    public void setPresenter(Splash.Presenter presenter) {

    }
}
