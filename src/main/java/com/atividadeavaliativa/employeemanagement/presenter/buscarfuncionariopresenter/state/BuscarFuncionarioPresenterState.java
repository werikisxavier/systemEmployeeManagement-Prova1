
package com.atividadeavaliativa.employeemanagement.presenter.buscarfuncionariopresenter.state;

import com.atividadeavaliativa.employeemanagement.presenter.buscarfuncionariopresenter.BuscarFuncionarioPresenter;
import java.awt.Component;
import java.awt.event.ActionListener;
import javax.swing.JButton;


public abstract class BuscarFuncionarioPresenterState {
    
    
    protected BuscarFuncionarioPresenter presenter;

    public BuscarFuncionarioPresenterState(BuscarFuncionarioPresenter buscarFuncionarioPresenter) {
        this.presenter = buscarFuncionarioPresenter;
        limpaListeners();
    }

    public void incluir() throws Exception {
        throw new RuntimeException("Não é possivel salvar a partir desse estado!");
    }

    public void visualizar() throws Exception {
        throw new RuntimeException("Não é possivel editar a partir desse estado!");
    }

    public void verBonus() throws Exception {
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
