package br.com.rodrigosolanomarques.manpo.configuracao;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import com.takisoft.fix.support.v7.preference.PreferenceFragmentCompat;

import br.com.rodrigosolanomarques.manpo.R;


public class ConfiguracaoFragment extends PreferenceFragmentCompat {


    public ConfiguracaoFragment() {

    }


    @Override
    public void onCreatePreferencesFix(@Nullable Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.configuracao, rootKey);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static ConfiguracaoFragment newInstance() {
        ConfiguracaoFragment fragment = new ConfiguracaoFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        configurarToolbar();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void configurarToolbar() {
        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.configuracao);
    }
}
