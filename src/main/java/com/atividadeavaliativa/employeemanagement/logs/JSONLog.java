package com.atividadeavaliativa.employeemanagement.logs;

import com.atividadeavaliativa.employeemanagement.model.Funcionario;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;


public class JSONLog implements ILogStrategy {

    private JSONObject obj;
    private JSONObject data;

    public JSONLog() {
        resetObj();
    }

    @Override
    public void writeFuncionarioOperacao(String operation, Funcionario funcionario) {
        resetObj();
        obj.put("funcionario", funcionario.getNome());
        obj.put("operação", operation);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("out.json", true))) {

            bw.write(obj.toString()); //toJSONString()
            bw.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void whiteBonusConsulta(Funcionario funcionario) {
        resetObj();
        obj.put("bonus consultado para o funcionario", funcionario.getNome());
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("out.json", true))) {

            bw.write(obj.toString());
            bw.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void whiteCalculoRealizado(List<Funcionario> funcionarios) {
        resetObj();
        JSONArray array = new JSONArray();
        for (Funcionario funcionario : funcionarios) {
            array.put(funcionario.getNome());
        }

        obj.put("salario calculado para o(s) funcionário(s)", array);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("out.json", true))) {

            bw.write(obj.toString());
            bw.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void resetObj() {
        obj = new JSONObject();
        data = new JSONObject();
    }
}
