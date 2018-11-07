package br.com.rodrigosolanomarques.manpo.tarefas.cadastro;

public class CadastrarTarefaPresenter implements CadastrarTarefaContract.Presenter {

    private CadastrarTarefaContract.View view;

    public CadastrarTarefaPresenter(CadastrarTarefaContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
