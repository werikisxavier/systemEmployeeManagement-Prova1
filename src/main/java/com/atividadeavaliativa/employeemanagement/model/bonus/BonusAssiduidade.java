package com.atividadeavaliativa.employeemanagement.model.bonus;

import com.atividadeavaliativa.employeemanagement.model.Funcionario;
import java.time.LocalDate;


public class BonusAssiduidade extends TipoBonus {

    public BonusAssiduidade(String descricao, LocalDate dateRecebimento) {
        super(descricao, dateRecebimento);
    }

    @Override
    public double calculaValorBonus(Funcionario funcionario) throws Exception {
        this.valor = getBonus(funcionario.getQuantidaDeFaltas(), funcionario.getSalarioBase());

        return this.valor;
    }

    private double getBonus(Integer quantidadeFaltas, Double salarioBase) {
        if (quantidadeFaltas >= 4) { return salarioBase * 0.01;}
        if (quantidadeFaltas >= 1) { return salarioBase * 0.05;}
        if (quantidadeFaltas == 0) {return salarioBase * 0.1;}
        if (quantidadeFaltas < 0){
        throw new IllegalArgumentException("A quantidade de faltas não pode ser negativa!");
        }
        return 0.0;
    }

}
