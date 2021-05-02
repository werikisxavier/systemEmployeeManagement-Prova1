
package com.atividadeavaliativa.employeemanagement.model;

import java.util.Date;


public class HistoricoCalculo {
  
    private String nomeFuncionario;
    private Date dataCalculo;
    private double salarioBase;
    private double somaBonus;
    private double salario;

    public HistoricoCalculo(String nomeFuncionario, Date dataCalculo, double salarioBase, double somaBonus, double salario) {
        this.nomeFuncionario = nomeFuncionario;
        this.dataCalculo = dataCalculo;
        this.salarioBase = salarioBase;
        this.somaBonus = somaBonus;
        this.salario = salario;
    }

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public Date getDataCalculo() {
        return dataCalculo;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public double getSomaBonus() {
        return somaBonus;
    }

    public double getSalario() {
        return salario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    public void setDataCalculo(Date dataCalculo) {
        this.dataCalculo = dataCalculo;
    }

    public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
    }

    public void setSomaBonus(double somaBonus) {
        this.somaBonus = somaBonus;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
    
    
}
