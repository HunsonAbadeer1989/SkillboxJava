package analyzer;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

import static analyzer.XMLHandler.voters;

public class Loader
{
//    private static SimpleDateFormat birthDayFormat = new SimpleDateFormat("yyyy.MM.dd");
//    private static SimpleDateFormat visitDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
//
//    private static HashMap<Integer, WorkTime> voteStationWorkTimes = new HashMap<>();
//    private static HashMap<String, Integer> voterCounts = new HashMap<>();

    public static void main(String[] args) throws Exception
    {

        String fileName = "res/data-1572M.xml";
        long start = System.currentTimeMillis();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        XMLHandler handler = new XMLHandler();
        parser.parse(new File(fileName), handler);

        voters.forEach((n, b) -> {
            try {
                DBConnection.countVoter(n, b);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        DBConnection.executeMultiInsert();
        System.out.println("Time: " + (System.currentTimeMillis() - start) / 1000 / 60 + " minutes");

//        HomeWork 14.12
//        long memory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
//        System.out.printf("Memory on start: %s kb.\n ", memory/1024);

//        String fileName = "res/data-18M.xml";

//        SAXParserFactory factory = SAXParserFactory.newInstance();
//        SAXParser parser = factory.newSAXParser();
//        analyzer.XMLHandler handler = new analyzer.XMLHandler();
//        parser.parse(new File(fileName), handler);
//        handler.printDuplicatedVoters();
//
//        memory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory() - memory;
//        System.out.printf("Memory spends with SAX: %s kb. \n", memory/1024);
//        memory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

//        parseFile(fileName);
//
//        analyzer.DBConnection.printVoterCounts();

        /**
         * HomeWork 14.12 was commended
         *

        System.out.println("Voting station work times: ");

        for(Integer votingStation : voteStationWorkTimes.keySet())
        {
            analyzer.WorkTime workTime = voteStationWorkTimes.get(votingStation);
            System.out.println("\t" + votingStation + " - " + workTime);
        }

        System.out.println("Duplicated voters: ");
        for(String voter : voterCounts.keySet())
        {
            Integer count = voterCounts.get(voter);
            if(count > 1) {
                System.out.println("\t" + voter + " - " + count);
            }
        }


        memory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory() - memory;
        System.out.printf("Memory spends with DOM: %s kb.\n", memory/1024);
        */
    }

    /**
    private static void parseFile(String fileName) throws Exception
    {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new File(fileName));

        findEqualVoters(doc);
//        fixWorkTimes(doc);
    }

    private static void findEqualVoters(Document doc) throws Exception
    {
        NodeList voters = doc.getElementsByTagName("voter");
        int votersCount = voters.getLength();

        for(int i = 0; i < votersCount; i++)
        {
            Node node = voters.item(i);
            NamedNodeMap attributes = node.getAttributes();

            String name = attributes.getNamedItem("name").getNodeValue();
            String birthDay = attributes.getNamedItem("birthDay").getNodeValue();
//            Date birthDay = birthDayFormat.parse(attributes.getNamedItem("birthDay").getNodeValue());

            DBConnection.countVoter(name, birthDay);
//            analyzer.Voter voter = new analyzer.Voter(name, birthDay);
//            Integer count = voterCounts.get(voter.toString());
//            voterCounts.put(voter.toString(), count == null ? 1 : count + 1);
        }
        DBConnection.executeMultiInsert();
    }

    private static void fixWorkTimes(Document doc) throws Exception
    {
        NodeList visits = doc.getElementsByTagName("visit");
        int visitCount = visits.getLength();
        WorkTime workTime;

        for(int i = 0; i < visitCount; i++)
        {
            Node node = visits.item(i);
            NamedNodeMap attributes = node.getAttributes();

            Integer station = Integer.parseInt(attributes.getNamedItem("station").getNodeValue());
            Date time = visitDateFormat.parse(attributes.getNamedItem("time").getNodeValue());
            workTime = voteStationWorkTimes.get(station);
            if(workTime == null)
            {
                workTime = new WorkTime();
                voteStationWorkTimes.put(station, workTime);
            }
            workTime.addVisitTime(time.getTime());
        }
    }
     */
}