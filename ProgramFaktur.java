import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

// Parent class Barang
class Barang {
    protected String kodeBarang;
    protected String namaBarang;
    protected double hargaBarang;

    // Constructor Barang
    public Barang(String kodeBarang, String namaBarang, double hargaBarang) {
        this.kodeBarang = kodeBarang;
        this.namaBarang = namaBarang;
        this.hargaBarang = hargaBarang;
    }
}

// Subclass Faktur
class Faktur extends Barang {
    private String noFaktur;
    private int jumlahBeli;

    // Constructor Faktur
    public Faktur(String noFaktur, String kodeBarang, String namaBarang, double hargaBarang, int jumlahBeli) {
        super(kodeBarang, namaBarang, hargaBarang);
        this.noFaktur = noFaktur;
        this.jumlahBeli = jumlahBeli;
    }

    // Method untuk menghitung total harga
    public double hitungTotal() {
        return hargaBarang * jumlahBeli;
    }

    // Method untuk menampilkan detail faktur
    public void tampilkanFaktur(String namaKasir) {
        // Mendapatkan waktu dan tanggal sekarang
        String currentDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());

        System.out.println("\n+----------------------------------------------------+");
        System.out.println("Selamat Datang di Supermarket Minang Midi");
        System.out.println("Tanggal dan Waktu : " + currentDate);
        System.out.println("+----------------------------------------------------+");
        System.out.println("No. Faktur   : " + noFaktur);
        System.out.println("Kode Barang  : " + kodeBarang);
        System.out.println("Nama Barang  : " + namaBarang);
        System.out.println("Harga Barang : Rp " + hargaBarang);
        System.out.println("Jumlah Beli  : " + jumlahBeli);
        System.out.println("TOTAL        : Rp " + hitungTotal());
        System.out.println("+----------------------------------------------------+");
        System.out.println("Kasir        : " + namaKasir);
        System.out.println("+----------------------------------------------------+");
    }
}

// Main class
public class ProgramFaktur {
    // Method untuk validasi input String
    public static void validateInput(String input, String fieldName) throws InvalidInputException {
        if (input == null || input.trim().isEmpty()) {
            throw new InvalidInputException(fieldName + " tidak boleh kosong.");
        }
    }

    // Method untuk validasi input angka
    public static void validateInput(double input, String fieldName) throws InvalidInputException {
        if (input <= 0) {
            throw new InvalidInputException(fieldName + " harus lebih dari 0.");
        }
    }

    // Method untuk validasi input angka (integer)
    public static void validateInput(int input, String fieldName) throws InvalidInputException {
        if (input <= 0) {
            throw new InvalidInputException(fieldName + " harus lebih dari 0.");
        }
    }

    // Method untuk membuat captcha
    public static String generateCaptcha() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder captcha = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            int randomIndex = (int) (Math.random() * chars.length());
            captcha.append(chars.charAt(randomIndex));
        }
        return captcha.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // Login Process
            String correctUsername = "hanezy";
            String correctPassword = "han6839";
            boolean isLoggedIn = false;

            while (!isLoggedIn) {
                System.out.println("\n+-----------------------------------------------------+");
                System.out.println("Log in");
                System.out.print("Username : ");
                String username = scanner.nextLine();
                System.out.print("Password : ");
                String password = scanner.nextLine();

                // Generate and validate captcha
                String captcha = generateCaptcha();
                System.out.println("Captcha : " + captcha);
                System.out.print("Masukkan Captcha : ");
                String userCaptcha = scanner.nextLine();

                if (username.equals(correctUsername) && password.equals(correctPassword) && userCaptcha.equals(captcha)) {
                    System.out.println("Login berhasil!");
                    isLoggedIn = true;
                } else {
                    System.out.println("Login gagal. Silakan coba lagi.");
                }
            }

            // Setelah login berhasil, lanjutkan ke input data barang
            System.out.println("\nSelamat Datang di Sistem Faktur Supermarket Minang Midi!");
            System.out.print("Masukkan No Faktur: ");
            String noFaktur = scanner.nextLine();
            validateInput(noFaktur, "No Faktur");

            System.out.print("Masukkan Kode Barang: ");
            String kodeBarang = scanner.nextLine();
            validateInput(kodeBarang, "Kode Barang");

            System.out.print("Masukkan Nama Barang: ");
            String namaBarang = scanner.nextLine();
            validateInput(namaBarang, "Nama Barang");

            System.out.print("Masukkan Harga Barang: ");
            double hargaBarang = scanner.nextDouble();
            validateInput(hargaBarang, "Harga Barang");

            System.out.print("Masukkan Jumlah Beli: ");
            int jumlahBeli = scanner.nextInt();
            validateInput(jumlahBeli, "Jumlah Beli");

            scanner.nextLine(); // Membersihkan buffer scanner
            System.out.print("Nama Kasir: ");
            String namaKasir = scanner.nextLine();
            validateInput(namaKasir, "Nama Kasir");

            // Buat dan tampilkan faktur
            Faktur faktur = new Faktur(noFaktur, kodeBarang, namaBarang, hargaBarang, jumlahBeli);
            faktur.tampilkanFaktur(namaKasir);

        } catch (InvalidInputException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}

// Custom Exception
class InvalidInputException extends Exception {
    public InvalidInputException(String message) {
        super(message);
    }
}
