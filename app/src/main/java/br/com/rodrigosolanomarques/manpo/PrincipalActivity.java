package br.com.rodrigosolanomarques.manpo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toolbar;

import br.com.rodrigosolanomarques.manpo.configuracao.ConfiguracaoFragment;
import br.com.rodrigosolanomarques.manpo.inicio.InicioFragment;
import br.com.rodrigosolanomarques.manpo.inicio.InicioPresenter;
import br.com.rodrigosolanomarques.manpo.tarefas.TarefasFragment;

public class PrincipalActivity extends AppCompatActivity implements PrincipalContract.View {

    private PrincipalContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        configurarToolbar();

        BottomNavigationView navigationView = findViewById(R.id.bottomNavigationView);
        navigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener());

//        new PrincipalPresenter();

        InicioFragment inicioFragment = new InicioFragment();
        new InicioPresenter(inicioFragment);
        carregarFragment(inicioFragment);
    }

    @NonNull
    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener() {
        return new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.inicio:
                        InicioFragment inicioFragment = InicioFragment.newInstance();
                        // Fazer um singleton - fazer
                        new InicioPresenter(inicioFragment);
                        carregarFragment(inicioFragment);
                        return true;

                    case R.id.tarefas:
                        TarefasFragment tarefasFragment = TarefasFragment.newInstance();
                        carregarFragment(tarefasFragment);
                        return true;

                    case R.id.configuracao:
                        ConfiguracaoFragment configuracaoFragment = ConfiguracaoFragment.newInstance();
                        carregarFragment(configuracaoFragment);
                        return true;
                }
                return false;
            }
        };
    }

    private void carregarFragment(Fragment fragment) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment, fragment);
        fragmentTransaction.commit();
    }

    private void configurarToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setLogo(R.drawable.ic_apple);
        setActionBar(toolbar);
    }

    @Override
    public void setPresenter(PrincipalContract.Presenter presenter) {
        this.presenter = presenter;
    }
}