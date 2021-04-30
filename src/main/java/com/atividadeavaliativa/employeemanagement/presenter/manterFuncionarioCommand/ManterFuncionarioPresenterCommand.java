
package com.atividadeavaliativa.employeemanagement.presenter.manterFuncionarioCommand;

import com.atividadeavaliativa.employeemanagement.presenter.ManterFuncionarioPresenter;


public abstract class ManterFuncionarioPresenterCommand {
    protected ManterFuncionarioPresenter presenter;

    public ManterFuncionarioPresenterCommand(ManterFuncionarioPresenter manterFuncionarioPresenter) {
        this.presenter = manterFuncionarioPresenter;
    }
    
    public abstract void executar() throws Exception;
}
