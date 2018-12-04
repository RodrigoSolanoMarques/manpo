package br.com.rodrigosolanomarques.manpo.inicio;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import br.com.rodrigosolanomarques.manpo.R;

public class InicioFragment extends Fragment implements InicioContract.View {

    private InicioContract.Presenter presenter;

    public InicioFragment() {
    }

    public static InicioFragment newInstance() {
        return new InicioFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        configurarToolbar();
        View layout = inflater.inflate(R.layout.fragment_inicio, container, false);
        return layout;
    }

    private void configurarToolbar() {
        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.sobre);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public void setPresenter(InicioContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
