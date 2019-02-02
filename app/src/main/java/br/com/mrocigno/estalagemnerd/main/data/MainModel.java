package br.com.mrocigno.estalagemnerd.main.data;

import android.app.Activity;

import java.util.List;

import javax.inject.Inject;

import br.com.mrocigno.estalagemnerd.config.BaseModel;
import br.com.mrocigno.estalagemnerd.config.ModelVO;
import br.com.mrocigno.estalagemnerd.localdata.LocalData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainModel extends BaseModel {

    public MainModel(LocalData localData, Retrofit retrofit) {
        super(localData, retrofit);
    }

    public void getPodcasts(final GetPodcastsCallback callback){
        getRetrofit().create(MainAPI.class).getPodcasts().enqueue(new Callback<ModelVO<List<PodcastsModelVO>>>() {
            @Override
            public void onResponse(Call<ModelVO<List<PodcastsModelVO>>> call, Response<ModelVO<List<PodcastsModelVO>>> response) {
                callback.getPodcatsSuccess(response.body().getData());
            }

            @Override
            public void onFailure(Call<ModelVO<List<PodcastsModelVO>>> call, Throwable t) {
                callback.getPodcatsFailure(t);
            }
        });
    }

    public interface GetPodcastsCallback{
        void getPodcatsSuccess(List<PodcastsModelVO> list);
        void getPodcatsFailure(Throwable t);
    }

}
