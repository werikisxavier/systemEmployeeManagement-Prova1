/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atividadeavaliativa.employeemanagement.application;

import com.atividadeavaliativa.employeemanagement.model.collections.FuncionarioCollection;
import com.atividadeavaliativa.employeemanagement.presenter.TelaPrincipalPresenter;

public class Program {

    public static void main(String[] args) {
        FuncionarioCollection funcionarios = FuncionarioCollection.getInstance();
        
        TelaPrincipalPresenter.getInstance(funcionarios);
    }

}
