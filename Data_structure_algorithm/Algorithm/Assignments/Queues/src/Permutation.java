import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> client = new RandomizedQueue<>();
        for(int i=1;i<args.length;i++){
            client.enqueue(args[i]);
        }
        int displayTime = 0;
        for(String item : client){
            if(displayTime<k){
                StdOut.println(item);
            }
            displayTime++;
        }
    }
}
