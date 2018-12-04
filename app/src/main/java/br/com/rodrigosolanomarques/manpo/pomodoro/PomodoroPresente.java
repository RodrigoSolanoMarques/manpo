package br.com.rodrigosolanomarques.manpo.pomodoro;

import java.util.Date;

import br.com.rodrigosolanomarques.manpo.data.local.TarefaDao;
import br.com.rodrigosolanomarques.manpo.data.model.Tarefa;

public class PomodoroPresente implements PomodoroContract.Presenter {

    private PomodoroContract.View view;
    private Tarefa tarefa;
    private TarefaDao tarefaDao;

    public PomodoroPresente(PomodoroContract.View view, Tarefa tarefa, TarefaDao tarefaDao) {
        this.view = view;
        this.view.setPresenter(this);

        this.tarefa = tarefa;
        this.tarefaDao = tarefaDao;
    }

    @Override
    public void salvarTarefa() {
        tarefaDao.update(tarefa);
    }

    @Override
    public void finalizarTarefa() {
        tarefa.setFinalizada(true);
        tarefa.setDataFinalizacao(new Date());
        salvarTarefa();
        view.bloquearBotoes();
    }

    @Override
    public void adicionarUmPomodoroExecutado() {
        int pomodoroExecutado = tarefa.getTempoExcutado() != null ? tarefa.getTempoExcutado() + 1 : 1;
        tarefa.setTempoExcutado(pomodoroExecutado);
        updateTarefa();
    }

    private void updateTarefa() {
        tarefaDao.update(tarefa);
    }

    @Override
    public void start() {
        view.preencherTarefaEmTela(tarefa);
    }
}
