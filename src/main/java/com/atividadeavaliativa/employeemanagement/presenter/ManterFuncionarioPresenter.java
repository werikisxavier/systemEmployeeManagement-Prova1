package com.atividadeavaliativa.employeemanagement.presenter;

import com.atividadeavaliativa.employeemanagement.model.Bonus.BonusGeneroso;
import com.atividadeavaliativa.employeemanagement.model.Bonus.BonusNormal;
import com.atividadeavaliativa.employeemanagement.model.Bonus.TipoBonus;
import com.atividadeavaliativa.employeemanagement.model.Cargo;
import com.atividadeavaliativa.employeemanagement.model.Funcionario;
import com.atividadeavaliativa.employeemanagement.model.collections.FuncionarioCollection;
import com.atividadeavaliativa.employeemanagement.utils.DateFormat;
import com.atividadeavaliativa.employeemanagement.view.ManterFuncionarioView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import javax.swing.JOptionPane;

public class ManterFuncionarioPresenter {

    private static ManterFuncionarioPresenter instence = null;
    private final ManterFuncionarioView view;
    private final FuncionarioCollection funcionarios;
    private static Integer idFuncionario = 1;
    private static boolean funcionarioDoMes = false;

    public ManterFuncionarioPresenter(FuncionarioCollection collection) {

        view = new ManterFuncionarioView();
        view.setSize(640, 235);
        funcionarios = collection;

        initListeners();
    }

    public ManterFuncionarioView getView() {
        return view;
    }

    private void initListeners() {
        view.getBtSalvar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    addFuncionario();

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

    private void addFuncionario() throws Exception {
        String cargo = String.valueOf(view.getCbCargo().getSelectedItem());
        String nome = view.getTfNome();
        Integer idade = Integer.parseInt(view.getTfIdade());
        Integer quantidaDeFaltas = Integer.parseInt((view.getTfFaltas()));
        LocalDate dataAdmissao = DateFormat.parseStringToLocalDate(view.getFtDataAdmissao());

        double salarioBase = Double.valueOf(view.getTfSalario());
        boolean funcionarioDoMes1 = verificarOpcaoFuncionarioDoMes();

        Funcionario funcionario = new Funcionario(getIdFuncionario(), nome, idade, quantidaDeFaltas, new Cargo(cargo, salarioBase), dataAdmissao, funcionarioDoMes1);

        TipoBonus bunus = getBonusFuncionario();

        funcionarios.addFuncionario(funcionario);

        funcionario.addBonus(bunus);
    }

    private TipoBonus getBonusFuncionario() {
        if (view.getCbBonus().getSelectedItem().equals("Normal")) {
            return new BonusNormal("Bônus Normal", LocalDate.now());
        } else {
            return new BonusGeneroso("Bônus Generoso", LocalDate.now());
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
