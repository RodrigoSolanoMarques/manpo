package br.com.rodrigosolanomarques.manpo.tarefas;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import java.util.Arrays;
import java.util.Date;

import br.com.rodrigosolanomarques.manpo.R;
import br.com.rodrigosolanomarques.manpo.data.model.Tarefa;
import br.com.rodrigosolanomarques.manpo.enumeration.Prioridade;
import br.com.rodrigosolanomarques.manpo.tarefas.cadastro.CadastrarTarefaActivity;

public class TarefasFragment
        extends Fragment
        implements TarefasContract.View, TarefasAdapter.OnItemClickListener, View.OnClickListener {

    private TarefasContract.Presenter presenter;

    public TarefasFragment() {
        // Required empty public constructor
    }

    public static TarefasFragment newInstance() {
        return new TarefasFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        configurarToolbar();

        View layout = inflater.inflate(R.layout.fragment_tarefas, container, false);

        RecyclerView recyclerView = layout.findViewById(R.id.rvListaTarefas);
        FloatingActionButton fabAdicionarTarefa = layout.findViewById(R.id.fabAdicionarTarefa);

        Tarefa tarefa1 = new Tarefa();
        tarefa1.setDescricao("Fazer o artigo da Pós");
        tarefa1.setPrioridade(Prioridade.BAIXA);
        tarefa1.setDataCriacao(new Date());
        tarefa1.setTempoPrevisto(20);
        tarefa1.setFinalizada(true);


        Tarefa tarefa4 = new Tarefa();
        tarefa4.setDescricao("Fazer a PS que vai alterar o metodo de venda embarcada");
        tarefa4.setPrioridade(Prioridade.ALTA);
        tarefa4.setDataCriacao(new Date());
        tarefa4.setTempoPrevisto(100);
        tarefa4.setFinalizada(false);


        Tarefa tarefa2 = new Tarefa();
        tarefa2.setDescricao("Criar CRUD para o projeto da Pós");
        tarefa2.setPrioridade(Prioridade.MEDIA);
        tarefa2.setDataCriacao(new Date());
        tarefa2.setTempoPrevisto(10);
        tarefa2.setFinalizada(true);


        Tarefa tarefa3 = new Tarefa();
        tarefa3.setDescricao("Fazer o artigo da Pós");
        tarefa3.setPrioridade(Prioridade.ALTA);
        tarefa3.setDataCriacao(new Date());
        tarefa3.setTempoPrevisto(50);
        tarefa3.setFinalizada(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        TarefasAdapter adapter = new TarefasAdapter(getContext(), Arrays.asList(
                tarefa1, tarefa2, tarefa3, tarefa4,
                tarefa1, tarefa2, tarefa3, tarefa4,
                tarefa1, tarefa2, tarefa3, tarefa4
        ), this);
        recyclerView.setAdapter(adapter);

        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        fabAdicionarTarefa.setOnClickListener(this);
        return layout;
    }

    private void configurarToolbar() {
        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.tarefas);
    }

    @Override
    public void setPresenter(TarefasContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onItemClick(Tarefa tarefa) {
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(getContext(), CadastrarTarefaActivity.class));
    }
}
