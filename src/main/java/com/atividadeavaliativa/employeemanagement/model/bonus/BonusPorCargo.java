
package com.atividadeavaliativa.employeemanagement.model.bonus;

import com.atividadeavaliativa.employeemanagement.model.Funcionario;
import java.time.LocalDate;


public class BonusPorCargo extends TipoBonus{

    public BonusPorCargo(String descricao, LocalDate dateRecebimento) {
        super(descricao, dateRecebimento);
    }

    @Override
    public double calculaValorBonus(Funcionario funcionario) {
        this.valor = getBonus(funcionario);
        return this.valor;
    }
   
    private double getBonus(Funcionario funcionario){
        double SalarioBase = funcionario.getSalarioBase();
        if (funcionario.getDescricaoCargo().equals("Diretor")) { return SalarioBase * 0.15;}
        if (funcionario.getDescricaoCargo().equals("Gerente")) { return SalarioBase * 0.10;}
        if (funcionario.getDescricaoCargo().equals("Secretario")) { return SalarioBase * 0.05;}
        if (funcionario.getDescricaoCargo().equals("Porteiro")) { return SalarioBase * 0.05;}
        if (funcionario.getDescricaoCargo().equals("Motorista")) { return SalarioBase * 0.05;}
        if (funcionario.getDescricaoCargo().equals("Programador")) { return SalarioBase * 0.05;}
        throw new IllegalArgumentException("Erro: Cargo não inicializado!");
    }
}
