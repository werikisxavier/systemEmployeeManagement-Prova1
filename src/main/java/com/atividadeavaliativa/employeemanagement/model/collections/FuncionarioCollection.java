package com.atividadeavaliativa.employeemanagement.model.collections;

import com.atividadeavaliativa.employeemanagement.model.Funcionario;
import com.atividadeavaliativa.employeemanagement.model.observer.IObserver;
import com.atividadeavaliativa.employeemanagement.model.observer.ISubject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FuncionarioCollection implements ISubject {

    private static FuncionarioCollection instancia = null;
    private final List<Funcionario> funcionarios;
    private final ArrayList<IObserver> observers;

    private FuncionarioCollection() {
        funcionarios = new ArrayList<>();
        observers = new ArrayList<>();
    }

    public static FuncionarioCollection getInstance() {
        if (instancia == null) {
            instancia = new FuncionarioCollection();
        }
        return instancia;
    }

    public void addFuncionario(Funcionario funcionario) {
        funcionarios.add(funcionario);
        notifyObservers();
    }

    public void removeFuncionario(Funcionario funcionario) {
        funcionarios.remove(funcionario);
        notifyObservers();
    }
    

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    @Override
    public void registerObserver(IObserver o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(IObserver o) {
        int i = observers.indexOf(o);
        if (i >= 0) {
            observers.remove(o);
        }
    }

    @Override
    public void notifyObservers() {

        observers.forEach(observer -> {
            observer.update(Collections.unmodifiableList(funcionarios));
        });
    }
}
