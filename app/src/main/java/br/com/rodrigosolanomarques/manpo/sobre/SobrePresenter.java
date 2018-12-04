package br.com.rodrigosolanomarques.manpo.sobre;

import android.support.annotation.NonNull;

public class SobrePresenter implements SobreContract.Presenter {

    private SobreContract.View view;

    public SobrePresenter(@NonNull SobreContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
