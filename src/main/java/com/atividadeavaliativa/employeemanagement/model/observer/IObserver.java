package com.atividadeavaliativa.employeemanagement.model.observer;

import com.atividadeavaliativa.employeemanagement.model.Funcionario;
import java.util.List;

public interface IObserver {

    public void update(List<Funcionario> funcionarios);
}
