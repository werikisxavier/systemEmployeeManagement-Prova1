package com.atividadeavaliativa.employeemanagement.presenter.buscarfuncionariopresenter.state;

import com.atividadeavaliativa.employeemanagement.presenter.buscarfuncionariopresenter.BuscarFuncionarioPresenter;
import com.atividadeavaliativa.employeemanagement.presenter.manterfuncionariopresenter.ManterFuncionarioPresenter;
import com.atividadeavaliativa.employeemanagement.presenter.manterfuncionariopresenter.state.VisualizarState;
import com.atividadeavaliativa.employeemanagement.view.BuscarFuncionarioView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FuncionarioSelecionadoState extends BuscarFuncionarioPresenterState {

    private BuscarFuncionarioView view;

    public FuncionarioSelecionadoState(BuscarFuncionarioPresenter buscarFuncionarioPresenter) {
        super(buscarFuncionarioPresenter);
        view = this.presenter.getView();

        initListeners();
        configurarView();
    }

    @Override
    public void visualizar() {
        ManterFuncionarioPresenter.getInstance().setEstado(new VisualizarState(
                ManterFuncionarioPresenter.getInstance(),
                getFuncionarioSelecionado()));
    }

    @Override
    public void verBonus() {
        //ver funcionario
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
                //ver
            }
        });

        view.getBtFechar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fechar();
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
}
