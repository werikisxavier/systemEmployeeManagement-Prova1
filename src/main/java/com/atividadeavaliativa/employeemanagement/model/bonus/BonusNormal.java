package com.atividadeavaliativa.employeemanagement.model.bonus;

import com.atividadeavaliativa.employeemanagement.model.Funcionario;
import java.time.LocalDate;


public class BonusNormal extends TipoBonus {

    public BonusNormal(String descricao, LocalDate dateRecebimento) {
        super(descricao, dateRecebimento);
    }
  
    @Override
    public double calculaValorBonus(Funcionario funcionario) throws Exception {
        this.valor= 100.00;
        return this.valor;
    }

}
