package br.com.rodrigosolanomarques.manpo.tarefas.cadastro;

import android.support.annotation.IntegerRes;
import android.support.annotation.StringRes;

import br.com.rodrigosolanomarques.manpo.BasePresenter;
import br.com.rodrigosolanomarques.manpo.BaseView;
import br.com.rodrigosolanomarques.manpo.data.model.Tarefa;

public interface CadastrarTarefaContract {

    interface View extends BaseView<Presenter> {

        void preencherTarefa(Tarefa tarefa);

        boolean validarCampos();

        void mostrarBotaoExcluir();

        void esconderBotaoExcluir();

        void mostrarAlertDialogInfoCadastrarAtividade();

        void mostrarToast(@StringRes int msg);

        void finalizarTela();

        void bloquearCampos();
    }

    interface Presenter extends BasePresenter {

        void start(long idTarefa);

        void buscarTarefa(long idTarefa);

        void preencherTarefa(Tarefa tarefa);

        void salvarTarefa();

        void deletarTarefa();
    }

}
