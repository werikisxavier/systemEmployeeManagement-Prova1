package com.atividadeavaliativa.employeemanagement.presenter.VerBonusPresenter;

import com.atividadeavaliativa.employeemanagement.presenter.TelaPrincipalPresenter.TelaPrincipalPresenter;
import com.atividadeavaliativa.employeemanagement.model.Funcionario;
import com.atividadeavaliativa.employeemanagement.model.bonus.TipoBonus;
import com.atividadeavaliativa.employeemanagement.utils.FormatarData;
import com.atividadeavaliativa.employeemanagement.view.TelaPrincipalView;
import com.atividadeavaliativa.employeemanagement.view.VerBonusView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public final class VerBonusPresenter {

    private final TelaPrincipalView view;
    private final VerBonusView jDialog;
    private final Funcionario funcionario;
    private DefaultTableModel tbWeatherDatas;
    
    public VerBonusPresenter(Funcionario funcionario) {
        view = TelaPrincipalPresenter.getInstance().getView();
        jDialog = new VerBonusView(view, true);
        jDialog.setSize(420, 280);
        jDialog.setLocationRelativeTo(view.getParent());
        tableInit();
        initListeners();
        this.funcionario=funcionario;
        configurarView();
        atualizarTabela(this.funcionario);
        jDialog.setVisible(true);
    }


    public VerBonusView getView() {
        return jDialog;
    }

    private void configurarView() {
        this.getView().setEnabled(false);
        this.getView().getBtFechar().setEnabled(true); 
        this.getView().getTfNome().setText(funcionario.getNome());
        this.getView().getTfNome().setEditable(false);
        this.getView().getTfCargo().setText(funcionario.getDescricaoCargo());
        this.getView().getTfCargo().setEditable(false);
        this.getView().setEnabled(true);
        
    }
    
        
    private void clearTable() {
        if (tbWeatherDatas.getRowCount() > 0) {
            for (int i = tbWeatherDatas.getRowCount() - 1; i > -1; i--) {
                tbWeatherDatas.removeRow(i);
            }
        }
    }
    
    private void tableInit() {
        tbWeatherDatas = new DefaultTableModel(
                new Object[][][]{},
                new String[]{"Tipo de Bonus", "Data de recebimento", "Valor (R$)"}
        );
        jDialog.getTblBonus().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbWeatherDatas.setNumRows(0);
        
        jDialog.getTblBonus().setModel(tbWeatherDatas);
    }
    
    public void atualizarTabela(Funcionario funcionario) {
        List<TipoBonus> listBonus = funcionario.getBonusCumulativo();
        if (listBonus != null) {
            clearTable();
            for (TipoBonus bonus : listBonus) {
                tbWeatherDatas.addRow(new Object[]{
                    bonus.getDescricao(),
                    FormatarData.parseLocalDateToString(bonus.getDateRecebimento()),
                    bonus.calculaValorBonus(funcionario)
                });
            }
        }
    }

    private void initListeners() {
       this.jDialog.getBtFechar().addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
              jDialog.setVisible(false); 
           }
       });
    }
    
}
