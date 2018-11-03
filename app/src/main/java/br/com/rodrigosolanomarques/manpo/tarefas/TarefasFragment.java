package br.com.rodrigosolanomarques.manpo.tarefas;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        return inflater.inflate(R.layout.fragment_tarefas, container, false);
    }

}
