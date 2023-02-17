
import java.util.Scanner;

public class PremiumKullanici extends Kullanici implements IPaylasimDuzenle {
    Scanner scanner = new Scanner(System.in);
    public PremiumKullanici(String adSoyad) {
        super(adSoyad);
    }

    @Override
    public void paylasimYap() { //Kullanıcı classından aldığı methodu harf limiti olmadan override eder.
        System.out.print("Paylaşım Başlığı: ");
        String baslik = scanner.nextLine();
        System.out.print("Paylaşım İçeriği: ");
        String icerik = scanner.nextLine();
        Paylasim paylasim = new Paylasim(baslik, icerik, this);
    }

    @Override
    public void paylasimDuzenle() { //Sadece premium kullanıcıların yararlanabildiği bu method IPaylasimDuzenle interfaceinden implement ve override edilir.
        System.out.print("Düzenlemek İstediğiniz Paylaşımın ID'si: ");
        String id = scanner.nextLine();
        
        int tercihIndeks = -1; //kullanıcının düzenlemek istediği paylaşımın idsi paylaşım listesinde aranır
        for(int x = 0 ; x < this.getPaylasimListesi().length ; x++) {
            if(this.getPaylasimListesi()[x] != null) {
                if(this.getPaylasimListesi()[x].getPaylasimID() == Integer.parseInt(id)) {
                    tercihIndeks = x;
                    break;
                }
            } 
        }
        
        if(tercihIndeks == -1) { //eğer bu paylaşım bulunmazsa kullanıcı uyarılır
            System.out.println("Bu ID'ye Sahip Bir Paylaşımınız Yok...");
        }
        else {//eğer bulunursa paylaşımın yeni içeriği sorulur ve kullanıcıdan yeni bir değer alınır.
            System.out.print("Paylaşımın Yeni İçeriği: ");
            String yeniIcerik = scanner.nextLine();
            this.getPaylasimListesi()[tercihIndeks].setIcerik(yeniIcerik);
            System.out.println("Paylaşım İçeriği Değiştirildi...");
        }
    }

    
    
}
