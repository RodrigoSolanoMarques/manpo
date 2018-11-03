package br.com.rodrigosolanomarques.manpo.inicio;

import br.com.rodrigosolanomarques.manpo.BasePresenter;
import br.com.rodrigosolanomarques.manpo.BaseView;

public interface InicioContract {

    interface View extends BaseView<Presenter> {

        void atualiarPomodoHoje(int valor);

        void atualiarPomodoSemana(int valor);

        void atualiarPomodoMes(int valor);

    }

    interface Presenter extends BasePresenter {

    }
}
