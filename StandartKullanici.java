
import java.util.Scanner;

public class StandartKullanici extends Kullanici{

    public StandartKullanici(String adSoyad) {
        super(adSoyad);
    }

    @Override
    public void paylasimYap() { //paylaşım yap methodu abstract olduğu için burada ovveride ediliyor
        Scanner scanner = new Scanner(System.in);
        System.out.print("Paylaşım Başlığı: ");
        String baslik = scanner.nextLine();
        while(baslik.length() > 30) { //kullanıcı standart olduğu için başlık ve içerik için karakter limitlerini aşarsa uyarılıyor.
            System.out.println("Başlık 30 karakterden uzun olamaz!");
            System.out.print("Paylaşım Başlığı: ");
            baslik = scanner.nextLine();
        }
        
        System.out.print("Paylaşım İçeriği: ");
        String icerik = scanner.nextLine();
        while(icerik.length() > 240) {
            System.out.println("İçerik 240 karakterden uzun olamaz!");
            System.out.print("Paylaşım İçeriği: ");
            icerik = scanner.nextLine();
        }
        
        Paylasim paylasim = new Paylasim(baslik, icerik, this);
        
    }
}
