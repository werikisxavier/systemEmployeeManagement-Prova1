package com.atividadeavaliativa.employeemanagement.presenter.manterfuncionariopresenter.state;

import com.atividadeavaliativa.employeemanagement.model.Funcionario;
import com.atividadeavaliativa.employeemanagement.presenter.buscarfuncionariopresenter.BuscarFuncionarioPresenter;
import com.atividadeavaliativa.employeemanagement.presenter.buscarfuncionariopresenter.state.VisualizacaoState;
import com.atividadeavaliativa.employeemanagement.presenter.manterfuncionariopresenter.ManterFuncionarioPresenter;
import com.atividadeavaliativa.employeemanagement.presenter.manterfuncionariopresenter.command.EditarCommand;
import com.atividadeavaliativa.employeemanagement.utils.FormatarData;
import com.atividadeavaliativa.employeemanagement.view.ManterFuncionarioView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class EditarState extends ManterFuncionarioPresenterState {

    private ManterFuncionarioView view;
    private Funcionario funcionario;

    public EditarState(ManterFuncionarioPresenter manterFuncionarioPresenter, Funcionario funcionario) {
        super(manterFuncionarioPresenter, funcionario);
        view = this.presenter.getView();
        this.funcionario = funcionario;
        configurarView();
        initListeners();

    }

    @Override
    public void salvar() throws Exception {
        new EditarCommand(this.presenter, funcionario.getId()).executar();
        JOptionPane.showMessageDialog(view, "Funcionário editado com sucesso!");
        BuscarFuncionarioPresenter.getInstance(this.presenter.getGerenciadorLog()).setEstado(
                new VisualizacaoState(BuscarFuncionarioPresenter.getInstance(
                        this.presenter.getGerenciadorLog())));
        this.presenter.setEstado(new VisualizarState(presenter, funcionario));
    }

    private void initListeners() {
        view.getBtSalvar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    salvar();
                } catch (Exception ex) {
                    gerarlogException(ex.getMessage());
                    JOptionPane.showMessageDialog(view, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
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
        this.presenter.getView().getBtSalvar().setEnabled(true);
        this.presenter.getView().getBtFechar().setEnabled(true);
        this.presenter.getView().getBtEditar().setEnabled(false);
        this.presenter.getView().getBtExcluir().setEnabled(false);
        this.presenter.getView().getTfNome().setText(this.funcionario.getNome());
        this.presenter.getView().getTfNome().setEditable(true);
        this.presenter.getView().getTfIdade().setText(String.valueOf(this.funcionario.getIdade()));
        this.presenter.getView().getTfIdade().setEditable(true);
        this.presenter.getView().getTfFaltas().setText(String.valueOf(this.funcionario.getQuantidaDeFaltas()));
        this.presenter.getView().getTfFaltas().setEditable(true);
        this.presenter.getView().getTfSalario().setText(String.valueOf(this.funcionario.getSalarioBase()));
        this.presenter.getView().getTfSalario().setEditable(true);
        this.presenter.getView().getCbCargo().setSelectedItem(this.funcionario.getDescricaoCargo());
        this.presenter.getView().getCbCargo().setEditable(true);
        this.presenter.getView().getCbCargo().setEnabled(true);
        this.presenter.getView().getFtDataAdmissao().setText(FormatarData.parseLocalDateToString(this.funcionario.getDataAdmissao()));
        this.presenter.getView().getFtDataAdmissao().setEditable(true);
        this.presenter.getView().getCbbFuncionárioDoMês().setSelected(this.funcionario.isFuncionarioDoMes());
        this.presenter.getView().getCbbFuncionárioDoMês().setEnabled(true);
        this.presenter.getView().getCbBonus().setSelectedItem(this.funcionario.getBonusCumulativo().get(0).getDescricao());
        this.presenter.getView().getCbBonus().setEditable(true);
        this.presenter.getView().getCbBonus().setEnabled(true);
        this.presenter.getView().setEnabled(true);
    }

    private void gerarlogException(String falha) {
        this.presenter.getGerenciadorLog().getLog().writeFalha(falha);
    }
}
