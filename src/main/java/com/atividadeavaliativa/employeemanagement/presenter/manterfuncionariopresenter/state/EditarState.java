
package com.atividadeavaliativa.employeemanagement.presenter.manterfuncionariopresenter.state;

import com.atividadeavaliativa.employeemanagement.model.Funcionario;
import com.atividadeavaliativa.employeemanagement.presenter.manterfuncionariopresenter.ManterFuncionarioPresenter;
import com.atividadeavaliativa.employeemanagement.presenter.manterfuncionariopresenter.command.SalvarCommand;
import com.atividadeavaliativa.employeemanagement.utils.DataFormat;
import com.atividadeavaliativa.employeemanagement.view.ManterFuncionarioView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;


public class EditarState extends ManterFuncionarioPresenterState{
    
    private ManterFuncionarioView view;
    private Funcionario funcionacio;

    public EditarState(ManterFuncionarioPresenter manterFuncionarioPresenter) {
        super(manterFuncionarioPresenter);
        view = this.presenter.getView();
        initListeners();
        configurarView();
    }

    @Override
    public void salvar() throws Exception {
        new SalvarCommand(this.presenter).executar();
        JOptionPane.showMessageDialog(view, "Funcionário editado com sucesso!");
    }

    private void initListeners() {
        view.getBtSalvar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    salvar();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
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
        this.presenter.getView().getBtSalvar().setEnabled(true);
        this.presenter.getView().getBtFechar().setEnabled(true);
        this.presenter.getView().getBtEditar().setEnabled(false);
        this.presenter.getView().getBtExcluir().setEnabled(false);
        this.presenter.getView().getTfNome().setText(funcionacio.getNome());
        this.presenter.getView().getTfNome().setEditable(true);
        this.presenter.getView().getTfIdade().setText(String.valueOf(funcionacio.getIdade()));
        this.presenter.getView().getTfIdade().setEditable(true);
        this.presenter.getView().getTfFaltas().setText(String.valueOf(funcionacio.getQuantidaDeFaltas()));
        this.presenter.getView().getTfFaltas().setEditable(true);
        this.presenter.getView().getTfSalario().setText(String.valueOf(funcionacio.getSalarioBase()));
        this.presenter.getView().getTfSalario().setEditable(true);
        this.presenter.getView().getCbCargo().setSelectedItem(funcionacio.getDescricaoCargo());
        this.presenter.getView().getCbCargo().setEditable(true);
        this.presenter.getView().getFtDataAdmissao().setText(DataFormat.parseLocalDateToString(funcionacio.getDataAdmissao()));
        this.presenter.getView().getFtDataAdmissao().setEditable(true);
        this.presenter.getView().getCbbFuncionárioDoMês().setSelected(funcionacio.isFuncionarioDoMes());
        this.presenter.getView().getCbbFuncionárioDoMês().setEnabled(true);
        this.presenter.getView().getCbBonus().setSelectedItem(funcionacio.getBonusCumulativo().get(0).getDescricao());
        this.presenter.getView().getCbBonus().setEditable(true);
        this.presenter.getView().setEnabled(true);
    }
}
