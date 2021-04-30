package com.atividadeavaliativa.employeemanagement.presenter;

import com.atividadeavaliativa.employeemanagement.model.collections.FuncionarioCollection;
import com.atividadeavaliativa.employeemanagement.presenter.manterFuncionarioState.InclusaoState;
import com.atividadeavaliativa.employeemanagement.presenter.manterFuncionarioState.ManterFuncionarioPresenterState;
import com.atividadeavaliativa.employeemanagement.view.ManterFuncionarioView;


public class ManterFuncionarioPresenter {

    private final ManterFuncionarioView view;
    private ManterFuncionarioPresenterState estado;

    public ManterFuncionarioPresenter() {

        view = new ManterFuncionarioView();
        view.setSize(640, 235);
        this.estado=new InclusaoState(this); 
    }

    public ManterFuncionarioView getView() {
        return view;
    }

     public ManterFuncionarioPresenterState getEstado()
    {
        return estado;
    }

    public void setEstado(ManterFuncionarioPresenterState estado)
    {
        this.estado = estado;
    }
    
}
