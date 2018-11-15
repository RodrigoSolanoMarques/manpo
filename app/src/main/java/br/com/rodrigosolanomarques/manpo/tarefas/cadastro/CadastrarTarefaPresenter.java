package br.com.rodrigosolanomarques.manpo.tarefas.cadastro;

import br.com.rodrigosolanomarques.manpo.data.local.TarefaDao;
import br.com.rodrigosolanomarques.manpo.data.model.Tarefa;

public class CadastrarTarefaPresenter implements CadastrarTarefaContract.Presenter {

    private CadastrarTarefaContract.View view;
    private Tarefa tarefa;
    private TarefaDao tarefaDao;

    public CadastrarTarefaPresenter(CadastrarTarefaContract.View view, TarefaDao tarefaDao) {
        this.view = view;
        this.view.setPresenter(this);

        this.tarefaDao = tarefaDao;
    }

    @Override
    @Deprecated
    public void start() {
    }

    @Override
    public void start(Integer idTarefa) {
        if (idTarefa != null && idTarefa > 0) {
            // Busca tarefa no banco de dados
        } else {
            tarefa = new Tarefa();
        }

    }

    @Override
    public void buscarTarefa(int idTarefa) {

    }
}
