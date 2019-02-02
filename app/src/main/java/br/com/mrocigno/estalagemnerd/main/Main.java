package br.com.mrocigno.estalagemnerd.main;

import br.com.mrocigno.estalagemnerd.config.BasePresenter;
import br.com.mrocigno.estalagemnerd.config.BaseView;

public interface Main {
    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter{

    }
}
