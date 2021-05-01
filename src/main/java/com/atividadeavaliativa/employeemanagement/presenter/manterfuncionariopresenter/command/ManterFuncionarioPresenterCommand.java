
package com.atividadeavaliativa.employeemanagement.presenter.manterfuncionariopresenter.command;

import com.atividadeavaliativa.employeemanagement.presenter.manterfuncionariopresenter.ManterFuncionarioPresenter;
import com.atividadeavaliativa.employeemanagement.view.ManterFuncionarioView;


public abstract class ManterFuncionarioPresenterCommand {
    protected ManterFuncionarioPresenter presenter;
    protected static Integer idFuncionario = 1;
    protected static boolean funcionarioDoMes = false;
    protected ManterFuncionarioView view;
    
    public ManterFuncionarioPresenterCommand(ManterFuncionarioPresenter manterFuncionarioPresenter) {
        this.presenter = manterFuncionarioPresenter;
        view = this.presenter.getView();
    }
    
    public abstract void executar() throws Exception;
    
    
    protected boolean verificarOpcaoFuncionarioDoMes() throws Exception {
        if (view.getCbbFuncionárioDoMês().isSelected() == true) {
            verificarFuncionarioDoMes();
            funcionarioDoMes = true;
            return true;
        } else {
            return false;
        }
    }

    protected void verificarFuncionarioDoMes() throws Exception {
        if (funcionarioDoMes) {
            throw new Exception("Já existe um funcionário do Mês!");
        }
    }

    protected int getIdFuncionario() {
        return idFuncionario++;
    }
}
