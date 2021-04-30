package com.atividadeavaliativa.employeemanagement.presenter;

import com.atividadeavaliativa.employeemanagement.model.collections.FuncionarioCollection;
import com.atividadeavaliativa.employeemanagement.view.TelaPrincipalView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaPrincipalPresenter {
    
    private static TelaPrincipalPresenter instence = null;
    private final TelaPrincipalView view;
    private final FuncionarioCollection funcionarios;
    private BuscarFuncionarioPresenter buscarFuncionarioPresenter;
    private ManterFuncionarioPresenter manterFuncionarioPresenter;
    private CalculaSalarioPresenter calculaSalarioPresenter;
    
    private TelaPrincipalPresenter(FuncionarioCollection collection) {
        
        view = new TelaPrincipalView();
        view.setSize(1300, 610);
        view.setLocationRelativeTo(view.getParent());
        view.setVisible(true);
        funcionarios = collection;
        iniciarViews();
        funcionarios.registerObserver(buscarFuncionarioPresenter);
        
        initListeners();
    }
    
    public static TelaPrincipalPresenter getInstance(FuncionarioCollection collection) {
        if (instence == null) {
            instence = new TelaPrincipalPresenter(collection);
        }
        return instence;
    }
    
    private void iniciarViews() {
        manterFuncionarioPresenter = new ManterFuncionarioPresenter();
        buscarFuncionarioPresenter = new BuscarFuncionarioPresenter(funcionarios);
        calculaSalarioPresenter = new CalculaSalarioPresenter(funcionarios);
    }
    
    private void initListeners() {
        view.getMenuItemManterFuncionario().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.add(manterFuncionarioPresenter.getView());
                manterFuncionarioPresenter.getView().setVisible(true);
            }
        });
        
        view.getMenuItemBuscarFuncionario().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.add(buscarFuncionarioPresenter.getView());
                buscarFuncionarioPresenter.getView().setVisible(true);
            }
        });
        
        view.getMenuItemCalculaSalario().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.add(calculaSalarioPresenter.getView());                
                calculaSalarioPresenter.getView().setVisible(true);
            }
        });
    }
}
