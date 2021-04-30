package com.atividadeavaliativa.employeemanagement.presenter.manterFuncionarioState;

import com.atividadeavaliativa.employeemanagement.presenter.ManterFuncionarioPresenter;
import com.atividadeavaliativa.employeemanagement.presenter.manterFuncionarioCommand.SalvarCommand;
import com.atividadeavaliativa.employeemanagement.view.ManterFuncionarioView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class InclusaoState extends ManterFuncionarioPresenterState {

    private ManterFuncionarioView view;

    public InclusaoState(ManterFuncionarioPresenter manterFuncionarioPresenter) {
        super(manterFuncionarioPresenter);
        view = this.presenter.getView();
        initListeners();
        this.presenter.getView().setEnabled(false);
        this.presenter.getView().getBtSalvar().setEnabled(true);
        this.presenter.getView().getBtFechar().setEnabled(true);
        this.presenter.getView().getBtEditar().setEnabled(false);
        this.presenter.getView().getBtExcluir().setEnabled(false);
        this.presenter.getView().getTfNome().setText("");
        this.presenter.getView().getTfNome().setEditable(true);
        this.presenter.getView().getTfIdade().setText("");
        this.presenter.getView().getTfIdade().setEditable(true);
        this.presenter.getView().getTfFaltas().setText("");
        this.presenter.getView().getTfFaltas().setEditable(true);
        this.presenter.getView().getTfSalario().setText("");
        this.presenter.getView().getTfSalario().setEditable(true);
        this.presenter.getView().setEnabled(true);

    }

    @Override
    public void salvar() throws Exception {
        new SalvarCommand(this.presenter).executar();
        JOptionPane.showMessageDialog(view, "Funcionário inserido com sucesso!");
    }

    private void initListeners() {
        view.getBtSalvar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    salvar();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        view.getBtFechar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
            }
        });
    }

}
