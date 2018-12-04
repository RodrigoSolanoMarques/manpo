package br.com.rodrigosolanomarques.manpo.pomodoro;

import br.com.rodrigosolanomarques.manpo.BasePresenter;
import br.com.rodrigosolanomarques.manpo.BaseView;
import br.com.rodrigosolanomarques.manpo.data.model.Tarefa;

public interface PomodoroContract {

    interface View extends BaseView<Presenter> {


        void preencherTarefaEmTela(Tarefa tarefa);

        void bloquearBotoes();
    }

    interface Presenter extends BasePresenter {

        void salvarTarefa();

        void finalizarTarefa();

        void adicionarUmPomodoroExecutado();
    }
}
