package com.atividadeavaliativa.employeemanagement.presenter.manterfuncionariopresenter.state;

import com.atividadeavaliativa.employeemanagement.model.Funcionario;
import com.atividadeavaliativa.employeemanagement.model.collections.FuncionarioCollection;
import com.atividadeavaliativa.employeemanagement.presenter.manterfuncionariopresenter.ManterFuncionarioPresenter;
import com.atividadeavaliativa.employeemanagement.presenter.manterfuncionariopresenter.command.ExcluirCommand;
import com.atividadeavaliativa.employeemanagement.utils.FormatarData;
import com.atividadeavaliativa.employeemanagement.view.ManterFuncionarioView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;

public class VisualizarState extends ManterFuncionarioPresenterState {

    private ManterFuncionarioView view;
    private List<Funcionario> funcionarios;
    private Funcionario funcionario;

    public VisualizarState(ManterFuncionarioPresenter manterFuncionarioPresenter, Funcionario funcionario) {
        super(manterFuncionarioPresenter, funcionario);
        view = this.presenter.getView();
        this.funcionario = funcionario;
        configurarView();
        initListeners();
    }

    @Override
    public void editar() {
        this.presenter.setEstado(new EditarState(presenter, funcionario));
    }

    @Override
    public void excluir() {
        int respota = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir o Funcionário:\n"
                + "ID: "
                + String.valueOf(funcionario.getId())
                + ", Nome: "
                + funcionario.getNome(),
                 "Confirmação de exclusão", JOptionPane.YES_NO_OPTION);
        if (respota == 0) {
            new ExcluirCommand(this.presenter, funcionario).executar();
            JOptionPane.showMessageDialog(view, "Funcionário Excluido com sucesso!");
            fechar();
        }
    }

    private void initListeners() {
        view.getBtEditar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editar();
            }
        });

        view.getBtExcluir().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluir();
            }
        });

        view.getBtFechar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fechar();
            }
        });
    }

    private void configurarView() {
        this.presenter.getView().setEnabled(false);
        this.presenter.getView().getBtSalvar().setEnabled(false);
        this.presenter.getView().getBtFechar().setEnabled(true);
        this.presenter.getView().getBtEditar().setEnabled(true);
        this.presenter.getView().getBtExcluir().setEnabled(true);
        this.presenter.getView().getTfNome().setText(this.funcionario.getNome());
        this.presenter.getView().getTfNome().setEditable(false);
        this.presenter.getView().getTfIdade().setText(String.valueOf(this.funcionario.getIdade()));
        this.presenter.getView().getTfIdade().setEditable(false);
        this.presenter.getView().getTfFaltas().setText(String.valueOf(this.funcionario.getQuantidaDeFaltas()));
        this.presenter.getView().getTfFaltas().setEditable(false);
        this.presenter.getView().getTfSalario().setText(String.valueOf(this.funcionario.getSalarioBase()));
        this.presenter.getView().getTfSalario().setEditable(false);
        this.presenter.getView().getCbCargo().setSelectedItem(this.funcionario.getDescricaoCargo());
        this.presenter.getView().getCbCargo().setEnabled(false);
        this.presenter.getView().getFtDataAdmissao().setText(FormatarData.parseLocalDateToString(this.funcionario.getDataAdmissao()));
        this.presenter.getView().getFtDataAdmissao().setEditable(false);
        this.presenter.getView().getCbbFuncionárioDoMês().setSelected(this.funcionario.isFuncionarioDoMes());
        this.presenter.getView().getCbbFuncionárioDoMês().setEnabled(false);
        this.presenter.getView().getCbBonus().setSelectedItem(this.funcionario.getBonusCumulativo().get(0).getDescricao());
        this.presenter.getView().getCbBonus().setEnabled(false);
        this.presenter.getView().setEnabled(true);
    }

}
