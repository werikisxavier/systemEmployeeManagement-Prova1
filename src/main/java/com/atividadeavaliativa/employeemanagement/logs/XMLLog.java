package com.atividadeavaliativa.employeemanagement.logs;

import com.atividadeavaliativa.employeemanagement.model.Funcionario;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class XMLLog implements ILogStrategy {

    private Document document;

    public XMLLog() {
    }

    @Override
    public void writeFuncionarioOperacao(String operacao, Funcionario funcionario) {
        gerarLog(operacao, funcionario);
    }

    @Override
    public void whiteBonusConsulta(Funcionario funcionario) {
        gerarLog("BonusConsultado", funcionario);
    }

    @Override
    public void whiteCalculoRealizado(List<Funcionario> funcionarios) {
        gerarLogList(funcionarios);
    }

    @Override
    public void writeFalha(String falha) {
        gerarLogFalha(falha);
    }

    private void gerarLog(String operation, Funcionario funcionario) {
        try {
            document = createnodes(operation, funcionario);

            FileWriter fileWritter = new FileWriter(new File("out.xml"), true);
            BufferedWriter bufferWritter = new BufferedWriter(fileWritter);

            TransformerFactory tFactory = TransformerFactory.newInstance();

            Transformer transformer = tFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            DOMSource source = new DOMSource(document);

            StreamResult result = new StreamResult(bufferWritter);

            transformer.transform(source, result);

            fileWritter.write("\n");

        } catch (ParserConfigurationException | TransformerException | IOException ex) {
            Logger.getLogger(XMLLog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Document createnodes(String operation, Funcionario funcionario) throws ParserConfigurationException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

        Document document = documentBuilder.newDocument();
        Element element = document.createElement(operation);
        document.appendChild(element);

        Node funci = document.getFirstChild();
        //CRIANDO NODE
        Element dados = document.createElement("Funcionario");
        funci.appendChild(dados);

        //NOME
        Attr nome = document.createAttribute("Nome");
        nome.setValue(funcionario.getNome());
        dados.setAttributeNode(nome);
        return document;
    }

    private Document createnodesList(List<Funcionario> funcionarios) throws ParserConfigurationException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

        Document document = documentBuilder.newDocument();
        Element element = document.createElement("SalarioConsultadoList");
        document.appendChild(element);

        for (Funcionario funcionario : funcionarios) {
            Element funcionarioNode = document.createElement("Funcionario");
            funcionarioNode.appendChild(document.createTextNode(funcionario.getNome()));
            element.appendChild(funcionarioNode);
        }

        return document;
    }

    private void gerarLogList(List<Funcionario> funcionarios) {
        try {
            document = createnodesList(funcionarios);

            FileWriter fileWritter = new FileWriter(new File("out.xml"), true);
            BufferedWriter bufferWritter = new BufferedWriter(fileWritter);

            TransformerFactory tFactory = TransformerFactory.newInstance();

            Transformer transformer = tFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            DOMSource source = new DOMSource(document);

            StreamResult result = new StreamResult(bufferWritter);

            transformer.transform(source, result);

            fileWritter.write("\n");

        } catch (ParserConfigurationException | TransformerException | IOException ex) {
            Logger.getLogger(XMLLog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void gerarLogFalha(String falha) {
        try {
            document = createnodesFalha(falha);

            FileWriter fileWritter = new FileWriter(new File("out.xml"), true);
            BufferedWriter bufferWritter = new BufferedWriter(fileWritter);

            TransformerFactory tFactory = TransformerFactory.newInstance();

            Transformer transformer = tFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            DOMSource source = new DOMSource(document);

            StreamResult result = new StreamResult(bufferWritter);

            transformer.transform(source, result);

            fileWritter.write("\n");

        } catch (ParserConfigurationException | TransformerException | IOException ex) {
            Logger.getLogger(XMLLog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Document createnodesFalha(String falha) throws ParserConfigurationException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

        Document document = documentBuilder.newDocument();
        Element element = document.createElement("Falha");
        document.appendChild(element);

        Node funci = document.getFirstChild();
        //CRIANDO NODE
        Element dados = document.createElement("descricao");
        funci.appendChild(dados);

        //NOME
        Attr nome = document.createAttribute("log");
        nome.setValue(falha);
        dados.setAttributeNode(nome);
        return document;
    }

}
