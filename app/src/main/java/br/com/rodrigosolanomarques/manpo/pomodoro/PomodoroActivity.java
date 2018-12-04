package br.com.rodrigosolanomarques.manpo.pomodoro;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.StringRes;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import br.com.rodrigosolanomarques.manpo.R;
import br.com.rodrigosolanomarques.manpo.config.AppDatabase;
import br.com.rodrigosolanomarques.manpo.config.SharedPreferencesConfiguracaoManpo;
import br.com.rodrigosolanomarques.manpo.data.model.Tarefa;
import br.com.rodrigosolanomarques.manpo.util.AlertDialogUtil;

public class PomodoroActivity extends AppCompatActivity implements PomodoroContract.View {

    private Chronometer chronometer;
    private ImageView ivExecutar;
    private ImageView ivParar;
    private ImageView ivPausar;
    private ImageView ivConcentracao;
    private ImageView ivIntervaloCurto;
    private ImageView ivIntervaloLongo;
    private TextView tvConcentracao;
    private TextView tvIntervaloCurto;
    private TextView tvIntervaloLongo;
    private TextView tvTarefaDescricao;
    private TextView tvPomodorosPrevistos;
    private TextView tvPomodorosExecutados;
    private Button btnFinalizarTarefa;
    private AlertDialog alertDialog;

    private Tarefa tarefa;
    private PomodoroContract.Presenter presenter;

    private long tempoParado = 0;
    private int tempoConcentracao = 0;
    private int tempoIntervaloCurto = 0;
    private int tempoIntervaloLongo = 0;
    private boolean isNotificarConcentracao = true;
    private boolean isNotificarIntervaloCurto = true;
    private boolean isNotificarIntervaloLongo = true;
    private int atualEtapaPomodoro = CONCENTRACAO;
    private int pomodorosConcluidosHoje = 0;
    private Integer pomodorosExecutadosNaTarefa = 0;

    public static String TAREFA = "tarefa";
    private static final int CONCENTRACAO = 0;
    private static final int INTERVALO_CURTO = 1;
    private static final int INTERVALO_LONGO = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pomodoro);

        configurarToolbar();
        recuperarExtras();
        recuperarViews();
        recuperarConfiguracoes();
        configurarListeners();
        habilitarConcentracao();
        alertDialog = criarAlertDialog();

        new PomodoroPresente(this, tarefa, AppDatabase.getInstance(getApplicationContext()).tarefaDao());
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.start();
    }

    private void recuperarExtras() {
        Bundle extras = getIntent().getExtras();
        if (extras != null && !extras.isEmpty()) {

            if (extras.containsKey(TAREFA)) {
                tarefa = (Tarefa) extras.getSerializable(TAREFA);
            }
        }
    }

    private void recuperarViews() {
        chronometer = findViewById(R.id.chronometer);
        ivExecutar = findViewById(R.id.ivExecutar);
        ivParar = findViewById(R.id.ivParar);
        ivPausar = findViewById(R.id.ivPausar);
        ivConcentracao = findViewById(R.id.ivConcentracao);
        ivIntervaloCurto = findViewById(R.id.ivIntervaloCurto);
        ivIntervaloLongo = findViewById(R.id.ivIntervaloLongo);
        tvConcentracao = findViewById(R.id.tvConcentracao);
        tvIntervaloCurto = findViewById(R.id.tvIntervaloCurto);
        tvIntervaloLongo = findViewById(R.id.tvIntervaloLongo);
        tvTarefaDescricao = findViewById(R.id.tvTarefaDescricao);
        tvPomodorosPrevistos = findViewById(R.id.tvPomodorosPrevistos);
        tvPomodorosExecutados = findViewById(R.id.tvPomodorosExecutados);
        btnFinalizarTarefa = findViewById(R.id.btnFinalizarTarefa);
    }

    private void configurarToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.pomodoro);
        setSupportActionBar(toolbar);

        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void configurarListeners() {
        ivExecutar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarCronometro();
            }
        });

        ivPausar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pausarCronometro();
            }
        });

        ivParar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pararCronometro();
            }
        });

        btnFinalizarTarefa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(PomodoroActivity.this);
                builder.setTitle(R.string.app_name);
                builder.setMessage(R.string.pergunta_deseja_finalizar_tarefa);
                builder.setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finalizarTarefa();
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton(R.string.nao, null);
                builder.create().show();
            }
        });

        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {

                Calendar tempo = Calendar.getInstance();
                tempo.setTimeInMillis(SystemClock.elapsedRealtime() - chronometer.getBase());

                if (atualEtapaPomodoro == CONCENTRACAO
                        && tempo.get(Calendar.SECOND) >= tempoConcentracao) {
                    proximaEtapaPomodoro(INTERVALO_CURTO);
                    if (pomodorosConcluidosHoje % 4 == 0) {
                        proximaEtapaPomodoro(INTERVALO_LONGO);
                    }
                    return;
                }

                if (atualEtapaPomodoro == INTERVALO_CURTO
                        && tempo.get(Calendar.SECOND) == tempoIntervaloCurto) {
                    proximaEtapaPomodoro(CONCENTRACAO);
                    return;
                }

                if (atualEtapaPomodoro == INTERVALO_LONGO
                        && tempo.get(Calendar.SECOND) == tempoIntervaloLongo) {
                    proximaEtapaPomodoro(CONCENTRACAO);
                }

            }
        });
    }

    private void recuperarConfiguracoes() {
        tempoConcentracao = SharedPreferencesConfiguracaoManpo.obterConcentracao(this);
        tempoIntervaloCurto = SharedPreferencesConfiguracaoManpo.obterIntervaloCurto(this);
        tempoIntervaloLongo = SharedPreferencesConfiguracaoManpo.obterIntervaloLongo(this);

        isNotificarConcentracao = SharedPreferencesConfiguracaoManpo.isNotificarConcentracao(this);
        isNotificarIntervaloCurto = SharedPreferencesConfiguracaoManpo.isNotificarIntervaloCurto(this);
        isNotificarIntervaloLongo = SharedPreferencesConfiguracaoManpo.isNotificarIntervaloLongo(this);
    }

    private void desabilitarPause() {
        ivPausar.setEnabled(false);
        ivPausar.setColorFilter(getResources().getColor(R.color.darker_gray));
    }

    private void habilitarPause() {
        ivPausar.setEnabled(true);
        ivPausar.setColorFilter(getResources().getColor(R.color.media));
    }

    private void desabilitarParar() {
        ivParar.setEnabled(false);
        ivParar.setColorFilter(getResources().getColor(R.color.darker_gray));
    }

    private void habilitarParar() {
        ivParar.setEnabled(true);
        ivParar.setColorFilter(getResources().getColor(R.color.alta));
    }

    private void desabilitarExecutar() {
        ivExecutar.setEnabled(false);
        ivExecutar.setColorFilter(getResources().getColor(R.color.darker_gray));
    }

    private void habilitarExecutar() {
        ivExecutar.setEnabled(true);
        ivExecutar.setColorFilter(getResources().getColor(R.color.green_light));
    }

    private void desabilitarConcentracao() {
        tvConcentracao.setTextColor(getResources().getColor(R.color.darker_gray));
        ivConcentracao.setColorFilter(getResources().getColor(R.color.darker_gray));
    }

    private void habilitarConcentracao() {
        tvConcentracao.setTextColor(getResources().getColor(R.color.green_light));
        ivConcentracao.setColorFilter(getResources().getColor(R.color.green_light));
    }

    private void desabilitarIntervaloCurto() {
        tvIntervaloCurto.setTextColor(getResources().getColor(R.color.darker_gray));
        ivIntervaloCurto.setColorFilter(getResources().getColor(R.color.darker_gray));
    }

    private void habilitarIntervaloCurto() {
        tvIntervaloCurto.setTextColor(getResources().getColor(R.color.yellow));
        ivIntervaloCurto.setColorFilter(getResources().getColor(R.color.yellow));
    }

    private void desabilitarIntervaloLongo() {
        tvIntervaloLongo.setTextColor(getResources().getColor(R.color.darker_gray));
        ivIntervaloLongo.setColorFilter(getResources().getColor(R.color.darker_gray));
    }

    private void habilitarIntervaloLongo() {
        tvIntervaloLongo.setTextColor(getResources().getColor(R.color.red));
        ivIntervaloLongo.setColorFilter(getResources().getColor(R.color.red));
    }

    private void iniciarCronometro() {
        chronometer.setBase(SystemClock.elapsedRealtime() - tempoParado);
        chronometer.start();

        desabilitarExecutar();
        habilitarPause();
        habilitarParar();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.cancel(R.drawable.ic_apple);
    }

    private void pausarCronometro() {
        tempoParado = SystemClock.elapsedRealtime() - chronometer.getBase();
        chronometer.stop();

        habilitarExecutar();
        desabilitarPause();
        habilitarParar();
    }

    private void pararCronometro() {
        chronometer.stop();
        chronometer.setBase(SystemClock.elapsedRealtime());
        tempoParado = 0;

        habilitarExecutar();
        desabilitarPause();
        desabilitarParar();
    }

    private void iniciarConcentracao() {
        habilitarConcentracao();
        desabilitarIntervaloCurto();
        desabilitarIntervaloLongo();
    }

    private void iniciarIntervaloCurto() {
        desabilitarConcentracao();
        habilitarIntervaloCurto();
        desabilitarIntervaloLongo();
    }

    private void iniciarIntervaloLongo() {
        desabilitarConcentracao();
        desabilitarIntervaloCurto();
        habilitarIntervaloLongo();
    }

    private void proximaEtapaPomodoro(int etapa) {

        pararCronometro();

        switch (etapa) {
            case CONCENTRACAO:
                iniciarConcentracao();
                atualEtapaPomodoro = CONCENTRACAO;
                if (isNotificarConcentracao) {
                    criarNotificacao(R.string.info_concentracao);
                }
                break;

            case INTERVALO_CURTO:
                iniciarIntervaloCurto();
                atualEtapaPomodoro = INTERVALO_CURTO;
                adicionarUmPomodoroExecutado();
                if (isNotificarIntervaloCurto) {
                    criarNotificacao(R.string.info_descanso_intervalo_curto);
                }
                break;

            case INTERVALO_LONGO:
                iniciarIntervaloLongo();
                atualEtapaPomodoro = INTERVALO_LONGO;
                if (isNotificarIntervaloLongo) {
                    criarNotificacao(R.string.info_descanso_intervalo_longo);
                }
                break;
        }
    }

    private AlertDialog criarAlertDialog() {
        return AlertDialogUtil.criarAlertDialog(PomodoroActivity.this);
    }

    @SuppressLint("StaticFieldLeak")
    private void finalizarTarefa() {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected void onPreExecute() {
                alertDialog.show();
            }

            @Override
            protected Void doInBackground(Void... voids) {
                presenter.finalizarTarefa();
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                alertDialog.dismiss();
                Toast.makeText(PomodoroActivity.this, "Esta tarefa foi finaliza com sucesso", Toast.LENGTH_LONG).show();
            }
        }.execute();

    }

    @SuppressLint("StaticFieldLeak")
    private void adicionarUmPomodoroExecutado() {
        pomodorosConcluidosHoje++;
        pomodorosExecutadosNaTarefa++;
        tvPomodorosExecutados.setText(String.valueOf(pomodorosExecutadosNaTarefa));

        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                presenter.adicionarUmPomodoroExecutado();
                return null;
            }
        }.execute();
    }

    private void criarNotificacao(@StringRes int msg) {
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getActivity(PomodoroActivity.this, 0, new Intent(this, PomodoroActivity.class), 0);


        NotificationCompat.Builder builder = new NotificationCompat.Builder(PomodoroActivity.this, "notificaocao");
        builder.setTicker("MANPO");
        builder.setContentTitle("Etapa concluída");
        builder.setContentText(getResources().getText(msg));
        builder.setSmallIcon(R.drawable.ic_apple);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_apple));
        builder.setContentIntent(pendingIntent);

        Notification notification = builder.build();
        notification.vibrate = new long[]{500, 1000, 500, 1000, 500, 1000};
        notification.priority = Notification.PRIORITY_MAX;
        mNotificationManager.notify(R.drawable.ic_apple, notification);


        Uri som = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Ringtone toque = RingtoneManager.getRingtone(PomodoroActivity.this, som);
        toque.play();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setPresenter(PomodoroContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void preencherTarefaEmTela(Tarefa tarefa) {
        tvTarefaDescricao.setText(tarefa.getDescricao());
        tvPomodorosPrevistos.setText(tarefa.getTempoPrevisto().toString());

        pomodorosExecutadosNaTarefa = tarefa.getTempoExcutado() != null ? tarefa.getTempoExcutado() : 0;
        tvPomodorosExecutados.setText(pomodorosExecutadosNaTarefa.toString());
    }

    @Override
    public void bloquearBotoes() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                btnFinalizarTarefa.setEnabled(false);
                btnFinalizarTarefa.setBackgroundColor(getResources().getColor(R.color.darker_gray));

                desabilitarIntervaloLongo();
                desabilitarIntervaloCurto();
                desabilitarConcentracao();
                desabilitarExecutar();
                desabilitarParar();
                desabilitarPause();
            }
        });
    }
}
