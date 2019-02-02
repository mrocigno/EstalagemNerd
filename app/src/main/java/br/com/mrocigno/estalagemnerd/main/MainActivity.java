package br.com.mrocigno.estalagemnerd.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import javax.inject.Inject;

import br.com.mrocigno.estalagemnerd.R;
import br.com.mrocigno.estalagemnerd.config.AbstractActivity;
import br.com.mrocigno.estalagemnerd.main.home.HomeFragment;
import br.com.mrocigno.estalagemnerd.main.home.HomePresenter;
import br.com.mrocigno.estalagemnerd.main.home.HomePresenterModule;

public class MainActivity extends AbstractActivity {

    @Inject
    HomePresenter homePresenter;

    HomeFragment homeFragment;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    public void initVars() {

    }

    @Override
    public void customCreate(Bundle savedInstanceState) {

        homeFragment = new HomeFragment();

        DaggerMainComponent.builder()
                .homePresenterModule(new HomePresenterModule(homeFragment))
                .build()
                .inject(this);

        addFragmentToActivity(getSupportFragmentManager(), homeFragment, R.id.contentFrame, "home");
    }

}
