package br.com.rodrigosolanomarques.manpo.config;

import br.com.rodrigosolanomarques.manpo.service.TarefaService;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {

    OkHttpClient okHttpClient = new OkHttpClient();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build();


    public TarefaService createTarefaService() {
        return retrofit.create(TarefaService.class);
    }
}
