package com.atividadeavaliativa.employeemanagement.presenter.manterfuncionariopresenter.command;

import com.atividadeavaliativa.employeemanagement.model.Cargo;
import com.atividadeavaliativa.employeemanagement.model.Funcionario;
import com.atividadeavaliativa.employeemanagement.model.bonus.BonusAssiduidade;
import com.atividadeavaliativa.employeemanagement.model.bonus.BonusFuncionarioDoMes;
import com.atividadeavaliativa.employeemanagement.model.bonus.BonusGeneroso;
import com.atividadeavaliativa.employeemanagement.model.bonus.BonusNormal;
import com.atividadeavaliativa.employeemanagement.model.bonus.BonusPorCargo;
import com.atividadeavaliativa.employeemanagement.model.bonus.BonusTempoDeServico;
import com.atividadeavaliativa.employeemanagement.model.bonus.TipoBonus;
import com.atividadeavaliativa.employeemanagement.model.collections.FuncionarioCollection;
import com.atividadeavaliativa.employeemanagement.presenter.manterfuncionariopresenter.ManterFuncionarioPresenter;
import com.atividadeavaliativa.employeemanagement.utils.FormatarData;
import java.time.LocalDate;

public class InserirCommand extends ManterFuncionarioPresenterCommand {

    public InserirCommand(ManterFuncionarioPresenter manterFuncionarioPresenter) {
        super(manterFuncionarioPresenter);
        view = this.presenter.getView();
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
        LocalDate dataAdmissao = FormatarData.parseStringToLocalDate(view.getFtDataAdmissao().getText());

        double salarioBase = Double.valueOf(view.getTfSalario().getText());
        boolean IsFuncionarioDoMes = verificarOpcaoFuncionarioDoMes();
        Funcionario funcionario = new Funcionario(
                getIdFuncionario(),
                nome,
                idade,
                quantidaDeFaltas,
                new Cargo(cargo, salarioBase),
                dataAdmissao,
                IsFuncionarioDoMes);
        
        FuncionarioCollection.getInstance().addFuncionario(funcionario);
        
        TipoBonus bonusIndependente = getBonusIndependenteFuncionario();
        TipoBonus bonusTempoDeServico =  new BonusTempoDeServico("Tempo de serviço", LocalDate.now());
        TipoBonus bonusAssiduidade =  new BonusAssiduidade("Assiduidade", LocalDate.now());
        TipoBonus bonusFuncionarioDoMes = new BonusFuncionarioDoMes("Funcionario do Mês", LocalDate.now());
        TipoBonus bonusPorCargo = new BonusPorCargo("Por cargo", LocalDate.now());
        
        funcionario.addBonus(bonusIndependente);
        funcionario.addBonus(bonusTempoDeServico);
        funcionario.addBonus(bonusAssiduidade);
        funcionario.addBonus(bonusPorCargo);
        
        if (IsFuncionarioDoMes) {
            funcionario.addBonus(bonusFuncionarioDoMes);
        }
        
        
    }

    private TipoBonus getBonusIndependenteFuncionario() {
        if (view.getCbBonus().getSelectedItem().equals("Normal")) {
            return new BonusNormal("Normal", LocalDate.now());
        } else {
            return new BonusGeneroso("Generoso", LocalDate.now());
        }
    }
    
    

}
