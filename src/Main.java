import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws IOException {

        String[] sentenceLine;
        int avgCount = 0;
        int sentenceCount = 0;
        int[] threadValue;

        System.out.println("Please enter thread number: ");
        Scanner scannerThreadCount = new Scanner(System.in);

        int threadCount = scannerThreadCount.nextInt();
        threadValue = new int[threadCount];

        Scanner sentence = new Scanner(new File("rw.txt"));
        ArrayList<String> sentenceList = new ArrayList<String>();
        ArrayList<String> sentenceThread = new ArrayList<String>();

        while (sentence.hasNextLine()) {
            sentenceList.add(sentence.nextLine());
        }

        sentence.close();

        String[] sentenceArray = sentenceList.toArray(new String[sentenceList.size()]);

        for (int r = 0; r < sentenceArray.length; r++) {
            sentenceLine = sentenceArray[r].split("[\\.|!|\\?|:] ");

                for (int i = 0; i < sentenceLine.length; i++) {
                    sentenceCount = sentenceLine.length;
                    System.out.println("Sentence " + (i + 1) + ": " + sentenceLine[i]);
                    String[] words = sentenceLine[i].split(" ");
                    for (String wordsForList : words) {

                        Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
                        Matcher m = p.matcher(wordsForList);
                        boolean b = m.find();

                        if (b){
                            StringBuffer sb= new StringBuffer(wordsForList);
                            sb.deleteCharAt(sb.length()-1);
                            wordsForList = String.valueOf(sb);
                        }
                        avgCount++;
                        sentenceThread.add(wordsForList);
                    }

                    //create Thread

                    ProcessThread processThread = new ProcessThread(i, sentenceThread);
                    threadValue[i] = i + 1;
                    Thread myThread = new Thread(processThread);
                    myThread.run();
                }
            System.out.println("Avg. Word Count : " + avgCount/sentenceCount);
            System.out.println("Sentence Count: " + sentenceCount);
            for (int x = 0; x < threadValue.length; x++){
                System.out.println("ThreadId=" + (x+1) + " " + "Count=" +threadValue[x]);
            }

        }


    }

}
