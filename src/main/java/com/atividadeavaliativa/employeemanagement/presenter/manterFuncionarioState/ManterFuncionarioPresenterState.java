package com.atividadeavaliativa.employeemanagement.presenter.manterFuncionarioState;

import com.atividadeavaliativa.employeemanagement.presenter.ManterFuncionarioPresenter;
import java.awt.Component;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public abstract class ManterFuncionarioPresenterState {

    protected ManterFuncionarioPresenter presenter;

    public ManterFuncionarioPresenterState(ManterFuncionarioPresenter manterFuncionarioPresenter) {
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


    /*
    public void salvar() throws Exception {
    }

    public void editar() throws Exception {
    }

    public void excluir() throws Exception {
    }

    public void cancelar() {
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

    protected Funcionario lerDados() throws Exception {
        String nome = presenter.getView().getTxtNome().getText();
        String cargo = presenter.getView().getTxtCargo().getText();
        double salarioBase = 0;
        if (!presenter.getView().getTxtSalarioBase().getText().isEmpty()) {
            salarioBase = Double.parseDouble(presenter.getView().getTxtSalarioBase().getText().replace(",", "."));

        }
        Funcionario f = new Funcionario(nome, cargo, salarioBase);

        if (!presenter.getView().getTxtFaltas().getText().isEmpty()) {
            int faltas = Integer.parseInt(presenter.getView().getTxtFaltas().getText());
            f.setFaltas(faltas);
        }

        if (!presenter.getView().getTxtDistancia().getText().isEmpty()) {
            double distancia = Double.parseDouble(presenter.getView().getTxtDistancia().getText());
            f.setDistanciaDoTrabalho(distancia);
        }
        
        return f;
    }
     */
}
