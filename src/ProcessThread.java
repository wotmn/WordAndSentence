import java.util.ArrayList;

public class ProcessThread implements Runnable{

    private int threadNumber;
    ArrayList<String> words = new ArrayList<>();
    ArrayList<Integer> count = new ArrayList<>();
    ArrayList<String> sentenceLine;

    public ProcessThread(int threadNumber, ArrayList<String> sentenceLine){
        this.threadNumber = threadNumber;
        this.sentenceLine = sentenceLine;
    }

    @Override
    public void run() {

            try {
                for (String sentenceWord : sentenceLine){
                    if (words.contains(sentenceWord)){
                        int index = words.indexOf(sentenceWord);
                        count.set(index, count.get(index) + 1);
                    }else{
                        words.add(sentenceWord);
                        count.add(1);
                    }
                }

                for (int i = 0; i < words.size(); i++){
                    System.out.println(words.get(i) + " " + count.get(i));
                }

                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }
}
