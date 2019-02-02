package br.com.mrocigno.estalagemnerd.splash;

import br.com.mrocigno.estalagemnerd.config.BasePresenter;
import br.com.mrocigno.estalagemnerd.config.BaseView;

public interface Splash {
    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter {
        String teste();
    }
}
