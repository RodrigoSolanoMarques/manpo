package br.com.rodrigosolanomarques.manpo.tarefas.cadastro;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.rodrigosolanomarques.manpo.R;
import br.com.rodrigosolanomarques.manpo.config.AppDatabase;
import br.com.rodrigosolanomarques.manpo.data.model.Tarefa;
import br.com.rodrigosolanomarques.manpo.enumeration.Prioridade;
import br.com.rodrigosolanomarques.manpo.util.AlertDialogUtil;

public class CadastrarTarefaActivity
        extends AppCompatActivity
        implements CadastrarTarefaContract.View {

    private TextInputLayout tilDescricao;
    private TextInputLayout tilTempoPrevisto;
    private TextInputLayout tilDataCriacao;
    private TextInputLayout tilDataFinalizacao;

    private TextInputEditText tiedtDescricao;
    private TextInputEditText tiedtTempoPrevisto;
    private TextInputEditText tiedtDataCriacao;
    private TextInputEditText tiedtDataFinalizacao;
    private ImageView ivPrioridade;
    private CheckBox chkFinalizar;
    private Spinner spPrioridade;
    private Button btnSalvar;
    private Button btnExcluir;

    private CadastrarTarefaContract.Presenter presenter;
    private long idTarefa;
    private AlertDialog alertDialog;
    @SuppressLint("SimpleDateFormat")
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public static final String TAREFA = "tarefa";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_tarefa);
        configurarToolbar();
        recuperarExtras();
        recuperarViews();
        configurarOnClickListener();
        configurarOnItemSelectedListener();
        configurarOnChangeListener();

        alertDialog = criarAlertDialog();
        presenter = new CadastrarTarefaPresenter(this, AppDatabase.getInstance(this).tarefaDao());
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

    @Override
    public void preencherTarefa(final Tarefa tarefa) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tiedtDescricao.setText(tarefa.getDescricao());

                Integer tempoPrevisto = tarefa.getTempoPrevisto();
                if (tempoPrevisto != null && tempoPrevisto > 0) {
                    tiedtTempoPrevisto.setText(String.valueOf(tempoPrevisto));
                }

                spPrioridade.setSelection(tarefa.getPrioridade().ordinal(), true);

                tiedtDataCriacao.setText(sdf.format(tarefa.getDataCriacao()));

                if (tarefa.isFinalizada()) {
                    tiedtDataFinalizacao.setText(sdf.format(tarefa.getDataFinalizacao()));
                    chkFinalizar.setChecked(true);
                }

            }
        });
    }

    @Override
    public boolean validarCampos() {
        boolean valido = true;
        Tarefa tarefa = new Tarefa();

        if (tiedtDescricao.getText().toString().isEmpty()) {
            tilDescricao.setError(getResources().getString(R.string.info_campo_obigatorio));
            valido = false;
        } else {
            tilDescricao.setError(null);
            tarefa.setDescricao(tiedtDescricao.getText().toString());
        }

        if (tiedtTempoPrevisto.getText().toString().isEmpty()) {
            tilTempoPrevisto.setError(getResources().getString(R.string.info_campo_obigatorio));
            valido = false;
        } else {
            tilTempoPrevisto.setError(null);
            tilTempoPrevisto.setHelperText(getString(R.string.info_ajuda_tempo_tarefa));
            tarefa.setTempoPrevisto(Integer.valueOf(tiedtTempoPrevisto.getText().toString()));
        }

        String dataCriacao = tiedtDataCriacao.getText().toString();

        if (dataCriacao.isEmpty()) {
            tilDataCriacao.setError(getResources().getString(R.string.info_campo_obigatorio));
            valido = false;
        } else {

            if (!validarFormatoData(dataCriacao)) {
                tilDataCriacao.setError(getResources().getString(R.string.info_data_invalida));
                valido = false;
            } else {
                try {
                    tilDataCriacao.setError(null);
                    Date dataCriacaoParse = sdf.parse(tiedtDataCriacao.getText().toString());
                    tarefa.setDataCriacao(dataCriacaoParse);
                } catch (ParseException e) {
                    tilDataCriacao.setError(getResources().getString(R.string.info_data_invalida));
                    valido = false;
                }
            }
        }

        if (chkFinalizar.isChecked()) {
            tarefa.setFinalizada(true);

            String dataFinalizacao = tiedtDataFinalizacao.getText().toString();
            if (dataFinalizacao.isEmpty()) {
                tilDataFinalizacao.setError(getResources().getString(R.string.info_campo_obigatorio));
                valido = false;
            } else {

                if (!validarFormatoData(dataFinalizacao)) {
                    tilDataFinalizacao.setError(getResources().getString(R.string.info_data_invalida));
                    valido = false;
                } else {

                    try {
                        tilDataFinalizacao.setError(null);
                        Date dataFinalizacaoParse = sdf.parse(tiedtDataFinalizacao.getText().toString());
                        tarefa.setDataFinalizacao(dataFinalizacaoParse);
                    } catch (ParseException e) {
                        tilDataFinalizacao.setError(getResources().getString(R.string.info_data_invalida));
                        valido = false;
                    }
                }
            }
        }

        tarefa.setPrioridade(Prioridade.values()[spPrioridade.getSelectedItemPosition()]);
        presenter.preencherTarefa(tarefa);
        return valido;
    }

    @Override
    public void mostrarBotaoExcluir() {
        btnExcluir.setVisibility(View.VISIBLE);
    }

    @Override
    public void esconderBotaoExcluir() {
        btnExcluir.setVisibility(View.GONE);
    }

    @Override
    public void mostrarAlertDialogInfoCadastrarAtividade() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.app_name);
        builder.setMessage(R.string.pergunta_deseja_cadastrar_atividades_para_tarefa);
        builder.setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Abrir tela de cadastro de atividades, talvez", Toast.LENGTH_LONG).show();
                dialog.dismiss();

            }
        });
        builder.setNegativeButton(R.string.nao, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();
            }
        });
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                builder.create().show();
            }
        });

    }

    @Override
    public void mostrarToast(@StringRes final int msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(CadastrarTarefaActivity.this, msg, Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void finalizarTela() {
        finish();
    }

    // ===============================================================
    // ========================== LifeCycle ==========================
    // ===============================================================

    @Override
    protected void onResume() {
        super.onResume();
        buscaTarefa(presenter, idTarefa);
    }

    // ===============================================================
    // ===============================================================

    private void configurarToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.tarefa);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void recuperarExtras() {
        Bundle extras = getIntent().getExtras();
        if (extras != null && !extras.isEmpty()) {

            if (extras.containsKey(TAREFA)) {
                idTarefa = extras.getLong(TAREFA);
            }
        }
    }

    private void recuperarViews() {
        tilDescricao = findViewById(R.id.tilDescricao);
        tilTempoPrevisto = findViewById(R.id.tilTempoPrevisto);
        tilDataCriacao = findViewById(R.id.tilDataCriacao);
        tilDataFinalizacao = findViewById(R.id.tilDataFinalizacao);

        tiedtDescricao = findViewById(R.id.tiedtDescricao);
        tiedtTempoPrevisto = findViewById(R.id.tiedtTempoPrevisto);
        tiedtDataCriacao = findViewById(R.id.tiedtDataCriacao);
        tiedtDataFinalizacao = findViewById(R.id.tiedtDataFinalizacao);
        ivPrioridade = findViewById(R.id.ivPrioridade);
        chkFinalizar = findViewById(R.id.chkFinalizar);
        spPrioridade = findViewById(R.id.spPrioridade);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnExcluir = findViewById(R.id.btnExcluir);
    }

    private void configurarOnClickListener() {
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarTarefa();
            }
        });

        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                excluirTarefa();
            }
        });
    }

    private void configurarOnItemSelectedListener() {
        spPrioridade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position == Prioridade.ALTA.getOrdem()) {
                    ivPrioridade.setBackgroundColor(getResources().getColor(R.color.alta));

                } else if (position == Prioridade.BAIXA.getOrdem()) {
                    ivPrioridade.setBackgroundColor(getResources().getColor(R.color.baixa));

                } else if (position == Prioridade.MEDIA.getOrdem()) {
                    ivPrioridade.setBackgroundColor(getResources().getColor(R.color.media));

                } else {
                    ivPrioridade.setBackgroundColor(getResources().getColor(R.color.baixa));
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void configurarOnChangeListener() {
        chkFinalizar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    tiedtDataFinalizacao.setText(sdf.format(new Date()));
                    tiedtDataFinalizacao.setEnabled(true);
                } else {
                    tiedtDataFinalizacao.getText().clear();
                    tiedtDataFinalizacao.setEnabled(false);
                }
            }
        });
    }

    private AlertDialog criarAlertDialog() {
        return AlertDialogUtil.criarAlertDialog(this);
    }

    private boolean validarFormatoData(String data) {
        try {
            sdf.parse(data);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    @SuppressLint("StaticFieldLeak")
    private void salvarTarefa() {

        if (!validarCampos()) {
            return;
        }

        new AsyncTask<Void, Void, Void>() {

            @Override
            protected void onPreExecute() {
                alertDialog.show();
            }

            @Override
            protected Void doInBackground(Void... voids) {
                presenter.salvarTarefa();
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                alertDialog.dismiss();
            }
        }.execute();
    }


    @SuppressLint("StaticFieldLeak")
    private void buscaTarefa(final CadastrarTarefaContract.Presenter presenter, final long idTarefa) {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected void onPreExecute() {
                alertDialog.show();
            }

            @Override
            protected Void doInBackground(Void... voids) {
                presenter.start(idTarefa);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                alertDialog.dismiss();
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    private void excluirTarefa() {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected void onPreExecute() {
                alertDialog.show();
            }

            @Override
            protected Void doInBackground(Void... voids) {
                presenter.deletarTarefa();
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                alertDialog.dismiss();
                mostrarToast(R.string.info_tarefa_excluida);
                finish();
            }
        }.execute();
    }
}
