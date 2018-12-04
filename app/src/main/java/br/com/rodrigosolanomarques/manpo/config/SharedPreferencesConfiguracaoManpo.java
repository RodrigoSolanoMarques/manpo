package br.com.rodrigosolanomarques.manpo.config;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.preference.PreferenceManager;

public class SharedPreferencesConfiguracaoManpo {

    public static String INTERVALO_CURTO = "intervalo_curto";
    public static String INTERVALO_LONGO = "intervalo_longo";
    public static String CONCENTRACAO = "concentracao";
    public static String NOTIFICACAO_INTERVALO_CURTO = "notificacao_intervalo_curto";
    public static String NOTIFICACAO_INTERVALO_LONGO = "notificacao_intervalo_longo";
    public static String NOTIFICACAO_CONCENTRACAO = "notificacao_concentracao";

    private static SharedPreferences obterSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void salvarIntervaloCurto(Context context, int valor) {
        obterSharedPreferences(context).edit().putInt(INTERVALO_CURTO, valor).apply();
    }

    public static void salvarIntervaloLongo(Context context, int valor) {
        obterSharedPreferences(context).edit().putInt(INTERVALO_LONGO, valor).apply();
    }

    public static void salvarConcentracao(Context context, int valor) {
        obterSharedPreferences(context).edit().putInt(CONCENTRACAO, valor).apply();
    }

    public static int obterIntervaloCurto(Context context) {
        return obterSharedPreferences(context).getInt(INTERVALO_CURTO, 5);
    }

    public static int obterIntervaloLongo(Context context) {
        return obterSharedPreferences(context).getInt(INTERVALO_LONGO, 30);
    }

    public static int obterConcentracao(Context context) {
        return obterSharedPreferences(context).getInt(CONCENTRACAO, 25);
    }


    public static void salvarNotificarIntervaloCurto(Context context, boolean valor) {
        obterSharedPreferences(context).edit().putBoolean(NOTIFICACAO_INTERVALO_CURTO, valor).apply();
    }

    public static void salvarNotificarIntervaloLongo(Context context, boolean valor) {
        obterSharedPreferences(context).edit().putBoolean(NOTIFICACAO_INTERVALO_LONGO, valor).apply();
    }

    public static void salvarNotificarConcentracao(Context context, boolean valor) {
        obterSharedPreferences(context).edit().putBoolean(NOTIFICACAO_CONCENTRACAO, valor).apply();
    }

    public static boolean isNotificarIntervaloCurto(Context context) {
        return obterSharedPreferences(context).getBoolean(NOTIFICACAO_INTERVALO_CURTO, true);
    }

    public static boolean isNotificarIntervaloLongo(Context context) {
        return obterSharedPreferences(context).getBoolean(NOTIFICACAO_INTERVALO_LONGO, true);
    }

    public static boolean isNotificarConcentracao(Context context) {
        return obterSharedPreferences(context).getBoolean(NOTIFICACAO_CONCENTRACAO, true);
    }
}
