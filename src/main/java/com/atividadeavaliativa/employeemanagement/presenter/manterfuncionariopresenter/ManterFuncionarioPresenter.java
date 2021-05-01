package com.atividadeavaliativa.employeemanagement.presenter.manterfuncionariopresenter;

import com.atividadeavaliativa.employeemanagement.presenter.manterfuncionariopresenter.state.InclusaoState;
import com.atividadeavaliativa.employeemanagement.presenter.manterfuncionariopresenter.state.ManterFuncionarioPresenterState;
import com.atividadeavaliativa.employeemanagement.view.ManterFuncionarioView;

public class ManterFuncionarioPresenter {

    private static ManterFuncionarioPresenter instence = null;
    private final ManterFuncionarioView view;
    private ManterFuncionarioPresenterState estado;

    private ManterFuncionarioPresenter() {

        view = new ManterFuncionarioView();
        view.setSize(640, 235);
        this.estado = new InclusaoState(this);
    }
    
    public static ManterFuncionarioPresenter getInstance() {
        if (instence == null) {
            instence = new ManterFuncionarioPresenter();
        }
        return instence;
    }

    public ManterFuncionarioView getView() {
        return view;
    }

    public ManterFuncionarioPresenterState getEstado() {
        return estado;
    }

    public void setEstado(ManterFuncionarioPresenterState estado) {
        this.estado = estado;
    }

}
