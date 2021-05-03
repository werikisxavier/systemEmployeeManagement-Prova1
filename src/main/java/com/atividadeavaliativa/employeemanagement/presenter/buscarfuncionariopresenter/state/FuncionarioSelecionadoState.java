package com.atividadeavaliativa.employeemanagement.presenter.buscarfuncionariopresenter.state;

import com.atividadeavaliativa.employeemanagement.model.Funcionario;
import com.atividadeavaliativa.employeemanagement.model.collections.FuncionarioCollection;
import com.atividadeavaliativa.employeemanagement.presenter.VerBonusPresenter;
import com.atividadeavaliativa.employeemanagement.presenter.buscarfuncionariopresenter.BuscarFuncionarioPresenter;
import com.atividadeavaliativa.employeemanagement.presenter.manterfuncionariopresenter.ManterFuncionarioPresenter;
import com.atividadeavaliativa.employeemanagement.presenter.manterfuncionariopresenter.state.VisualizarState;
import com.atividadeavaliativa.employeemanagement.view.BuscarFuncionarioView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class FuncionarioSelecionadoState extends BuscarFuncionarioPresenterState {

    private BuscarFuncionarioView view;
    private Integer idFuncionario;
    private List<Funcionario> funcionarios;
    private Funcionario funcionario;

    public FuncionarioSelecionadoState(BuscarFuncionarioPresenter buscarFuncionarioPresenter) {
        super(buscarFuncionarioPresenter);
        view = this.presenter.getView();
        this.idFuncionario = getFuncionarioSelecionado();
        funcionario = getFuncionario();
        initListeners();
        configurarView();
    }

    @Override
    public void visualizar() {
        ManterFuncionarioPresenter.getInstance(this.presenter.getGerenciadorLog()).setEstado(new VisualizarState(
                ManterFuncionarioPresenter.getInstance(this.presenter.getGerenciadorLog()),
                funcionario));
    }

    @Override
    public void verBonus() {
        new VerBonusPresenter(funcionario);
        this.presenter.getGerenciadorLog().getLog().whiteBonusConsulta(funcionario);
    }

    private void initListeners() {

        view.getBtVisualizar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                visualizar();
            }
        });

        view.getBtVerBonus().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verBonus();
            }
        });

        view.getBtFechar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fechar();
            }
        });

        view.getBtBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscar();
            }
        });
    }

    private void configurarView() {
        this.presenter.getView().setEnabled(false);
        this.presenter.getView().getBtBuscar().setEnabled(true);
        this.presenter.getView().getBtFechar().setEnabled(true);
        this.presenter.getView().getBtNovo().setEnabled(false);
        this.presenter.getView().getBtVisualizar().setEnabled(true);
        this.presenter.getView().getBtVerBonus().setEnabled(true);
        this.presenter.getView().getTfNome().setText("");
        this.presenter.getView().getTfNome().setEditable(true);
        this.presenter.getView().setEnabled(true);

    }

    private Integer getFuncionarioSelecionado() {
        Integer idSelecionado = (Integer) (view.getTblFuncionarios().getValueAt(view.getTblFuncionarios().getSelectedRow(), 0));
        return idSelecionado;
    }

    private Funcionario getFuncionario() {
        if (idFuncionario != null) {
            funcionarios = FuncionarioCollection.getInstance().getFuncionarios();

            for (Funcionario func : funcionarios) {
                if (func.getId().equals(idFuncionario)) {
                    return func;
                }
            }
        }
        return null;
    }

    private void buscar() {
        this.presenter.buscarPorNome();
    }
}
