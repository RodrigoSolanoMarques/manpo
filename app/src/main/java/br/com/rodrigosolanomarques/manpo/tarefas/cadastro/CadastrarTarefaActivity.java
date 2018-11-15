package br.com.rodrigosolanomarques.manpo.tarefas.cadastro;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import javax.inject.Inject;

import br.com.rodrigosolanomarques.manpo.R;
import br.com.rodrigosolanomarques.manpo.data.local.TarefaDao;

public class CadastrarTarefaActivity
        extends AppCompatActivity
        implements CadastrarTarefaContract.View {

//    @Inject
//     TarefaDao tarefaDao;

    private CadastrarTarefaContract.Presenter presenter;
    private Integer idTarefa;

    public static final String TAREFA = "tarefa";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_tarefa);
        configurarToolbar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recuperarExtras();

        presenter = new CadastrarTarefaPresenter(this, null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_cadastrar_tarefa, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.menu_atividade:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void setPresenter(CadastrarTarefaContract.Presenter presenter) {
        this.presenter = presenter;
    }

    // ===============================================================
    // ========================== LifeCycle ==========================
    // ===============================================================

    @Override
    protected void onResume() {
        super.onResume();
        presenter.start(idTarefa);
    }

    private void configurarToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.tarefa);
        setSupportActionBar(toolbar);
    }

    private void recuperarExtras() {
        Bundle extras = getIntent().getExtras();
        if (extras != null && !extras.isEmpty()) {

            if (extras.containsKey(TAREFA)) {
                idTarefa = extras.getInt(TAREFA);
            }
        }
    }
}
