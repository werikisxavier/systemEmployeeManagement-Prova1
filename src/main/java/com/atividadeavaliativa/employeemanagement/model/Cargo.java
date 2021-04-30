
package com.atividadeavaliativa.employeemanagement.model;


public class Cargo {
    
    private String descricao;
    private double SalarioBase;

    public Cargo() {
    }

    public Cargo(String descricao, double SalarioBase) {
        this.descricao = descricao;
        this.SalarioBase = SalarioBase;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getSalarioBase() {
        return SalarioBase;
    }

    public void setSalarioBase(double SalarioBase) {
        this.SalarioBase = SalarioBase;
    }
    
    
}
