package br.com.rodrigosolanomarques.manpo.pomodoro;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import br.com.rodrigosolanomarques.manpo.R;

public class PomodoroActivity extends AppCompatActivity {

    public static String TAREFA = "tarefa";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pomodoro);
    }
}
