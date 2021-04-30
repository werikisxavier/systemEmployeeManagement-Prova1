
package com.atividadeavaliativa.employeemanagement.model.observer;

import com.atividadeavaliativa.employeemanagement.model.observer.IObserver;



public interface ISubject {
    
    public void registerObserver(IObserver o);
    public void removeObserver(IObserver o);
    public void notifyObservers();
    
}
