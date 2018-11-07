package br.com.rodrigosolanomarques.manpo.tarefas.cadastro;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import br.com.rodrigosolanomarques.manpo.R;

public class CadastrarTarefaActivity
        extends AppCompatActivity
        implements CadastrarTarefaContract.View {

    public static final String TAREFA = "tarefa";

    private CadastrarTarefaContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_tarefa);


    }

    @Override
    public void setPresenter(CadastrarTarefaContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
