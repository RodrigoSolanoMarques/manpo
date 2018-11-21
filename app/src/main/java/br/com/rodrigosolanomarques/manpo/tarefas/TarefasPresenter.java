package br.com.rodrigosolanomarques.manpo.tarefas;

import java.util.List;

import br.com.rodrigosolanomarques.manpo.data.local.TarefaDao;
import br.com.rodrigosolanomarques.manpo.data.model.Tarefa;

public class TarefasPresenter implements TarefasContract.Presenter {

    private TarefasContract.View view;
    private TarefaDao tarefaDao;
    private List<Tarefa> listaTarefas;

    public TarefasPresenter(TarefasContract.View view, TarefaDao tarefaDao) {
        this.view = view;
        this.tarefaDao = tarefaDao;

        view.setPresenter(this);
    }

    @Override
    public void start() {
        view.carregarTarefas();
    }

    @Override
    public List<Tarefa> carregarTarefas() {
        return tarefaDao.getAll();
    }

}
