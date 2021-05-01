package com.atividadeavaliativa.employeemanagement.model.bonus;

import com.atividadeavaliativa.employeemanagement.model.Funcionario;
import java.time.LocalDate;

public class BonusFuncionarioDoMes extends TipoBonus {

    public BonusFuncionarioDoMes(String descricao, LocalDate dateRecebimento) {
        super(descricao, dateRecebimento);
    }

    @Override
    public double calculaValorBonus(Funcionario funcionario) {
        this.valor = getBonus(funcionario.isFuncionarioDoMes());
        return this.valor;
    }

    private double getBonus(boolean isFuncionarioDoMes) {
        if (isFuncionarioDoMes == true) {
            return 200.00;
        }
        return 0.0;
    }
}
