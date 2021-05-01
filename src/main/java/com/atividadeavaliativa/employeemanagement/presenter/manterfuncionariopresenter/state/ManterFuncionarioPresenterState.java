package com.atividadeavaliativa.employeemanagement.presenter.manterfuncionariopresenter.state;

import com.atividadeavaliativa.employeemanagement.model.Funcionario;
import com.atividadeavaliativa.employeemanagement.presenter.manterfuncionariopresenter.ManterFuncionarioPresenter;
import java.awt.Component;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public abstract class ManterFuncionarioPresenterState {

    protected ManterFuncionarioPresenter presenter;
    

    public ManterFuncionarioPresenterState(ManterFuncionarioPresenter manterFuncionarioPresenter, Funcionario funcionario) {
        this.presenter = manterFuncionarioPresenter;
        limpaListeners();
    }

    public void salvar() throws Exception {
        throw new RuntimeException("Não é possivel salvar a partir desse estado!");
    }

    public void editar() throws Exception {
        throw new RuntimeException("Não é possivel editar a partir desse estado!");
    }

    public void excluir() throws Exception {
        throw new RuntimeException("Não é possivel excluir a partir desse estado!");
    }

    public final void fechar() {
        this.presenter.getView().setVisible(false);
        this.presenter.getView().dispose();
    }

    private void limpaListeners() {

        for (Component c : this.presenter.getView().getContentPane().getComponents()) {
            if (c instanceof JButton) {
                for (ActionListener al : ((JButton) c).getActionListeners()) {
                    ((JButton) c).removeActionListener(al);
                }
            }
        }
    }

}
