package com.atividadeavaliativa.employeemanagement.presenter;

import com.atividadeavaliativa.employeemanagement.presenter.manterfuncionariopresenter.ManterFuncionarioPresenter;
import com.atividadeavaliativa.employeemanagement.presenter.buscarfuncionariopresenter.BuscarFuncionarioPresenter;
import com.atividadeavaliativa.employeemanagement.model.Funcionario;
import com.atividadeavaliativa.employeemanagement.model.collections.FuncionarioCollection;
import com.atividadeavaliativa.employeemanagement.model.observer.IObserver;
import com.atividadeavaliativa.employeemanagement.presenter.manterfuncionariopresenter.state.InclusaoState;
import com.atividadeavaliativa.employeemanagement.view.TelaPrincipalView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TelaPrincipalPresenter implements IObserver {

    private static TelaPrincipalPresenter instence = null;
    private final TelaPrincipalView view;
    private final FuncionarioCollection funcionarios;
    private BuscarFuncionarioPresenter buscarFuncionarioPresenter;
    private ManterFuncionarioPresenter manterFuncionarioPresenter;
    private CalculaSalarioPresenter calculaSalarioPresenter;
    private VerBonusPresenter verBonusPresenter;

    private TelaPrincipalPresenter() {

        view = new TelaPrincipalView();
        view.setSize(1300, 610);
        view.setLocationRelativeTo(view.getParent());
        view.setVisible(true);
        funcionarios = FuncionarioCollection.getInstance();;
        iniciarViews();
        funcionarios.registerObserver(buscarFuncionarioPresenter);
        funcionarios.registerObserver(this);
        initListeners();
    }

    public static TelaPrincipalPresenter getInstance() {
        if (instence == null) {
            instence = new TelaPrincipalPresenter();
        }
        return instence;
    }

    private void iniciarViews() {
        manterFuncionarioPresenter = ManterFuncionarioPresenter.getInstance();
        buscarFuncionarioPresenter = BuscarFuncionarioPresenter.getInstance();
        calculaSalarioPresenter = CalculaSalarioPresenter.getInstance();
    }

    private void initListeners() {
        view.getMenuItemManterFuncionario().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.add(manterFuncionarioPresenter.getView());
                manterFuncionarioPresenter.getView().setVisible(true);
                setStatePadrao();
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

    public TelaPrincipalView getView() {
        return view;
    }

    private void setStatePadrao() {
        manterFuncionarioPresenter.setEstado(new InclusaoState(manterFuncionarioPresenter, null));
    }

    @Override
    public void update(List<Funcionario> funcionarios) {
        view.setLbFuncionarios(String.valueOf(funcionarios.size()));
    }

}
