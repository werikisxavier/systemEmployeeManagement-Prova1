package com.atividadeavaliativa.employeemanagement.logs;




public class XMLLog implements ILog {
/**
    private static Log instence = null;

    private XMLLog() {
    }
 
    public static ILog getInstance() {
        if (instence == null) {
            instence = new XMLLog();
        }
        return instence;
    }

    @Override
    public void write(String operation, WeatherData weatherdata) {

        try {

            Document document = createnodes(operation, weatherdata);

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

        } catch (ParserConfigurationException | TransformerException | IOException ex) {
            Logger.getLogger(XMLLog.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private Document createnodes(String operation, WeatherData weatherdata) throws ParserConfigurationException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

        Document document = documentBuilder.newDocument();
        Element element = document.createElement(operation);
        document.appendChild(element);

        Node weather = document.getFirstChild();
        //CRIANDO NODE
        Element dados = document.createElement("Dados");
        weather.appendChild(dados);

        //TEMPERARATURA
        Attr temperature = document.createAttribute("Temperatura");
        temperature.setValue(String.valueOf(weatherdata.getTemperature()));
        dados.setAttributeNode(temperature);

        //HUMIDADE
        Attr humidity = document.createAttribute("Humidade");
        humidity.setValue(String.valueOf(weatherdata.getHumidity()));
        dados.setAttributeNode(humidity);

        //PRESSAO
        Attr pressure = document.createAttribute("Pressão");
        pressure.setValue(String.valueOf(weatherdata.getPressure()));
        dados.setAttributeNode(pressure);

        //DATA
        Attr date = document.createAttribute("Data");
        date.setValue(DateFormat.parseDateToString(weatherdata.getDate()));
        dados.setAttributeNode(date);

        return document;
    }
*/
}
