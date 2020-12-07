import javax.xml.parsers.DocumentBuilderFactory;
        import javax.xml.parsers.DocumentBuilder;
        import javax.xml.parsers.ParserConfigurationException;


        import org.w3c.dom.Document;
        import org.w3c.dom.NodeList;
        import org.w3c.dom.Node;
        import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.List;

class XMLCardsReader {



    // building a document from the XML file
    // returns a Document object after loading the book.xml file.
    public Document getDocFromFile(String filename)
            throws ParserConfigurationException {
        {


            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = null;

            try {
                doc = db.parse(filename);
            } catch (Exception ex) {
                System.out.println("XML parse failure");
                ex.printStackTrace();
            }
            return doc;
        } // exception handling

    }

    // reads data from XML file and prints data
    public List <Card> readCardData(Document d) {
        ArrayList <Card> cardList  = new ArrayList<>();

        Element root = d.getDocumentElement();


        NodeList cards = root.getElementsByTagName("card");

        for (int i = 0; i < cards.getLength(); i++) {

        //    System.out.println("card information : " + (i + 1));

            //reads data from the nodes
            Node card = cards.item(i);
            String cardName = card.getAttributes().getNamedItem("name").getNodeValue();
            String cardImg =  card.getAttributes().getNamedItem("img").getNodeValue();
            String cardBudget = card.getAttributes().getNamedItem("budget").getNodeValue();
            //System.out.println("name = " + cardName + "\n" +"img:"+ cardImg+ "\n"+ "budget: "+ cardBudget );
                Card cardData = new Card(cardName,cardImg,Integer.parseInt(cardBudget));
            cardList.add(cardData);
            //reads data

            NodeList children = card.getChildNodes();
            ArrayList<Part> cardPartList = new ArrayList<>();
            for (int j = 0; j < children.getLength(); j++) {

                Node sub = children.item(j);

                if ("scene".equals(sub.getNodeName())) {
                    String number = sub.getAttributes().getNamedItem("number").getNodeValue();
                    //System.out.println(" Scene number = " + number);
                    String title = sub.getTextContent().trim();
                    //System.out.println("Title = " + title);

                } else if ("part".equals(sub.getNodeName())) {
                    String partName = sub.getAttributes().getNamedItem("name").getNodeValue();
                    String partLevel = sub.getAttributes().getNamedItem("level").getNodeValue();
                    int partLevelInt = Integer.parseInt(partLevel);
                   // System.out.println(" PartNme = " + partName +" PartLevel :" + partLevel);
                    Part cardPart = new Part(partName,partLevelInt);
                    cardPartList.add(cardPart);
                    cardData.setCardPart(cardPartList);


                }


            } //for childnodes

         //   System.out.println("\n");

        }//for book nodes
return cardList;
    }// method
}