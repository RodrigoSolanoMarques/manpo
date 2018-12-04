package br.com.rodrigosolanomarques.manpo.pomodoro;

import android.annotation.SuppressLint;
import android.os.CountDownTimer;
import android.widget.TextView;

import java.util.Calendar;

public class MyCountDownTimer extends CountDownTimer {

    private TextView textView;
    private long tempoRestante = 0;

    public MyCountDownTimer(TextView textView, long millisInFuture) {
        super(millisInFuture, 1000);
        this.textView = textView;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onTick(long millisUntilFinished) {
        tempoRestante = millisUntilFinished;
        textView.setText(formatarMinutos(tempoRestante) + ":" + formatarSegundos(tempoRestante));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onFinish() {
        tempoRestante -= 1000;
        textView.setText(formatarMinutos(tempoRestante) + ":" + formatarSegundos(tempoRestante));
    }


    private String formatarMinutos(long tempo) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(tempo);
        return String.valueOf(c.get(Calendar.MINUTE));
    }

    private String formatarSegundos(long tempo) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(tempo);
        return String.valueOf(c.get(Calendar.SECOND));
    }
}
