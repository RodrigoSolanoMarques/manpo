package br.com.rodrigosolanomarques.manpo.util;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import br.com.rodrigosolanomarques.manpo.R;

public class AlertDialogUtil {

    public static AlertDialog criarAlertDialog(AppCompatActivity appCompatActivity) {
        return criarAlertDialog(appCompatActivity, null);
    }

    public static AlertDialog criarAlertDialog(Activity activity) {
        return criarAlertDialog(activity, null);
    }

    public static AlertDialog criarAlertDialog(AppCompatActivity appCompatActivity, String mensagem) {
        AlertDialog.Builder builder = new AlertDialog.Builder(appCompatActivity);
        if (mensagem != null && !mensagem.isEmpty()) {
            return criarAlertDialogPersonalizado(builder, appCompatActivity.getLayoutInflater(), mensagem);
        }
        return criarAlertDialogPadrao(builder);
    }

    public static AlertDialog criarAlertDialog(Activity activity, String mensagem) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        if (mensagem != null && !mensagem.isEmpty()) {
            return criarAlertDialogPersonalizado(builder, activity.getLayoutInflater(), mensagem);
        }
        return criarAlertDialogPadrao(builder);
    }


    private static AlertDialog criarAlertDialogPersonalizado(AlertDialog.Builder builder,
                                                             LayoutInflater inflater,
                                                             String mensagem) {
        View view = inflater.inflate(R.layout.alert_dialog_progress_circle, null);
        TextView textView = view.findViewById(R.id.textView);
        textView.setText(mensagem);
        builder.setView(view);

        return criar(builder);
    }


    private static AlertDialog criarAlertDialogPadrao(AlertDialog.Builder builder) {
        builder.setView(R.layout.alert_dialog_progress_circle);
        return criar(builder);
    }

    private static AlertDialog criar(AlertDialog.Builder builder) {
        builder.setCancelable(false);
        return builder.create();
    }


}
