package br.com.rodrigosolanomarques.manpo.config;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.preference.PreferenceManager;

public class SharedPreferencesConfiguracaoManpo {

    public static String INTERVALO_CURTO = "intervalo_curto";
    public static String INTERVALO_LONGO = "intervalo_longo";
    public static String CONCENTRACAO = "concentracao";
    public static String NOTIFICACAO_INTERVALO_CURTO = "notificacao_intervalo_curto";
    public static String NOTIFICACAO_INTERVALO_LONGO = "notificaocao_intervalo_longo";
    public static String NOTIFICACAO_CONCENTRACAO = "notificacao_concentracao";

    private static SharedPreferences obterSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static int obterIntervaloCurto(Context context) {
        String valor = obterSharedPreferences(context).getString(INTERVALO_CURTO, "5");
        return Integer.parseInt(valor);
    }

    public static int obterIntervaloLongo(Context context) {
        String valor = obterSharedPreferences(context).getString(INTERVALO_LONGO, "30");
        return Integer.parseInt(valor);
    }

    public static int obterConcentracao(Context context) {
        String valor = obterSharedPreferences(context).getString(CONCENTRACAO, "25");
        return Integer.parseInt(valor);
    }

    public static boolean notificarIntervaloCurto(Context context) {
        String valor = obterSharedPreferences(context).getString(NOTIFICACAO_INTERVALO_CURTO, "true");
        return Boolean.valueOf(valor);
    }

    public static boolean notificarIntervaloLongo(Context context) {
        String valor = obterSharedPreferences(context).getString(NOTIFICACAO_INTERVALO_LONGO, "true");
        return Boolean.valueOf(valor);
    }

    public static boolean notificarConcentracao(Context context) {
        String valor = obterSharedPreferences(context).getString(NOTIFICACAO_CONCENTRACAO, "true");
        return Boolean.valueOf(valor);
    }
}
