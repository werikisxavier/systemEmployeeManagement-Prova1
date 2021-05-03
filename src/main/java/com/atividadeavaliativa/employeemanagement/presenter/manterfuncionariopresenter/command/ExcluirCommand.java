package com.atividadeavaliativa.employeemanagement.presenter.manterfuncionariopresenter.command;

import com.atividadeavaliativa.employeemanagement.model.Funcionario;
import com.atividadeavaliativa.employeemanagement.model.collections.FuncionarioCollection;
import com.atividadeavaliativa.employeemanagement.presenter.manterfuncionariopresenter.ManterFuncionarioPresenter;

public class ExcluirCommand extends ManterFuncionarioPresenterCommand {
    
    private Funcionario funcionario;
    
    public ExcluirCommand(ManterFuncionarioPresenter manterFuncionarioPresenter, Funcionario funcionario) {
        super(manterFuncionarioPresenter);
        this.funcionario = funcionario;
    }
    
    @Override
    public void executar() {
        excluirFuncionario();
    }
    
    private void excluirFuncionario() {
        FuncionarioCollection.getInstance().removeFuncionario(funcionario);
        this.presenter.getGerenciadorLog().getLog().writeFuncionarioOperacao("exclusão", funcionario);
    }
    
}
