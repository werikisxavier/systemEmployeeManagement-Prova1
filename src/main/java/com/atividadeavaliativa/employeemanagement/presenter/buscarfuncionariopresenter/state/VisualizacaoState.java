package com.atividadeavaliativa.employeemanagement.presenter.buscarfuncionariopresenter.state;

import com.atividadeavaliativa.employeemanagement.presenter.TelaPrincipalPresenter;
import com.atividadeavaliativa.employeemanagement.presenter.buscarfuncionariopresenter.BuscarFuncionarioPresenter;
import com.atividadeavaliativa.employeemanagement.presenter.manterfuncionariopresenter.ManterFuncionarioPresenter;
import com.atividadeavaliativa.employeemanagement.view.BuscarFuncionarioView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VisualizacaoState extends BuscarFuncionarioPresenterState {

    private BuscarFuncionarioView view;

    public VisualizacaoState(BuscarFuncionarioPresenter buscarFuncionarioPresenter) {
        super(buscarFuncionarioPresenter);
        view = this.presenter.getView();

        initListeners();
        configurarView();
    }

    @Override
    public void incluir() {
        TelaPrincipalPresenter.getInstance().getView().add(ManterFuncionarioPresenter.getInstance(this.presenter.getGerenciadorLog()).getView());
        ManterFuncionarioPresenter.getInstance(this.presenter.getGerenciadorLog()).getView().setVisible(true);
    }

    public void visualizar() {
        this.presenter.setEstado(new FuncionarioSelecionadoState(this.presenter));

    }

    private void initListeners() {
        view.getBtNovo().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                incluir();
            }
        });

        view.getTblFuncionarios().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (view.getTblFuncionarios().getSelectedRow() > -1) {
                    visualizar();
                    getFuncionarioSelecionado();
                }
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
        this.presenter.getView().getBtNovo().setEnabled(true);
        this.presenter.getView().getBtVisualizar().setEnabled(false);
        this.presenter.getView().getBtVerBonus().setEnabled(false);
        this.presenter.getView().getTfNome().setText("");
        this.presenter.getView().getTfNome().setEditable(true);
        this.presenter.getView().setEnabled(true);
    }

    private void buscar(){
        this.presenter.buscarPorNome();
    }
    
    private Integer getFuncionarioSelecionado() {
        Integer idSelecionado = (Integer) (view.getTblFuncionarios().getValueAt(view.getTblFuncionarios().getSelectedRow(), 0));
        return idSelecionado;
    }
    
   
}
