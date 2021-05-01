package com.atividadeavaliativa.employeemanagement.presenter;


import com.atividadeavaliativa.employeemanagement.view.CalculaSalarioView;

public class CalculaSalarioPresenter {

    private static CalculaSalarioPresenter instence = null;
    private final CalculaSalarioView view;

    private CalculaSalarioPresenter() {

        view = new CalculaSalarioView();
        view.setSize(640,290);
        view.setLocation(0, 230);

    }
    
    public static CalculaSalarioPresenter getInstance() {
        if (instence == null) {
            instence = new CalculaSalarioPresenter();
        }
        return instence;
    }

    public CalculaSalarioView getView() {
        return view;
    }

}
