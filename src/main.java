import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Nojpg on 17.09.17.
 */
public class main {
    private static final String path = "/home/sovereign/Sherban/";
    static LinkedList<String> listsDoubleToFile = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        LinkedList<LinkedList> lists = new LinkedList<>();
        String listStr[] = new File(
                path)
                .list((dir, namelink) -> namelink.endsWith(".txt"));
        assert listStr != null;
        Arrays.stream(listStr).map(s -> readData(path + s)).forEach(lists::add);
        summation(lists);
    }

    private static LinkedList<Double> readData (String title){
        LinkedList<Double> doubleLinkedList = new LinkedList<>();
        try{
            FileInputStream fstream = new FileInputStream(title);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;
            while ((strLine = br.readLine()) != null){
                String[] splitStr = strLine.split(" ");
                for (String aSplitStr : splitStr) {
                    doubleLinkedList.add(Double.parseDouble(aSplitStr.replace(',', '.')));
                }
            }
        }catch (IOException e){
            System.out.println("Error");
        }
        return doubleLinkedList;
    }

    private static void summation(LinkedList<LinkedList> lists) throws IOException{
        double sum = 0;
        for (int i = 0; i < lists.get(0).size(); i++) {
            for (int j = 0; j < lists.size(); j++) {
                sum += (Double.parseDouble(lists.get(j).get(i).toString()));
            }
            listsDoubleToFile.add(String.valueOf(sum/lists.size()));
            sum = 0;
        }
        test(listsDoubleToFile);
    }

    private static void test(List<String> testList) throws IOException {
        FileWriter fileWriter = new FileWriter("/home/sovereign/Public/testwrite1.txt");
        int i = 1;
                for(String num: listsDoubleToFile){
                    if ((listsDoubleToFile.size()+i)%8 == 0 &&
                            !(listsDoubleToFile.get(i-1).equals(System.lineSeparator())) &&
                            !(listsDoubleToFile.get(i-1).equalsIgnoreCase(" "))){
                        fileWriter.write(System.lineSeparator());
                    } else if (!num.equalsIgnoreCase(" ") &&
                            !num.equalsIgnoreCase(System.lineSeparator())){
                        fileWriter.write(num + " ");
                    } else{
                        fileWriter.write(num);
                    }
                    i++;
        }
        fileWriter.close();
        System.out.println("closed");
    }
}
