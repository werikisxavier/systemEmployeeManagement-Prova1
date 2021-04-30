package com.atividadeavaliativa.employeemanagement.model.Bonus;

import com.atividadeavaliativa.employeemanagement.model.Funcionario;
import java.time.LocalDate;


public class BonusGeneroso extends TipoBonus {

    public BonusGeneroso(String descricao, LocalDate dateRecebimento) {
        super(descricao, dateRecebimento);
    }

    @Override
    public double calculaValorBonus(Funcionario funcionario) throws Exception {
        this.valor=500.00;
        return this.valor;
    }

}
