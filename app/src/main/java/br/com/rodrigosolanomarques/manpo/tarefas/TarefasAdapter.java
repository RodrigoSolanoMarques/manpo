package br.com.rodrigosolanomarques.manpo.tarefas;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import br.com.rodrigosolanomarques.manpo.R;
import br.com.rodrigosolanomarques.manpo.data.model.Tarefa;

public class TarefasAdapter extends RecyclerView.Adapter<TarefasAdapter.TarefaHolder> {

    private final List<Tarefa> tarefas;
    private Context context;

    public TarefasAdapter(Context context, List tarefas) {
        this.tarefas = tarefas;
        this.context = context;
    }

    @NonNull
    @Override
    public TarefaHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new TarefaHolder(
                LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.fragment_tarefas_item,
                                viewGroup,
                                false));
    }

    @Override
    public void onBindViewHolder(@NonNull TarefaHolder tarefaHolder, int position) {

        Tarefa tarefa = tarefas.get(position);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        tarefaHolder.tvTitulo.setText(tarefa.getDescricao());
        tarefaHolder.tvTempo.setText(String.valueOf(tarefa.getTempo()));
        tarefaHolder.tvDataCriacao.setText(sdf.format(tarefa.getDataCriacao()));

        switch (tarefa.getPrioridade()) {
            case ALTA:
                tarefaHolder.ivPrioridade.getDrawable()
                        .setTint(context.getResources().getColor(R.color.alta));
                break;
            case BAIXA:
                tarefaHolder.ivPrioridade.getDrawable()
                        .setTint(context.getResources().getColor(R.color.baixa));
                break;
            case MEDIA:
                tarefaHolder.ivPrioridade.getDrawable()
                        .setTint(context.getResources().getColor(R.color.media));
                break;
        }

        if (tarefa.isFinalizada()) {
            tarefaHolder.ivFinalizado.setVisibility(View.VISIBLE);
        } else {
            tarefaHolder.ivFinalizado.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return tarefas.size();
    }

    class TarefaHolder extends RecyclerView.ViewHolder {
        private ImageView ivPrioridade;
        private ImageView ivFinalizado;
        private TextView tvTitulo;
        private TextView tvTempo;
        private TextView tvDataCriacao;


        public TarefaHolder(@NonNull View itemView) {
            super(itemView);
            ivPrioridade = itemView.findViewById(R.id.ivPrioridade);
            ivFinalizado = itemView.findViewById(R.id.ivFinalizado);
            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            tvTempo = itemView.findViewById(R.id.tvTempo);
            tvDataCriacao = itemView.findViewById(R.id.tvDataCriacao);
        }
    }
}
