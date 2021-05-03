package com.atividadeavaliativa.employeemanagement.presenter.ConfigurandoLogPresenter;

import com.atividadeavaliativa.employeemanagement.presenter.TelaPrincipalPresenter.TelaPrincipalPresenter;
import com.atividadeavaliativa.employeemanagement.logs.JSONLog;
import com.atividadeavaliativa.employeemanagement.logs.TXTLog;
import com.atividadeavaliativa.employeemanagement.logs.XMLLog;
import com.atividadeavaliativa.employeemanagement.view.ConfigurarLogView;
import com.atividadeavaliativa.employeemanagement.view.TelaPrincipalView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class ConfigurarLogPresenter {

    private final TelaPrincipalView view;
    private final ConfigurarLogView jDialog;

    public ConfigurarLogPresenter() {
        view = TelaPrincipalPresenter.getInstance().getView();
        jDialog = new ConfigurarLogView(view, true);
        jDialog.setSize(260, 120);
        jDialog.setLocationRelativeTo(view.getParent());
        initListeners();
        jDialog.setVisible(true);
    }

    private void initListeners() {
        jDialog.getBtClose().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jDialog.setVisible(false);
            }
        });

        jDialog.getBtSave().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    setLog();
                    JOptionPane.showMessageDialog(view, "Configurado com sucesso!");
                    jDialog.setVisible(false);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, "Error: "+ex.getMessage());
                }
            }
        });
    }

    private void setLog() throws Exception{
        int opcao = jDialog.getCbLog().getSelectedIndex();
        if (opcao == 0) {TelaPrincipalPresenter.getInstance().setLogStrategy(new JSONLog());}
        if (opcao == 1) {TelaPrincipalPresenter.getInstance().setLogStrategy(new XMLLog());}
        if (opcao == 2) {TelaPrincipalPresenter.getInstance().setLogStrategy(new TXTLog());} 
    }
}
