package com.atividadeavaliativa.employeemanagement.presenter.buscarfuncionariopresenter;

import com.atividadeavaliativa.employeemanagement.logs.GerenciadorStrategy;
import com.atividadeavaliativa.employeemanagement.model.Funcionario;
import com.atividadeavaliativa.employeemanagement.model.observer.IObserver;
import com.atividadeavaliativa.employeemanagement.presenter.buscarfuncionariopresenter.state.BuscarFuncionarioPresenterState;
import com.atividadeavaliativa.employeemanagement.presenter.buscarfuncionariopresenter.state.VisualizacaoState;
import com.atividadeavaliativa.employeemanagement.view.BuscarFuncionarioView;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class BuscarFuncionarioPresenter implements IObserver {

    private static BuscarFuncionarioPresenter instence = null;
    private BuscarFuncionarioPresenterState estado;
    private final BuscarFuncionarioView view;
    private DefaultTableModel tbWeatherDatas;
    private List<Funcionario> funcionarios;
    private List<Funcionario> listBuscaFuncionarios;
    private GerenciadorStrategy log;

    private BuscarFuncionarioPresenter(GerenciadorStrategy log) {

        view = new BuscarFuncionarioView();
        view.setSize(640, 350);
        view.setLocation(645, 0);
        this.log = log;
        tableInit();
        this.estado = new VisualizacaoState(this);
    }

    public static BuscarFuncionarioPresenter getInstance(GerenciadorStrategy log) {
        if (instence == null) {
            instence = new BuscarFuncionarioPresenter(log);
        }
        return instence;
    }

    public BuscarFuncionarioView getView() {
        return view;
    }

    private void clearTable() {
        if (tbWeatherDatas.getRowCount() > 0) {
            for (int i = tbWeatherDatas.getRowCount() - 1; i > -1; i--) {
                tbWeatherDatas.removeRow(i);
            }
        }
    }

    private void tableInit() {
        tbWeatherDatas = new DefaultTableModel(
                new Object[][][][][]{},
                new String[]{"ID", "Nome", "Idade", "Função", "Salário Base (R$)"}
        );

        view.getTblFuncionarios().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbWeatherDatas.setNumRows(0);

        view.getTblFuncionarios().setModel(tbWeatherDatas);
    }

    public BuscarFuncionarioPresenterState getEstado() {
        return estado;
    }

    public void setEstado(BuscarFuncionarioPresenterState estado) {
        this.estado = estado;
    }

    @Override
    public void update(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
        if (funcionarios != null) {
            clearTable();
            for (Funcionario funcionario : funcionarios) {
                tbWeatherDatas.addRow(new Object[]{
                    funcionario.getId(),
                    funcionario.getNome(),
                    funcionario.getIdade(),
                    funcionario.getCargo().getDescricao(),
                    funcionario.getCargo().getSalarioBase()
                });
            }
        }
    }

    public void buscarPorNome() {
        listBuscaFuncionarios = new ArrayList<>();
        for (Funcionario funcionario : this.funcionarios) {
            if (this.getView().getTfNome().getText().equals(funcionario.getNome())) {
                listBuscaFuncionarios.add(funcionario);
            }
        }
        update(listBuscaFuncionarios);
    }

    public GerenciadorStrategy getGerenciadorLog() {
        return log;
    }

}
