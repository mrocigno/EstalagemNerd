package br.com.mrocigno.estalagemnerd.main.home;

import java.util.List;

import br.com.mrocigno.estalagemnerd.config.BasePresenter;
import br.com.mrocigno.estalagemnerd.config.BaseView;
import br.com.mrocigno.estalagemnerd.main.data.PodcastsModelVO;

public interface Home {
    interface View extends BaseView<Presenter>{

        void showPodcasts(List<PodcastsModelVO> list);

    }

    interface Presenter extends BasePresenter{
        String teste();

        void loadData();

    }
}
