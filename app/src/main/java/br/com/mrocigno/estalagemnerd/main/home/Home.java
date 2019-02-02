package br.com.mrocigno.estalagemnerd.main.home;

import br.com.mrocigno.estalagemnerd.config.BasePresenter;
import br.com.mrocigno.estalagemnerd.config.BaseView;

public interface Home {
    interface View extends BaseView<Presenter>{

    }

    interface Presenter extends BasePresenter{
        String teste();
    }
}
