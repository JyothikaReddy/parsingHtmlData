import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.util.List;

public class XMLParser {
    public static void main(String[] args) {
        try {
            File inputFile = new File("data.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            
            doc.getDocumentElement().normalize();
            
            NodeList nList = doc.getElementsByTagName("data");
            
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    
                    List<String> data = List.of("firstname", "lastname", "department", "semester", "rollnum", "section", "course1", "course2", "course3", "course4", "course5", "course6");

                    StringBuilder html = new StringBuilder();
                    html.append("<html>\n<head>\n<link rel=\"stylesheet\" href=\"course_registration.css\">\n</head>\n<body>\n<p><b>Entered details</b></p>\n<table>\n");
                    int count=0;
                    for (String items : data) {
                        if((count%2==0 && count<6)){
                            html.append("<tr>\n");
                        }
                        if(count<6)
                        html.append("<td>").append(items).append("</td>\n");
                        else if(count==9)
                        html.append("<tr>\n<td></td>\n");
                        else if(count==6)
                        html.append("<tr>\n<td>").append("Courses:").append("</td>\n");
                        String value = eElement.getElementsByTagName(items).item(0).getTextContent();
                        html.append("<td>").append(value).append("</td>\n");
                        if(count==8 || count==11 || (count%2==1 && count<7))
                        html.append("</tr>");
                        count++;
                    }
                    html.append("</table>\n</body>\n</html>\n");
                    // Write the HTML code to a file
                    try (PrintWriter writer = new PrintWriter(new FileWriter("output.html"))) {
                        writer.println(html);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

