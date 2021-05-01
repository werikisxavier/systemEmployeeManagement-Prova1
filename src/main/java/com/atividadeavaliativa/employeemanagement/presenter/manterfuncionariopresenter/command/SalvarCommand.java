
package com.atividadeavaliativa.employeemanagement.presenter.manterfuncionariopresenter.command;

import com.atividadeavaliativa.employeemanagement.model.Cargo;
import com.atividadeavaliativa.employeemanagement.model.Funcionario;
import com.atividadeavaliativa.employeemanagement.model.bonus.BonusGeneroso;
import com.atividadeavaliativa.employeemanagement.model.bonus.BonusNormal;
import com.atividadeavaliativa.employeemanagement.model.bonus.TipoBonus;
import com.atividadeavaliativa.employeemanagement.model.collections.FuncionarioCollection;
import com.atividadeavaliativa.employeemanagement.presenter.manterfuncionariopresenter.ManterFuncionarioPresenter;
import com.atividadeavaliativa.employeemanagement.utils.DataFormat;
import com.atividadeavaliativa.employeemanagement.view.ManterFuncionarioView;
import java.time.LocalDate;

public class SalvarCommand extends ManterFuncionarioPresenterCommand {

    private ManterFuncionarioView view;
    private static Integer idFuncionario = 1;
    private static boolean funcionarioDoMes = false;

    public SalvarCommand(ManterFuncionarioPresenter manterFuncionarioPresenter) {
        super(manterFuncionarioPresenter);
        view=this.presenter.getView();
    }
    
    @Override
    public void executar() throws Exception {
        addFuncionario();
    }

    private void addFuncionario() throws Exception {
        String cargo = String.valueOf(view.getCbCargo().getSelectedItem());
        String nome = view.getTfNome().getText();
        Integer idade = Integer.parseInt(view.getTfIdade().getText());
        Integer quantidaDeFaltas = Integer.parseInt((view.getTfFaltas().getText()));
        LocalDate dataAdmissao = DataFormat.parseStringToLocalDate(view.getFtDataAdmissao().getText());

        double salarioBase = Double.valueOf(view.getTfSalario().getText());
        boolean funcionarioDoMes1 = verificarOpcaoFuncionarioDoMes();
        Funcionario funcionario = new Funcionario(
                getIdFuncionario(),
                nome,
                idade,
                quantidaDeFaltas,
                new Cargo(cargo, salarioBase),
                dataAdmissao,
                funcionarioDoMes1);

        TipoBonus bunus = getBonusFuncionario();
        FuncionarioCollection.getInstance().addFuncionario(funcionario);
        funcionario.addBonus(bunus);
    }

    private TipoBonus getBonusFuncionario() {
        if (view.getCbBonus().getSelectedItem().equals("Normal")) {
            return new BonusNormal("Normal", LocalDate.now());
        } else {
            return new BonusGeneroso("Generoso", LocalDate.now());
        }
    }

    private boolean verificarOpcaoFuncionarioDoMes() throws Exception {
        if (view.getCbbFuncionárioDoMês().isSelected() == true) {
            verificarFuncionarioDoMes();
            funcionarioDoMes = true;
            return true;
        } else {
            return false;
        }
    }

    private void verificarFuncionarioDoMes() throws Exception {
        if (funcionarioDoMes) {
            throw new Exception("Já existe um funcionário do Mês!");
        }
    }

    private int getIdFuncionario() {
        return idFuncionario++;
    }
}
