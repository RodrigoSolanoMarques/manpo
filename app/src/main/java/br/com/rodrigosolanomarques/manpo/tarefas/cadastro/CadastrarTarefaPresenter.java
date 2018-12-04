package br.com.rodrigosolanomarques.manpo.tarefas.cadastro;

import br.com.rodrigosolanomarques.manpo.R;
import br.com.rodrigosolanomarques.manpo.data.local.TarefaDao;
import br.com.rodrigosolanomarques.manpo.data.model.Tarefa;

public class CadastrarTarefaPresenter implements CadastrarTarefaContract.Presenter {

    private CadastrarTarefaContract.View view;
    private Tarefa tarefa;
    private TarefaDao tarefaDao;

    public CadastrarTarefaPresenter(CadastrarTarefaContract.View view, TarefaDao tarefaDao) {
        this.view = view;
        this.view.setPresenter(this);
        this.tarefaDao = tarefaDao;
    }

    @Override
    @Deprecated
    public void start() {
    }

    @Override
    public void start(long idTarefa) {
        if (idTarefa > 0) {
            buscarTarefa(idTarefa);
            view.mostrarBotaoExcluir();
        } else {
            tarefa = new Tarefa();
        }
        view.preencherTarefa(tarefa);
        if (tarefa.isFinalizada()) {
            view.bloquearCampos();
        }
    }

    @Override
    public void buscarTarefa(long idTarefa) {
        tarefa = tarefaDao.findById(idTarefa);
    }

    @Override
    public void preencherTarefa(Tarefa tarefa) {
        this.tarefa.setDescricao(tarefa.getDescricao());
        this.tarefa.setTempoPrevisto(tarefa.getTempoPrevisto());
        this.tarefa.setDataCriacao(tarefa.getDataCriacao());
        this.tarefa.setDataFinalizacao(tarefa.getDataFinalizacao());
        this.tarefa.setPrioridade(tarefa.getPrioridade());
        this.tarefa.setFinalizada(tarefa.isFinalizada());
    }

    @Override
    public void salvarTarefa() {

        if (tarefa.getId() == 0) {
            Long idTarefa = tarefaDao.insert(tarefa);
            tarefa.setId(idTarefa);
//            view.mostrarAlertDialogInfoCadastrarAtividade();
            view.mostrarToast(R.string.info_tarefa_salva);
            view.finalizarTela();
            return;
        }
        tarefaDao.update(tarefa);
        view.mostrarToast(R.string.info_tarefa_salva);
        view.finalizarTela();

    }

    @Override
    public void deletarTarefa() {
        tarefaDao.delete(tarefa);
    }
}
