import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

public class XMLBoardReader {
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
    public List<Room> readBoardData(Document d) {
        List<Room> rooms = new ArrayList<>();
        // problem : if we add starting room will mess with the data , if not it wouldn't movie as it should be
        //    rooms.add(Room.StartingRoom);

        Element root = d.getDocumentElement();
        NodeList sets = root.getElementsByTagName("set");

        // get rooms
        for (int i = 0; i < sets.getLength(); i++) {
            //System.out.println("Printing information for sets " + (i + 1));
            Element set = (Element) sets.item(i);
            String roomName = set.getAttributes().getNamedItem("name").getNodeValue();
            // area
            Element area = (Element) set.getElementsByTagName("area").item(0);
            String x = area.getAttributes().getNamedItem("x").getNodeValue();
            String y = area.getAttributes().getNamedItem("y").getNodeValue();
            String h = area.getAttributes().getNamedItem("h").getNodeValue();
            String w = area.getAttributes().getNamedItem("w").getNodeValue();

            Room currentRoom = new Room(roomName,Integer.parseInt(x),Integer.parseInt(y),Integer.parseInt(h),Integer.parseInt(w));
            rooms.add(currentRoom);
        }
        var LevelUp = new ArrayList<LevelUp>();
//        var creditLevelUp = new ArrayList<CreditLevelUp>();

        // fill in adjacent rooms
        for (int i = 0; i < sets.getLength(); i++) {
            Element set = (Element) sets.item(i);
            // neighbors
            Element child_Neighbors = (Element) set.getElementsByTagName("neighbors").item(0);
            NodeList neighbors = ((Element) child_Neighbors).getElementsByTagName("neighbor");

            NodeList upgradesList = set.getElementsByTagName("upgrades");
            NodeList partsList = set.getElementsByTagName("parts");
            NodeList takesList = set.getElementsByTagName("takes");

            var currentRoom = rooms.get(i);
            var roomParts = new ArrayList<Part>(); // this should be like class Part
            var adjacentRooms = new ArrayList<Room>();
            var takersArrayList = new ArrayList<Takers>();


          //  var takeRooms = new ArrayList<Room>();
            if (takesList.getLength() > 0){
                NodeList takes = ((Element) takesList.item(0)).getElementsByTagName("take");
                for (int z = 0; z < takes.getLength(); z++) {
                    Node sub_takes = takes.item(z);
                    if("take".equals(sub_takes.getNodeName())){
                        String number = sub_takes.getAttributes().getNamedItem("number").getNodeValue();

                        Element subElement = (Element) sub_takes;
                        NodeList areaList =  subElement.getElementsByTagName("area");
                        Node sub_area = areaList.item(0);

                        String x = sub_area.getAttributes().getNamedItem("x").getNodeValue();
                        String y = sub_area.getAttributes().getNamedItem("y").getNodeValue();
                        String h = sub_area.getAttributes().getNamedItem("h").getNodeValue();
                        String w = sub_area.getAttributes().getNamedItem("w").getNodeValue();

                       Takers takers = new Takers(
                               Integer.parseInt(number),
                                Integer.parseInt(x),
                                Integer.parseInt(y),
                                Integer.parseInt(w),
                                Integer.parseInt(h)
                                );
                        takersArrayList.add(takers);


                    }

                }
                //here goes for the taker for current room
                currentRoom.setTakers(takersArrayList);
            }


            if (partsList.getLength() > 0) {
                NodeList parts = ((Element) partsList.item(0)).getElementsByTagName("part");

                //  System.out.println(" Parts Count" + parts.getLength());
                for (int z = 0; z < parts.getLength(); z++) {
                    // you need a for loop here
                    Node sub_parts = parts.item(z);
                    if ("part".equals(sub_parts.getNodeName())) {
                        String name = sub_parts.getAttributes().getNamedItem("name").getNodeValue();
                        String level = sub_parts.getAttributes().getNamedItem("level").getNodeValue();

                        // grab the area element
                        // then grab the attributes, x, y, h and w
                         Element subElement = (Element) sub_parts;
                         NodeList areaList =  subElement.getElementsByTagName("area");
                         Node sub_area = areaList.item(0);

                         String x = sub_area.getAttributes().getNamedItem("x").getNodeValue();
                         String y = sub_area.getAttributes().getNamedItem("y").getNodeValue();
                         String h = sub_area.getAttributes().getNamedItem("h").getNodeValue();
                         String w = sub_area.getAttributes().getNamedItem("w").getNodeValue();
                        NodeList line =  subElement.getElementsByTagName("line");
                        Node sub_line = line.item(0);
                        String getLine = sub_line.getTextContent();

                        // then grab the line element
                        // and grab the child node (which is the text)
                        //System.out.println(getLine);

                      // this is the part for each off card work area
                           Part installParts = new Part(
                                   name,
                                   Integer.parseInt(level),
                                   Integer.parseInt(x),
                                   Integer.parseInt(y),
                                   Integer.parseInt(w),
                                   Integer.parseInt(h),
                                   getLine);
                          roomParts.add(installParts);
                    }
                }
                currentRoom.setPartRoom(roomParts);

            }

            if(neighbors.getLength()>0) {
                for (int j = 0; j < neighbors.getLength(); j++) {
                    Node sub_neighbors = neighbors.item(j);

                    if ("neighbor".equals(sub_neighbors.getNodeName())) {
                        String neighborName = sub_neighbors.getAttributes().getNamedItem("name").getNodeValue();

                        // find this in the list of rooms
                        for (int k = 0; k < rooms.size(); k++) {
                            Room possibleNeighbor = rooms.get(k);
                            if (possibleNeighbor.getName().equals(neighborName)) {
                                //  System.out.println(" found neighbor = " + possibleNeighbor.getName());
                                // put this room in the adjacent
                                adjacentRooms.add(possibleNeighbor);
                                break;
                            }
                        }
                    }
                    currentRoom.setAdjacentRoom(adjacentRooms);
                }
            }

            if(upgradesList.getLength()>0) {
                NodeList upgrade = ((Element) upgradesList.item(0)).getElementsByTagName("upgrade");
                for (int k = 0; k < upgrade.getLength(); k++) {
                    Node sub_upGrades = upgrade.item(k);
                        String level = sub_upGrades.getAttributes().getNamedItem("level").getNodeValue();
                        String currency = sub_upGrades.getAttributes().getNamedItem("currency").getNodeValue();
                        String cost = sub_upGrades.getAttributes().getNamedItem("amt").getNodeValue();
                        System.out.println(level + currency+cost);
                        LevelUpCurrency currencyEnum = LevelUpCurrency.Dollar;
                        if(currency.equals("credit")){
                            currencyEnum = LevelUpCurrency.Credit;

                        }
                    LevelUp levelUpDollar = new  LevelUp(Integer.parseInt(level),Integer.parseInt(cost),currencyEnum);
                    LevelUp.add(levelUpDollar);
                }
                currentRoom.setLevelUpsList(LevelUp);
            }

        }

        return rooms;
    }
}

