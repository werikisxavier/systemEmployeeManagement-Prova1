
package com.atividadeavaliativa.employeemanagement.logs;

import com.atividadeavaliativa.employeemanagement.model.Funcionario;
import java.util.List;

public interface ILogStrategy {
    
    public void writeFuncionarioOperacao(String operation, Funcionario funcionario);
    
    public void whiteBonusConsulta(Funcionario funcionario);
    
    public void whiteCalculoRealizado(List<Funcionario> funcionarios);
    
    public void writeFalha(String falha);
}
