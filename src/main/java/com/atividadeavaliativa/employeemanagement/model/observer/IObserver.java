package com.atividadeavaliativa.employeemanagement.model;

import java.util.List;

public interface IObserver {

    public void update(List<Funcionario> funcionarios);
}
