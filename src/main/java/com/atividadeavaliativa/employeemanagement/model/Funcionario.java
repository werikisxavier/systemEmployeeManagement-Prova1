
package com.atividadeavaliativa.employeemanagement.model;

import com.atividadeavaliativa.employeemanagement.model.Bonus.TipoBonus;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;


public class Funcionario {
   
    private Integer id;
    private String nome;
    private double salario;
    private Integer idade;
    private Integer quantidaDeFaltas;
    private Cargo cargo;
    List<TipoBonus> bonusCumulativo;
    private LocalDate dataAdmissao;
    private boolean funcionarioDoMes;

    public Funcionario(Integer id, String nome, Integer idade, Integer quantidaDeFaltas, Cargo cargo, LocalDate dataAdmissao, boolean funcionarioDoMes) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.quantidaDeFaltas = quantidaDeFaltas;
        this.cargo = cargo;
        this.dataAdmissao = dataAdmissao;
        this.funcionarioDoMes = funcionarioDoMes;
        this.bonusCumulativo = new ArrayList<>();
    }
 
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public Integer getQuantidaDeFaltas() {
        return quantidaDeFaltas;
    }

    public void setQuantidaDeFaltas(Integer numeroDeFaltas) {
        this.quantidaDeFaltas = numeroDeFaltas;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public List<TipoBonus> getBonusCumulativo() {
        return bonusCumulativo;
    }
    
    public void addBonus(TipoBonus tipoBonus){
        bonusCumulativo.add(tipoBonus);
    }
    
    public void removeBonus(TipoBonus tipoBonus){
        bonusCumulativo.remove(tipoBonus);
    }

    public LocalDate getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(LocalDate dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public boolean isFuncionarioDoMes() {
        return funcionarioDoMes;
    }

    public void setFuncionarioDoMes(boolean funcionarioDoMes) {
        this.funcionarioDoMes = funcionarioDoMes;
    }
    
    public int getAnosDeTrabalho() {
        LocalDate dateNow = LocalDate.now();
        Period period = Period.between(this.dataAdmissao, dateNow);
        return period.getYears();
    }
    
    public double getSalarioBase(){
        return cargo.getSalarioBase();
    }
    
    
}
