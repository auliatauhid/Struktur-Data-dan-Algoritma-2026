import java.util.Scanner;

/**
 * Implementasi Hash Table dari awal (tanpa menggunakan HashMap bawaan Java)
 * - Ukuran awal array   : 10
 * - Fungsi hash         : metode pembagian (modulo)
 * - Resolusi tabrakan   : Separate Chaining menggunakan Linked List
 *
 * Operasi yang tersedia:
 *   - insert(key, value)
 *   - search(key)
 *   - remove(key)
 *   - display()
 *
 * Program ini juga menyediakan MENU INTERAKTIF (lihat method main)
 * sehingga pengguna bisa memasukkan key/value sendiri lewat keyboard
 * untuk menjalankan test case secara manual.
 */
public class HashTable {

    // ---------- Node untuk Linked List (chaining) ----------
    private static class Node {
        int key;
        String value;
        Node next;

        Node(int key, String value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    // ---------- Atribut Hash Table ----------
    private Node[] table;   // array of linked list (chain)
    private int capacity;   // ukuran array
    private int size;       // jumlah elemen yang tersimpan

    // ---------- Konstruktor ----------
    public HashTable() {
        this.capacity = 10;              // ukuran awal array = 10
        this.table = new Node[capacity];
        this.size = 0;
    }

    // ---------- Fungsi Hash (metode pembagian / modulo) ----------
    private int hashFunction(int key) {
        // Math.abs untuk berjaga-jaga jika key negatif
        return Math.abs(key) % capacity;
    }

    // ---------- a. insert(key, value) ----------
    public void insert(int key, String value) {
        int index = hashFunction(key);
        Node head = table[index];

        // Jika key sudah ada, update value-nya (tidak membuat duplikat)
        Node current = head;
        while (current != null) {
            if (current.key == key) {
                current.value = value;
                System.out.println("Key " + key + " sudah ada, value diperbarui menjadi \"" + value + "\" pada indeks " + index);
                return;
            }
            current = current.next;
        }

        // Buat node baru dan sisipkan di AKHIR linked list (chaining)
        Node newNode = new Node(key, value);

        if (head == null) {
            table[index] = newNode;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }

        size++;
        System.out.println("Insert -> key: " + key + ", value: \"" + value + "\" ke indeks " + index);
    }

    // ---------- b. search(key) ----------
    public String search(int key) {
        int index = hashFunction(key);
        Node current = table[index];

        while (current != null) {
            if (current.key == key) {
                return current.value;
            }
            current = current.next;
        }
        return "Tidak ditemukan";
    }

    // ---------- c. remove(key) ----------
    public boolean remove(int key) {
        int index = hashFunction(key);
        Node current = table[index];
        Node previous = null;

        while (current != null) {
            if (current.key == key) {
                if (previous == null) {
                    // Node yang dihapus adalah head dari list
                    table[index] = current.next;
                } else {
                    previous.next = current.next;
                }
                size--;
                System.out.println("Key " + key + " berhasil dihapus dari indeks " + index);
                return true;
            }
            previous = current;
            current = current.next;
        }

        System.out.println("Key " + key + " tidak ditemukan, tidak ada yang dihapus");
        return false;
    }

    // ---------- d. display() ----------
    public void display() {
        System.out.println("\n===== Isi Hash Table (kapasitas = " + capacity + ", jumlah elemen = " + size + ") =====");
        for (int i = 0; i < capacity; i++) {
            System.out.print("Indeks " + i + ": ");
            Node current = table[i];

            if (current == null) {
                System.out.println("kosong");
            } else {
                StringBuilder sb = new StringBuilder();
                while (current != null) {
                    sb.append("[").append(current.key).append(" -> ").append(current.value).append("]");
                    if (current.next != null) {
                        sb.append(" -> ");
                    }
                    current = current.next;
                }
                System.out.println(sb.toString());
            }
        }
        System.out.println("=================================================\n");
    }

    // ---------- Getter tambahan (opsional, berguna untuk verifikasi test) ----------
    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return capacity;
    }

    // ---------- Program Utama: MENU INTERAKTIF ----------
    public static void main(String[] args) {
        HashTable ht = new HashTable();
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        System.out.println("=====================================================");
        System.out.println(" PROGRAM HASH TABLE (Separate Chaining) - MODE MANUAL ");
        System.out.println("=====================================================");

        while (running) {
            System.out.println("\nPilih operasi yang ingin diuji:");
            System.out.println("1. Insert (key, value)");
            System.out.println("2. Search (key)");
            System.out.println("3. Remove (key)");
            System.out.println("4. Display seluruh Hash Table");
            System.out.println("5. Tampilkan jumlah elemen & kapasitas");
            System.out.println("0. Keluar");
            System.out.print("Masukkan pilihan: ");

            String pilihanStr = sc.nextLine().trim();
            int pilihan;
            try {
                pilihan = Integer.parseInt(pilihanStr);
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid, masukkan angka menu (0-5).");
                continue;
            }

            switch (pilihan) {
                case 1: {
                    System.out.print("Masukkan key (angka bulat): ");
                    int key = bacaInt(sc);
                    System.out.print("Masukkan value (teks): ");
                    String value = sc.nextLine();
                    ht.insert(key, value);
                    break;
                }
                case 2: {
                    System.out.print("Masukkan key yang dicari: ");
                    int key = bacaInt(sc);
                    String hasil = ht.search(key);
                    System.out.println("Hasil pencarian key " + key + " -> " + hasil);
                    break;
                }
                case 3: {
                    System.out.print("Masukkan key yang ingin dihapus: ");
                    int key = bacaInt(sc);
                    ht.remove(key);
                    break;
                }
                case 4:
                    ht.display();
                    break;
                case 5:
                    System.out.println("Jumlah elemen tersimpan : " + ht.getSize());
                    System.out.println("Kapasitas array         : " + ht.getCapacity());
                    break;
                case 0:
                    running = false;
                    System.out.println("Program selesai. Terima kasih!");
                    break;
                default:
                    System.out.println("Pilihan tidak dikenal, silakan pilih 0-5.");
            }
        }

        sc.close();
    }

    // Helper untuk membaca input integer dengan validasi sederhana
    private static int bacaInt(Scanner sc) {
        while (true) {
            String input = sc.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Input harus berupa angka bulat, coba lagi: ");
            }
        }
    }
}