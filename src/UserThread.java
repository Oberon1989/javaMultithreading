import java.util.HashMap;
import java.util.Map;

public class UserThread extends Thread{
    private char checkChar;
    private float percent;
    private HashMap<Character,Float> map;
    String data;

    public UserThread(char checkChar,String data,HashMap<Character,Float> map)
    {
        super();
        this.checkChar=checkChar;
        this.data=data;
        this.map=map;

    }
    @Override
    public void run() {
       counting();
    }

    private void counting()
    {
        int length =data.length();
        int count=0;
        for (int i = 0; i < length; i++) {
            if(Character.toLowerCase(data.charAt(i))==Character.toLowerCase(checkChar))
            {
                count++;
            }
        }
       float resul=((float) count/length)*100;
        map.put(new Character(checkChar),resul);
    }
}
