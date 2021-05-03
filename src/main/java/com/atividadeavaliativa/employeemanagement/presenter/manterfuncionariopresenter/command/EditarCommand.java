package com.atividadeavaliativa.employeemanagement.presenter.manterfuncionariopresenter.command;

import com.atividadeavaliativa.employeemanagement.model.Cargo;
import com.atividadeavaliativa.employeemanagement.model.Funcionario;
import com.atividadeavaliativa.employeemanagement.model.bonus.BonusGeneroso;
import com.atividadeavaliativa.employeemanagement.model.bonus.BonusNormal;
import com.atividadeavaliativa.employeemanagement.model.bonus.TipoBonus;
import com.atividadeavaliativa.employeemanagement.model.collections.FuncionarioCollection;
import com.atividadeavaliativa.employeemanagement.presenter.manterfuncionariopresenter.ManterFuncionarioPresenter;
import com.atividadeavaliativa.employeemanagement.utils.FormatarData;
import java.time.LocalDate;

public class EditarCommand extends ManterFuncionarioPresenterCommand {

    private Integer idFuncionario;

    public EditarCommand(ManterFuncionarioPresenter manterFuncionarioPresenter, Integer idFuncionario) {
        super(manterFuncionarioPresenter);
        this.idFuncionario = idFuncionario;

    }

    @Override
    public void executar() throws Exception {
        editarFuncionario();
    }

    private void editarFuncionario() throws Exception {

        String cargoDescricao = String.valueOf(view.getCbCargo().getSelectedItem());
        String nome = view.getTfNome().getText();
        Integer idade = Integer.parseInt(view.getTfIdade().getText());
        Integer quantidaDeFaltas = Integer.parseInt((view.getTfFaltas().getText()));
        LocalDate dataAdmissao = FormatarData.parseStringToLocalDate(view.getFtDataAdmissao().getText());

        double salarioBase = Double.valueOf(view.getTfSalario().getText());
        boolean funcionarioDoMes1 = verificarOpcaoFuncionarioDoMes();
        Cargo cargo = new Cargo(cargoDescricao, salarioBase);

        for (Funcionario func : FuncionarioCollection.getInstance().getFuncionarios()) {
            if (func.getId().equals(idFuncionario)) {
                func.setNome(nome);
                func.setIdade(idade);
                func.setQuantidaDeFaltas(quantidaDeFaltas);
                func.setDataAdmissao(dataAdmissao);
                func.setFuncionarioDoMes(funcionarioDoMes1);
                func.setCargo(cargo);
                TipoBonus bunus = getBonusFuncionario();
                func.addBonus(bunus);
                FuncionarioCollection.getInstance().notifyObservers();
                this.presenter.getGerenciadorLog().getLog().writeFuncionarioOperacao("edicão", func);
            }
        }
    }

    private TipoBonus getBonusFuncionario() {
        if (view.getCbBonus().getSelectedItem().equals("Normal")) {
            return new BonusNormal("Normal", LocalDate.now());
        } else {
            return new BonusGeneroso("Generoso", LocalDate.now());
        }
    }

}
