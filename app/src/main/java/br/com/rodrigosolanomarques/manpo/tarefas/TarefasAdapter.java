package br.com.rodrigosolanomarques.manpo.tarefas;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
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
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Tarefa tarefa);

        void executarTarefa(Tarefa tarefa);
    }

    public TarefasAdapter(Context context, List tarefas, OnItemClickListener listener) {
        this.tarefas = tarefas;
        this.context = context;
        this.listener = listener;
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
        tarefaHolder.tvTempo.setText(String.valueOf(tarefa.getTempoPrevisto()));
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
            tarefaHolder.ivConcluido.setVisibility(View.VISIBLE);
            tarefaHolder.ivExecutar.setVisibility(View.GONE);
        } else {
            tarefaHolder.ivConcluido.setVisibility(View.GONE);
            tarefaHolder.ivExecutar.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return tarefas.size();
    }

    class TarefaHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView ivPrioridade;
        private ImageView ivConcluido;
        private ImageView ivExecutar;
        private TextView tvTitulo;
        private TextView tvTempo;
        private TextView tvDataCriacao;
        private CardView cardView;


        public TarefaHolder(@NonNull View itemView) {
            super(itemView);
            ivPrioridade = itemView.findViewById(R.id.ivPrioridade);
            ivConcluido = itemView.findViewById(R.id.ivConcluido);
            ivExecutar = itemView.findViewById(R.id.ivExecutar);
            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            tvTempo = itemView.findViewById(R.id.tvTempo);
            tvDataCriacao = itemView.findViewById(R.id.tvDataCriacao);
            cardView = itemView.findViewById(R.id.cardView);
            cardView.setOnClickListener(this);

            ivExecutar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.executarTarefa(tarefas.get(getAdapterPosition()));
                }
            });
        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(tarefas.get(getAdapterPosition()));
        }
    }
}
