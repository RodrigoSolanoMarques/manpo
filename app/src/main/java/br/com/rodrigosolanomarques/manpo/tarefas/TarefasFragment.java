package br.com.rodrigosolanomarques.manpo.tarefas;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import java.util.List;

import br.com.rodrigosolanomarques.manpo.R;
import br.com.rodrigosolanomarques.manpo.config.AppDatabase;
import br.com.rodrigosolanomarques.manpo.data.model.Tarefa;
import br.com.rodrigosolanomarques.manpo.pomodoro.PomodoroActivity;
import br.com.rodrigosolanomarques.manpo.tarefas.cadastro.CadastrarTarefaActivity;
import br.com.rodrigosolanomarques.manpo.util.AlertDialogUtil;

public class TarefasFragment
        extends Fragment
        implements TarefasContract.View, TarefasAdapter.OnItemClickListener, View.OnClickListener {

    private RecyclerView recyclerView;
    private FloatingActionButton fabAdicionarTarefa;


    private TarefasContract.Presenter presenter;
    private AlertDialog alertDialog;

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

        recuperarViews(layout);
        configurarRecyclerView(recyclerView);

        alertDialog = AlertDialogUtil.criarAlertDialog(getActivity());
        fabAdicionarTarefa.setOnClickListener(this);

        new TarefasPresenter(this, AppDatabase.getInstance(getContext()).tarefaDao());
        return layout;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    private void configurarToolbar() {
        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.backlog);
    }

    private void recuperarViews(View layout) {
        recyclerView = layout.findViewById(R.id.rvListaTarefas);
        fabAdicionarTarefa = layout.findViewById(R.id.fabAdicionarTarefa);
    }

    private void configurarRecyclerView(RecyclerView recyclerView) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
    }

    @Override
    public void setPresenter(TarefasContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onItemClick(Tarefa tarefa) {
        Intent intent = new Intent(getActivity(), CadastrarTarefaActivity.class);
        intent.putExtra(CadastrarTarefaActivity.TAREFA, tarefa.getId());
        startActivity(intent);
    }

    @Override
    public void executarTarefa(Tarefa tarefa) {
        Intent intent = new Intent(getActivity(), PomodoroActivity.class);
        intent.putExtra(PomodoroActivity.TAREFA, tarefa);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(getContext(), CadastrarTarefaActivity.class));
    }


    @SuppressLint("StaticFieldLeak")
    public void carregarTarefas() {

        new AsyncTask<Void, Void, List<Tarefa>>() {

            @Override
            protected void onPreExecute() {
                alertDialog.show();
            }

            @Override
            protected List<Tarefa> doInBackground(Void... voids) {
                return presenter.carregarTarefas();
            }

            @Override
            protected void onPostExecute(List<Tarefa> tarefas) {
                alertDialog.dismiss();
                TarefasAdapter adapter = new TarefasAdapter(getContext(), tarefas, TarefasFragment.this);
                recyclerView.setAdapter(adapter);
            }
        }.execute();
    }

}
