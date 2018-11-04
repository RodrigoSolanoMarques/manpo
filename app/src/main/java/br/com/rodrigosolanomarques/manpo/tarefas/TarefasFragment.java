package br.com.rodrigosolanomarques.manpo.tarefas;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import br.com.rodrigosolanomarques.manpo.R;

public class TarefasFragment extends Fragment {


    public TarefasFragment() {
        // Required empty public constructor
    }

    public static TarefasFragment newInstance() {
        TarefasFragment fragment = new TarefasFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        configurarToolbar();

        View layout = inflater.inflate(R.layout.fragment_tarefas, container, false);

        // pegar o recycle view

        return layout;
    }

    private void configurarToolbar() {
        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.tarefas);
    }

}
