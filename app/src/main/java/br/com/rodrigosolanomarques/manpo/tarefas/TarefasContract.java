package br.com.rodrigosolanomarques.manpo.tarefas;

import java.util.List;

import br.com.rodrigosolanomarques.manpo.BasePresenter;
import br.com.rodrigosolanomarques.manpo.BaseView;
import br.com.rodrigosolanomarques.manpo.data.model.Tarefa;

public interface TarefasContract {

    interface View extends BaseView<Presenter> {

        void carregarTarefas();
    }

    interface Presenter extends BasePresenter {

        List<Tarefa> carregarTarefas();

    }
}
