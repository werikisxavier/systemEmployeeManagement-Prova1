package com.atividadeavaliativa.employeemanagement.presenter.buscarfuncionariopresenter;

import com.atividadeavaliativa.employeemanagement.model.Funcionario;
import com.atividadeavaliativa.employeemanagement.model.observer.IObserver;
import com.atividadeavaliativa.employeemanagement.presenter.buscarfuncionariopresenter.state.BuscarFuncionarioPresenterState;
import com.atividadeavaliativa.employeemanagement.presenter.buscarfuncionariopresenter.state.VisualizacaoState;
import com.atividadeavaliativa.employeemanagement.view.BuscarFuncionarioView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class BuscarFuncionarioPresenter implements IObserver {
    
    private BuscarFuncionarioPresenterState estado;
    //private static BuscarFuncionarioPresenter instence = null;
    private final BuscarFuncionarioView view;
    private DefaultTableModel tbWeatherDatas;
    
    
    public BuscarFuncionarioPresenter() {
        
        view = new BuscarFuncionarioView();
        view.setSize(640, 350);
        view.setLocation(645, 0);
        
        tableInit();
        initListeners();
        this.estado= new VisualizacaoState(this);
    }
    
    public BuscarFuncionarioView getView() {
        return view;
    }
    
    private void initListeners() {
        view.getBtFechar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
            }
        });
   
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
                new Object[][][][][]{},
                new String[]{"ID", "Nome", "Idade", "Função", "Salário Base (R$)"}
        );
        
        view.getTblFuncionarios().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbWeatherDatas.setNumRows(0);
        
        view.getTblFuncionarios().setModel(tbWeatherDatas);
    }
    

    public BuscarFuncionarioPresenterState getEstado() {
        return estado;
    }

    public void setEstado(BuscarFuncionarioPresenterState estado) {
        this.estado = estado;
    }
    
    
    @Override
    public void update(List<Funcionario> funcionarios) {
        if (funcionarios != null) {
            clearTable();
            for (Funcionario funcionario : funcionarios) {
                tbWeatherDatas.addRow(new Object[]{
                    funcionario.getId(),
                    funcionario.getNome(),
                    funcionario.getIdade(),
                    funcionario.getCargo().getDescricao(),
                    funcionario.getCargo().getSalarioBase()
                });
            }
        }
    }
    
}
