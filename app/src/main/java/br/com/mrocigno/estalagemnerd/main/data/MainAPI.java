package br.com.mrocigno.estalagemnerd.main.data;

import java.util.List;

import br.com.mrocigno.estalagemnerd.config.ModelVO;
import retrofit2.Call;
import retrofit2.http.GET;

public interface MainAPI {

    @GET("estalagem.php")
    Call<ModelVO<List<PodcastsModelVO>>> getPodcasts();
}
