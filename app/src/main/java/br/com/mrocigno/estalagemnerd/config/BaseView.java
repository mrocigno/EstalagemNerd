package br.com.mrocigno.estalagemnerd.config;

public interface BaseView<T extends BasePresenter> {

    void setPresenter(T presenter);

}
