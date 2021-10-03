import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {


    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        String data = "";
        HashMap<Character, Float> map = setMap();

        Scanner scanner = new Scanner(new FileReader("textData.txt"));
        while (scanner.hasNextLine()) {
            data += scanner.nextLine();
        }
        long time = System.nanoTime();
        ExecutorService es = Executors.newCachedThreadPool();
        String finalData = data;
        map.forEach((k, v)->{es.execute(new UserThread(k, finalData,map));});
        es.shutdown();
        es.awaitTermination(2, TimeUnit.SECONDS);
        time = System.nanoTime() - time;
        System.out.printf("завершено за %.3f ms\n", time/1_000_000.0);
        List keys = new ArrayList(map.keySet());
        Collections.sort(keys);
        keys.forEach(s->{System.out.println(String.format("Буква %s составляет %.1f%s от текста",s,map.get(s),"%"));});




    }

    public static HashMap<Character, Float> setMap() {
        int startValue = 1072;//a
        int length = 32;
        HashMap<Character, Float> result = new HashMap<>();
        char ch = 'ё';
        for (int i = 0; i < length; i++) {

            result.put(new Character((char) (startValue + i)), 0f);
            if (startValue + i == 1077) {
                result.put(new Character(ch), 0f);

            }

        }
        return result;
    }
}
