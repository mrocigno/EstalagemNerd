package br.com.mrocigno.estalagemnerd.main.home;

import java.util.List;

import javax.inject.Inject;

import br.com.mrocigno.estalagemnerd.main.data.MainModel;
import br.com.mrocigno.estalagemnerd.main.data.PodcastsModelVO;

public class HomePresenter implements Home.Presenter, MainModel.GetPodcastsCallback {

    Home.View view;
    MainModel model;

    @Inject
    public HomePresenter(Home.View view, MainModel model) {
        this.model = model;
        this.view = view;
    }

    @Inject
    @Override
    public void setupListeners() {
        view.setPresenter(this);
    }

    @Override
    public String teste() {
        if(model == null)
            return "chorei";
        else{
            if(model.getLocalData() == null)
                return "chorei";
        }

        return "hehe boy";
    }

    @Override
    public void loadData() {
        view.showProgresBar(true);
        model.getPodcasts(this);
    }

    @Override
    public void getPodcatsSuccess(List<PodcastsModelVO> list) {
        view.showProgresBar(false);
        view.showPodcasts(list);
    }

    @Override
    public void getPodcatsFailure(Throwable t) {

    }
}
