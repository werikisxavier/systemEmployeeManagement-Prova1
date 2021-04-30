
package com.atividadeavaliativa.employeemanagement.model.bonus;

import com.atividadeavaliativa.employeemanagement.model.Funcionario;
import java.time.LocalDate;


public abstract class TipoBonus {
    
    protected String descricao;
    protected double valor;
    protected LocalDate dateRecebimento;

    public TipoBonus(String descricao, LocalDate dateRecebimento) {
        this.descricao = descricao;
        this.dateRecebimento = dateRecebimento;
    }
    
    public abstract double calculaValorBonus(Funcionario funcionario) throws Exception;
    
    
    
}
