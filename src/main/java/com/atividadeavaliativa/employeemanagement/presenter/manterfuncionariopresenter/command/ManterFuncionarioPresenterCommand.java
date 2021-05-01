
package com.atividadeavaliativa.employeemanagement.presenter.manterfuncionariopresenter.command;

import com.atividadeavaliativa.employeemanagement.presenter.manterfuncionariopresenter.ManterFuncionarioPresenter;


public abstract class ManterFuncionarioPresenterCommand {
    protected ManterFuncionarioPresenter presenter;

    public ManterFuncionarioPresenterCommand(ManterFuncionarioPresenter manterFuncionarioPresenter) {
        this.presenter = manterFuncionarioPresenter;
    }
    
    public abstract void executar() throws Exception;
}
