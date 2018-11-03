package br.com.rodrigosolanomarques.manpo.inicio;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.rodrigosolanomarques.manpo.R;

public class InicioFragment extends Fragment implements InicioContract.View {

    private InicioContract.Presenter presenter;

    private TextView tvPomodoroHoje;
    private TextView tvPomodoroSemana;
    private TextView tvPomodoroMes;

    public InicioFragment() {
    }

    public static InicioFragment newInstance() {
        InicioFragment fragment = new InicioFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_inicio, container, false);

        tvPomodoroHoje = layout.findViewById(R.id.tvPomodoroHoje);
        tvPomodoroSemana = layout.findViewById(R.id.tvPomodoroSemana);
        tvPomodoroMes = layout.findViewById(R.id.tvPomodoroMes);

        return layout;
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

    @Override
    public void atualiarPomodoHoje(int valor) {
        tvPomodoroHoje.setText(String.valueOf(valor));
    }

    @Override
    public void atualiarPomodoSemana(int valor) {
        tvPomodoroSemana.setText(String.valueOf(valor));
    }

    @Override
    public void atualiarPomodoMes(int valor) {
        tvPomodoroMes.setText(String.valueOf(valor));
    }
}
