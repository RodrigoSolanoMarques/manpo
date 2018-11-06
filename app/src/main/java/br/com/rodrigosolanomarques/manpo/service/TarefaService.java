package br.com.rodrigosolanomarques.manpo.service;

import java.util.List;

import br.com.rodrigosolanomarques.manpo.data.model.Tarefa;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface TarefaService {

    @POST("")
    Call<Void> saveAll(@Body List<Tarefa> list);

}
