package br.com.mrocigno.estalagemnerd.config;

import android.app.Activity;
import android.content.ContentValues;
import android.util.Log;

import javax.inject.Inject;

import br.com.mrocigno.estalagemnerd.localdata.LocalData;

import retrofit2.Retrofit;

public class BaseModel {

    Retrofit retrofit;

    LocalData localData;

    public BaseModel(LocalData localData, Retrofit retrofit) {
        this.localData = localData;
        this.retrofit = retrofit;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public LocalData getLocalData() {
        return localData;
    }

}
