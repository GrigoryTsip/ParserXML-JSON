package ru.netology;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Main {

    public static String[] colomnMapping = {"id", "firstName", "lastName", "country", "age"};
    public static String fileName = "data.xml";

    public static void main(String[] args) throws IOException, RuntimeException, ParserConfigurationException, SAXException {

       List<Employee> staff = parseXML(colomnMapping, fileName);

        System.out.println("Из XML-файла data.xml создано экземпляров класса Employee: " + staff.size()
                + "\n\nЭкземпляры класса:");
        staff.forEach(System.out::println);

        // Объекты класса Employee конвертируем в формат JSON и записываем в файл data.json
        String json = listToJson(staff);
        writeString(json, "data2.json");

        System.out.println("\nВсе объекты класса Employee конвертированы в объекты JSON " +
                "\nв файле data2.json, расположенном в корневой папке проекта");
    }

    public static List<Employee> parseXML(String[] colomn, String fileName) throws ParserConfigurationException, IOException, SAXException {

        List<Employee> staff = new ArrayList<>();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new File(fileName));

        // Получение списка всех элементов employee внутри корневого элемента
        NodeList employee = doc.getDocumentElement().getElementsByTagName("employee");

        for (int i = 0; i < employee.getLength(); i++) {

            Node node = employee.item(i);
            if (Node.ELEMENT_NODE == node.getNodeType()) {

                Element emp = (Element) node;
                String[] attr = new String[colomn.length];

                for (int j = 0; j < colomn.length; j++) {
                    attr[j] = emp.getAttribute(colomn[j]);
                }
                Employee stf = new Employee(attr);
                staff.add(stf);
            }
        }
        return staff;
    }


    public static String listToJson(List<Employee> list) {

        GsonBuilder bldr = new GsonBuilder();
        Gson gson = bldr
                .setPrettyPrinting()
                .create();
        return gson.toJson(list);
    }

    public static void writeString(String json, String fileName) {

        try (FileWriter file = new FileWriter(fileName)) {

            file.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}