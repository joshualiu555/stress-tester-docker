import java.util.Random;

public class Generator {
    public static void main(String[] args) {
        Random random = new Random();
        int n = random.nextInt(10) + 1;
        System.out.println(n);
        for (int i = 0; i < n; i++) {
            int num = random.nextInt(5) + 1;
            System.out.print(num + " ");
        }
    }
}
