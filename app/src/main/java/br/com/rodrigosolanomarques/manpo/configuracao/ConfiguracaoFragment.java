package br.com.rodrigosolanomarques.manpo.configuracao;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.preference.Preference;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import com.takisoft.fix.support.v7.preference.PreferenceFragmentCompat;

import br.com.rodrigosolanomarques.manpo.R;
import br.com.rodrigosolanomarques.manpo.config.SharedPreferencesConfiguracaoManpo;


public class ConfiguracaoFragment extends PreferenceFragmentCompat {

    private Preference listPreferenceIntervaloCurto;
    private Preference listPreferenceIntervaloLongo;
    private Preference switchPreferenceConcentracao;
    private Preference switchPreferenceIntervaloCurto;
    private Preference switchPreferenceIntervaloLongo;


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

        recuperarPreferences();
        configurarOnPreferenceChangeListener();

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void configurarOnPreferenceChangeListener() {

        listPreferenceIntervaloCurto.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object o) {
                Integer valor = Integer.valueOf(o.toString());
                SharedPreferencesConfiguracaoManpo.salvarIntervaloCurto(getContext(), valor);
                return true;
            }
        });

        listPreferenceIntervaloLongo.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object o) {
                Integer valor = Integer.valueOf(o.toString());
                SharedPreferencesConfiguracaoManpo.salvarIntervaloLongo(getContext(), valor);
                return true;
            }
        });

        switchPreferenceConcentracao.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object o) {
                SharedPreferencesConfiguracaoManpo.salvarNotificarConcentracao(getActivity(), (Boolean) o);
                return true;
            }
        });

        switchPreferenceIntervaloCurto.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object o) {
                SharedPreferencesConfiguracaoManpo.salvarNotificarIntervaloCurto(getActivity(), (Boolean) o);
                return true;
            }
        });

        switchPreferenceIntervaloLongo.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object o) {
                SharedPreferencesConfiguracaoManpo.salvarNotificarIntervaloLongo(getActivity(), (Boolean) o);
                return true;
            }
        });
    }

    private void recuperarPreferences() {

        listPreferenceIntervaloCurto = findPreference("p_intervalo_curto");
        listPreferenceIntervaloLongo = findPreference("p_intervalo_longo");

        switchPreferenceConcentracao = findPreference(SharedPreferencesConfiguracaoManpo.NOTIFICACAO_CONCENTRACAO);
        switchPreferenceIntervaloCurto = findPreference(SharedPreferencesConfiguracaoManpo.NOTIFICACAO_INTERVALO_CURTO);
        switchPreferenceIntervaloLongo = findPreference(SharedPreferencesConfiguracaoManpo.NOTIFICACAO_INTERVALO_LONGO);
    }

    private void configurarToolbar() {
        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.configuracao);
    }
}
