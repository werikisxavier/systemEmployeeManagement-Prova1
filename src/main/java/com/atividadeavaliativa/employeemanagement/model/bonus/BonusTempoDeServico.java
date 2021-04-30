package com.atividadeavaliativa.employeemanagement.model.bonus;

import com.atividadeavaliativa.employeemanagement.model.Funcionario;
import java.time.LocalDate;

public class BonusTempoDeServico extends TipoBonus {

    public BonusTempoDeServico(String descricao, LocalDate dateRecebimento) {
        super(descricao, dateRecebimento);
    }

    @Override
    public double calculaValorBonus(Funcionario funcionario) throws Exception{
        this.valor = getBonus(funcionario.getAnosDeTrabalho(),funcionario.getSalarioBase());
        return this.valor;
    }
   
    private double getBonus(Integer tempoDeServico, double SalarioBase){
     if (tempoDeServico >= 20) { return SalarioBase * 0.15;}
     if (tempoDeServico >= 16) { return SalarioBase * 0.1;}
     if (tempoDeServico >= 11) { return SalarioBase * 0.08;}
     if (tempoDeServico >= 6) { return SalarioBase * 0.03;}
     if (tempoDeServico >= 1) { return SalarioBase * 0.02;}
     if (tempoDeServico == 0) { return 0.0;}  
     if (tempoDeServico < 0){
         throw new IllegalArgumentException("O data de Admissão é superior a data atual!");
     }
     
     return 0.0;
    }

}
