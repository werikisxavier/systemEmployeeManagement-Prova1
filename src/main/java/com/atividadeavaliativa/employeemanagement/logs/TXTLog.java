
package com.atividadeavaliativa.employeemanagement.logs;

import com.atividadeavaliativa.employeemanagement.model.Funcionario;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import java.util.List;

public class TXTLog implements ILogStrategy {

    @Override
    public void writeFuncionarioOperacao(String operation, Funcionario funcionario) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("out.txt", true))) {
            bw.write("Funcionario " + funcionario.getNome() + " " + operation);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void whiteBonusConsulta(Funcionario funcionario) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("out.txt", true))) {
            bw.write("Bônus consultado para o funcionário " + funcionario.getNome());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void whiteCalculoRealizado(List<Funcionario> funcionarios) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("out.txt", true))) {
            bw.write("Salário calculado para o(s) funcionário(s): [");
            bw.newLine();
            for (Funcionario funcionario : funcionarios) {
                bw.write(funcionario.getNome());
                bw.newLine();
            }
            bw.write("]");
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeFalha(String falha) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("out.txt", true))) {
            bw.write("Falha: " +falha );      
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
