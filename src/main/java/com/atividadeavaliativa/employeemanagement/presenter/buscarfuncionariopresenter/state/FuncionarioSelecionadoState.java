package com.atividadeavaliativa.employeemanagement.presenter.buscarfuncionariopresenter.state;

import com.atividadeavaliativa.employeemanagement.model.Funcionario;
import com.atividadeavaliativa.employeemanagement.presenter.buscarfuncionariopresenter.BuscarFuncionarioPresenter;
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
    //ver funcionario
    }

    @Override
    public void verBonus() {
    //ver funcionario
    }
    
    private void getFuncionarioSelecionado(){
    Integer idSelecionado = (Integer)view.getTblFuncionarios().getValueAt(0, view.getTblFuncionarios().getSelectedRow());  
        
        System.out.println("oi"+idSelecionado);
        
    }

    private void initListeners() {
        view.getBtVisualizar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               //ver
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
}
