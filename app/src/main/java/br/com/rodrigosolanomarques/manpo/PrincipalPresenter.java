package br.com.rodrigosolanomarques.manpo;

import android.support.annotation.NonNull;

import br.com.rodrigosolanomarques.manpo.sobre.SobreContract;

public class PrincipalPresenter implements PrincipalContract.Presenter {

    private PrincipalContract.View view;
    private SobreContract.Presenter inicioPresenter;

    public PrincipalPresenter(@NonNull PrincipalContract.View view) {
        this.view = view;

        view.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
