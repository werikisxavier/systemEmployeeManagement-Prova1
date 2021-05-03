
package com.atividadeavaliativa.employeemanagement.model.observer;

public interface ISubject {
    
    public void registerObserver(IObserver o);
    public void removeObserver(IObserver o);
    public void notifyObservers();
    
}
