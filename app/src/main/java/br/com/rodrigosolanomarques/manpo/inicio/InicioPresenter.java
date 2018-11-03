package br.com.rodrigosolanomarques.manpo.inicio;

import android.support.annotation.NonNull;

public class InicioPresenter implements InicioContract.Presenter {

    private InicioContract.View view;

    public InicioPresenter(@NonNull InicioContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
