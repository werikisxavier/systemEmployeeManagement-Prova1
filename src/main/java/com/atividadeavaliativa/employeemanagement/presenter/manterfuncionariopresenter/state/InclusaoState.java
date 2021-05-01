package com.atividadeavaliativa.employeemanagement.presenter.manterfuncionariopresenter.state;

import com.atividadeavaliativa.employeemanagement.model.Funcionario;
import com.atividadeavaliativa.employeemanagement.presenter.manterfuncionariopresenter.ManterFuncionarioPresenter;
import com.atividadeavaliativa.employeemanagement.presenter.manterfuncionariopresenter.command.SalvarCommand;
import com.atividadeavaliativa.employeemanagement.view.ManterFuncionarioView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class InclusaoState extends ManterFuncionarioPresenterState {

    private ManterFuncionarioView view;

    public InclusaoState(ManterFuncionarioPresenter manterFuncionarioPresenter,Funcionario funcionario) {
        super(manterFuncionarioPresenter,funcionario);
        view = this.presenter.getView();
        initListeners();
        configurarView();
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
                fechar();
            }
        });
    }

    private void configurarView() {
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
        this.presenter.getView().getFtDataAdmissao().setText("");
        this.presenter.getView().getFtDataAdmissao().setEditable(true);
        this.presenter.getView().getCbCargo().setSelectedItem("Gerente");
        this.presenter.getView().getCbCargo().setEditable(true);
        this.presenter.getView().getCbCargo().setEnabled(true);
        this.presenter.getView().getCbBonus().setSelectedItem("Normal");
        this.presenter.getView().getCbBonus().setEditable(true);
        this.presenter.getView().getCbBonus().setEnabled(true);
        this.presenter.getView().getCbbFuncionárioDoMês().setSelected(false);
        this.presenter.getView().getCbbFuncionárioDoMês().setEnabled(true);
    }

}
