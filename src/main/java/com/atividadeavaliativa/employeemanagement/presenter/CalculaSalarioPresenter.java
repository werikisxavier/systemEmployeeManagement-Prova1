package com.atividadeavaliativa.employeemanagement.presenter;

import com.atividadeavaliativa.employeemanagement.presenter.manterfuncionariopresenter.ManterFuncionarioPresenter;
import com.atividadeavaliativa.employeemanagement.model.collections.FuncionarioCollection;
import com.atividadeavaliativa.employeemanagement.view.CalculaSalarioView;

public class CalculaSalarioPresenter {

    private static ManterFuncionarioPresenter instence = null;
    private final CalculaSalarioView view;
    private final FuncionarioCollection funcionarios;

    public CalculaSalarioPresenter(FuncionarioCollection collection) {

        view = new CalculaSalarioView();
        view.setSize(640,290);
        view.setLocation(0, 230);
        funcionarios = collection;

    }

    public CalculaSalarioView getView() {
        return view;
    }

}
