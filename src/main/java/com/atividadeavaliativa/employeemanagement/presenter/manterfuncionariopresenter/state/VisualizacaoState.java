package com.atividadeavaliativa.employeemanagement.presenter.manterfuncionariopresenter.state;

import com.atividadeavaliativa.employeemanagement.model.Funcionario;
import com.atividadeavaliativa.employeemanagement.presenter.manterfuncionariopresenter.ManterFuncionarioPresenter;
import com.atividadeavaliativa.employeemanagement.utils.DataFormat;
import com.atividadeavaliativa.employeemanagement.view.ManterFuncionarioView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VisualizacaoState extends ManterFuncionarioPresenterState {

    private ManterFuncionarioView view;
    private Funcionario funcionacio;

    public VisualizacaoState(ManterFuncionarioPresenter manterFuncionarioPresenter) {
        super(manterFuncionarioPresenter);
        view = this.presenter.getView();
        initListeners();
        configurarView();

    }

    @Override
    public void editar() {
        //fazer alguma coisa
    }
    
    @Override
    public void excluir(){
         //fazer alguma coisa
    }
    

    private void initListeners() {
        view.getBtEditar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("//mudar estado ");
            }
        });
        

        view.getBtExcluir().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("//excluir iitem");
            }
        });

        view.getBtFechar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
            }
        });
    }

    private void configurarView() {
        this.presenter.getView().setEnabled(false);
        this.presenter.getView().getBtSalvar().setEnabled(false);
        this.presenter.getView().getBtFechar().setEnabled(true);
        this.presenter.getView().getBtEditar().setEnabled(true);
        this.presenter.getView().getBtExcluir().setEnabled(true);
        this.presenter.getView().getTfNome().setText(funcionacio.getNome());
        this.presenter.getView().getTfNome().setEditable(false);
        this.presenter.getView().getTfIdade().setText(String.valueOf(funcionacio.getIdade()));
        this.presenter.getView().getTfIdade().setEditable(false);
        this.presenter.getView().getTfFaltas().setText(String.valueOf(funcionacio.getQuantidaDeFaltas()));
        this.presenter.getView().getTfFaltas().setEditable(false);
        this.presenter.getView().getTfSalario().setText(String.valueOf(funcionacio.getSalarioBase()));
        this.presenter.getView().getTfSalario().setEditable(false);
        this.presenter.getView().getCbCargo().setSelectedItem(funcionacio.getDescricaoCargo());
        this.presenter.getView().getCbCargo().setEditable(false);
        this.presenter.getView().getFtDataAdmissao().setText(DataFormat.parseLocalDateToString(funcionacio.getDataAdmissao()));
        this.presenter.getView().getFtDataAdmissao().setEditable(false);
        this.presenter.getView().getCbbFuncionárioDoMês().setSelected(funcionacio.isFuncionarioDoMes());
        this.presenter.getView().getCbbFuncionárioDoMês().setEnabled(false);
        this.presenter.getView().getCbBonus().setSelectedItem(funcionacio.getBonusCumulativo().get(0).getDescricao());
        this.presenter.getView().getCbBonus().setEnabled(false);
        this.presenter.getView().setEnabled(true);
    }

}
