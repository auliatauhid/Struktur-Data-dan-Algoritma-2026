import java.util.Scanner;
import java.util.Stack;

public class CekStackUTS {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();

        System.out.print("Masukkan jumlah elemen Stack 1 : ");
        int n1 = sc.nextInt();
        System.out.println("Masukkan elemen Stack 1 :");
        for (int i = 0; i < n1; i++) s1.push(sc.nextInt());

        System.out.print("Masukkan jumlah elemen Stack 2 : ");
        int n2 = sc.nextInt();
        System.out.println("Masukkan elemen Stack 2 :");
        for (int i = 0; i < n2; i++) s2.push(sc.nextInt());

        System.out.println("\n==============================");
        System.out.println("       ISI KEDUA STACK");
        System.out.println("==============================");
        System.out.println("Stack 1 : " + s1 + "  <-- TOP: " + (s1.isEmpty() ? "kosong" : s1.peek()));
        System.out.println("Stack 2 : " + s2 + "  <-- TOP: " + (s2.isEmpty() ? "kosong" : s2.peek()));
        System.out.println("==============================");
        System.out.println("\nHasil   : Kedua stack " + (s1.equals(s2) ? "SAMA" : "TIDAK SAMA"));
        System.out.println("==============================");

        sc.close();
    }
}