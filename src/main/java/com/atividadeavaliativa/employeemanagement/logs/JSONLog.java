/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atividadeavaliativa.employeemanagement.logs;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONObject;
//import org.json.simple.JSONObject;
//import util.DateFormat;

/**
 *
 * @author W-E-R
 */
public class JSONLog implements ILog {

    private JSONObject obj;
    private JSONObject data;

    private static ILog instence = null;

    private JSONLog() {
    obj = new JSONObject();
    data = new JSONObject();
    }

    public static ILog getInstance() {
        if (instence == null) {
            instence = new JSONLog();
        }
        return instence;
    }
/**
    @Override
    public void write(String operation, WeatherData weatherdata) {

        data.put("temperatura", weatherdata.getTemperature());
        data.put("humidade", weatherdata.getHumidity());
        data.put("pressao", weatherdata.getPressure());
        data.put("data", DateFormat.parseToString(weatherdata.getDate()));
        obj.put("operacão", operation);
        obj.put("dados", data);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("out.json", true))) {

            bw.write(obj.toJSONString());
            bw.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
*/
}
