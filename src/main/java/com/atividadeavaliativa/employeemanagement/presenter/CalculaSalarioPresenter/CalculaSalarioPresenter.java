package com.atividadeavaliativa.employeemanagement.presenter.CalculaSalarioPresenter;

import com.atividadeavaliativa.employeemanagement.logs.GerenciadorStrategy;
import com.atividadeavaliativa.employeemanagement.model.Funcionario;
import com.atividadeavaliativa.employeemanagement.model.HistoricoCalculo;
import com.atividadeavaliativa.employeemanagement.model.bonus.TipoBonus;
import com.atividadeavaliativa.employeemanagement.model.collections.FuncionarioCollection;
import com.atividadeavaliativa.employeemanagement.utils.FormatarData;
import com.atividadeavaliativa.employeemanagement.view.CalculaSalarioView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class CalculaSalarioPresenter {

    private static CalculaSalarioPresenter instence = null;
    private final CalculaSalarioView view;
    private DefaultTableModel tbWeatherDatas;
    private List<HistoricoCalculo> historico;
    private GerenciadorStrategy log;

    private CalculaSalarioPresenter(GerenciadorStrategy log) {
        view = new CalculaSalarioView();
        view.setSize(640, 290);
        view.setLocation(0, 230);
        tableInit();
        initListeners();
        clearTable();
        this.log = log;
        historico = new ArrayList<>();
    }

    public static CalculaSalarioPresenter getInstance(GerenciadorStrategy log) {
        if (instence == null) {
            instence = new CalculaSalarioPresenter(log);
        }
        return instence;
    }

    public CalculaSalarioView getView() {
        return view;
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
                new String[]{"Funcion??rio", "Data", "Sal??rio Base (R$)", "B??nus (R$)", "Sal??rio (R$)"});
        view.getTblFuncionarios().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbWeatherDatas.setNumRows(0);
        view.getTblFuncionarios().setModel(tbWeatherDatas);
    }

    public void realizarCalculo(List<Funcionario> funcionarios) throws Exception {
        double somaBonusFuncionario = 0.0;
        if (funcionarios != null) {
            for (Funcionario funcionario : funcionarios) {
                List<TipoBonus> listBonus = funcionario.getBonusCumulativo();
                if (listBonus != null) {
                    for (TipoBonus bonus : listBonus) {
                        somaBonusFuncionario += bonus.calculaValorBonus(funcionario);
                    }
                }

                String nomeFuncionario = funcionario.getNome();
                Date dataCalculo = view.getDcDataCalculo().getDate();
                double salarioBase = funcionario.getSalarioBase();
                double somaBonus = somaBonusFuncionario;
                double salario = funcionario.getSalarioBase() + somaBonusFuncionario;

                tbWeatherDatas.addRow(new Object[]{
                    nomeFuncionario,
                    FormatarData.parseDateToString(dataCalculo),
                    salarioBase,
                    somaBonus,
                    salario
                });

                historico.add(new HistoricoCalculo(nomeFuncionario, dataCalculo, salarioBase, somaBonus, salario));                
                funcionario.setSalario(funcionario.getSalarioBase() + somaBonusFuncionario);
            }
        }
        this.getGerenciadorLog().getLog().whiteCalculoRealizado(funcionarios);
    }

    public void listarTodos(List<HistoricoCalculo> historico) throws Exception {
        if (!historico.isEmpty()) {
            clearTable();
            for (HistoricoCalculo calculo : historico) {
                tbWeatherDatas.addRow(new Object[]{
                    calculo.getNomeFuncionario(),
                    FormatarData.parseDateToString(calculo.getDataCalculo()),
                    calculo.getSalarioBase(),
                    calculo.getSomaBonus(),
                    calculo.getSalario()
                });
            }
        } else {
            throw new Exception("Historico de calculos realizados est?? vazio!");
        }
    }

    public void listarPorData(List<HistoricoCalculo> historico) throws Exception {
        if (historico != null) {
            clearTable();
            String dataBusca = FormatarData.parseDateToString(view.getDcDDataBusca().getDate());

            for (HistoricoCalculo calculo : historico) {
                String dataCalculo = FormatarData.parseDateToString(calculo.getDataCalculo());
                if (dataBusca.equals(dataCalculo)) {
                    tbWeatherDatas.addRow(new Object[]{
                        calculo.getNomeFuncionario(),
                        FormatarData.parseDateToString(calculo.getDataCalculo()),
                        calculo.getSalarioBase(),
                        calculo.getSomaBonus(),
                        calculo.getSalario()
                    });
                }
            }
        }
    }

    private void initListeners() {

        view.getBtListarTodos().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    listarTodos(historico);
                } catch (Exception ex) {  
                    JOptionPane.showMessageDialog(view, "Error: " + ex.getMessage());
                    gerarlogException(ex.getMessage());
                }
            }
        });

        view.getBtFechar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.setVisible(false);
            }
        });

        view.getBtCalcular().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    realizarCalculo(FuncionarioCollection.getInstance().getFuncionarios());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, "Error: " + ex.getMessage());
                    gerarlogException(ex.getMessage());
                }
            }
        });

        view.getBtBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    listarPorData(historico);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, "Error: " + ex.getMessage());
                    gerarlogException(ex.getMessage());
                }
            }
        });

    }
          
    public GerenciadorStrategy getGerenciadorLog() {
        return log;
    }
    
    private void gerarlogException(String falha){
        this.log.getLog().writeFalha(falha);
    }

}
