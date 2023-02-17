
import java.util.Scanner;

public abstract class Kullanici {
    private String adSoyad;
    private int id;
    private Kullanici[] arkadasListesi; //kullanıcının arkadaşlarının içinde tutan bir array
    private Kullanici[] gelenArkadaslikIstekleri; //kullanıcıya gelen arkadaş isteklerinin içinde tutan bir array
    private Paylasim[] paylasimListesi; //kullanıcının paylaşımlarını içinde tutan bir array
    private Chatbox[] chatboxListesi; //kullanıcın arkadaşlarıyla oluşturduğu chatboxları içinde tutan  bir array
    
    private static int idBelirleyici = 0; //Diğer bir çok classta da kullanılan bu static değişken oluşturulan kullanıcının id'si olarak kullanılır.
    private static Kullanici[] kullaniciListesi = new Kullanici[10]; //Oluşturulan her kullanıcı id'si indeksi olmak üzere bu arraye konulur.

    public Kullanici(String adSoyad) {
        this.adSoyad = adSoyad;
        id = idBelirleyici++; //kullanıcın idsi belirlendikten sonra idbelirleyicinin değeri 1 arttırılır bu sayede bir sonraki kullanıcı başka bir id almış olur.
        kullaniciListesi[id] = this;
        paylasimListesi = new Paylasim[30]; //kullanıcı tipi fark etmeksizin h er kullanıcının 30 paylaşım limiti vardır
        
        if(this instanceof PremiumKullanici) { //Kullanıcı tipine göre arraylerin limitleri belirlenir
            arkadasListesi = new Kullanici[9];
            gelenArkadaslikIstekleri = new Kullanici[9];
            chatboxListesi = new Chatbox[9];
        }
        else {
            arkadasListesi = new Kullanici[4];
            gelenArkadaslikIstekleri = new Kullanici[4];
            chatboxListesi = new Chatbox[4];
        }
    }
    
    public boolean istekVarmi() {
        boolean istekVarmi = false;
        
        for(int x = 0 ; x < gelenArkadaslikIstekleri.length ; x++) { //eğer gelenarkadas arrayinin içinde null olmayan bir değer var ise bu döngü kullanıcının arkadaşlık isteği olduğunu söyler
            if(gelenArkadaslikIstekleri[x] != null) {
                istekVarmi = true;
            }
        }
        
        return istekVarmi;
    }//Bu kullanıcı için herhangi bir arkadaşlık isteği olma durumunu döndüdür.
    
    public void arkadasIstegiDegerlendir() {
        Scanner scanner = new Scanner(System.in);
        
        if(istekVarmi()) { //ilk önce kullanıcın bir arkadaşisteği olup olmadığına bakılır
            int arkadasIndeks = -1;
            for(int x = 0 ; x < gelenArkadaslikIstekleri.length ; x++) { //arkadaş isteği var ise döngüde null olmayan ilk değerin indeksi alınır
                if(gelenArkadaslikIstekleri[x] != null) {
                    arkadasIndeks = x;
                }
            }
            
            // gelenArkadaslikIstekleri[arkadasIndeks] => İstek yollayan arkadaş
            System.out.println(gelenArkadaslikIstekleri[arkadasIndeks].getAdSoyad()+" Arkadaşınız Olmak İstiyor"); // bu indeks ile kullanıcıya eklenke istenen arkadaş gösterilir.
            System.out.println("1. Kabut Et     2. Reddet");
            System.out.print("Seçenek: ");
            String secenek = scanner.nextLine();
            
            while(!secenek.equals("1")&&(!secenek.equals("2"))) { //kullanıcının başka bir seçenek girmesine karşı önlem alınır.
                System.out.println("Geçersiz Seçenek...");
                System.out.print("Seçenek: ");
                secenek = scanner.nextLine();
            }
            
            switch(secenek) {
                case "1":
                    if(getArkadasSayisi() == arkadasListesi.length) { //eğer arkadaş sayısı limite ulaşmışsa arkadaşlık isteği silinir
                        System.out.println("Arkadaş Ekleme Limitinize Ulaşmışsınız...");
                        gelenArkadaslikIstekleri[arkadasIndeks] = null;
                        break;
                    }
                    else if(gelenArkadaslikIstekleri[arkadasIndeks].getArkadasSayisi() == gelenArkadaslikIstekleri[arkadasIndeks].arkadasListesi.length) { //eklenen kişi arkadaş limitine ulaşmış ise arkadaşlık isteği silinir
                        System.out.println("Kabul Edilen Kişi Arkadaş Limitine Ulaşmış...");
                        gelenArkadaslikIstekleri[arkadasIndeks] = null;
                        break;
                    }                    
                    else {
                        arkadasEkle(gelenArkadaslikIstekleri[arkadasIndeks]); //eğer iki kullanıcı da arkadaş ekleyebilecek durumda ise arkadaş listelerine bu kullanıcılar eklenir
                        gelenArkadaslikIstekleri[arkadasIndeks].arkadasEkle(Kullanici.this);
                        gelenArkadaslikIstekleri[arkadasIndeks] = null; //bekleyenarkadaşlık istekleri arrayinden bu kişi silinir.
                        System.out.println("İsteği Kabul Ettiniz...");
                        break;
                    }
                case "2":
                    System.out.println("İsteği Reddetiniz...");
                    gelenArkadaslikIstekleri[arkadasIndeks] = null; //istek reddedilirse yine bu kişi listeden silinir.
                    break;     
            }
          
        }
        else { //eğer arkadaş isteği yoksa bu kullanıcıya bildirilir ve methoddan çıkılır.
            System.out.println("Herhangi Bir Arkadaşlık İsteğiniz Yok...");
        }
    }//Gelen Arkadaşlık İsteğini Değerlendirmeyi sağlar
    
    private void arkadasEkle(Kullanici kullanici) { //private olan bu method parametre olarak gelen kullanıcıyı arkadaş listesindeki boş bir yere ekler
        for(int x = 0 ; x < arkadasListesi.length ; x++) {
            if(arkadasListesi[x] == null) {
                arkadasListesi[x] = kullanici;
                break;
            }
        }
    }//Kullanıcıyı Arkadaş Listesine Ekler
    
    public void arkadasIstegiYolla(String adSoyad) {
        int arkadasIndeks = -1;
        for(int x = 0 ; x < Kullanici.getKullaniciListesi().length ; x++) { //adısoyadı girilmiş kullanıcı, kullanıcı listesinde aranır ve indeksi belirlenir
            if(Kullanici.getKullaniciListesi()[x] != null) {
                if(Kullanici.getKullaniciListesi()[x].getAdSoyad().equals(adSoyad)) {
                    arkadasIndeks = x;
                    break;
                }
            }
        }
        
        //Kullanici.getKullaniciListesi()[arkadasIndeks] => Arkadaş eklenmek istenen kullanıcı
        if(arkadasIndeks == -1) { //eğer bu adsoyad kullanıcı listesinde yok ise kullanıcı uyarılır
            System.out.println("Bu İsimde Bir Kullanıcı Yok...");
        }
        else {
            boolean arkadasEkliMi = false; //eğer bu kullanıcı zaten arkadaş listesinde var ise arkadasEkliMi değişkeni true değerini alır
            for(int x = 0 ; x < arkadasListesi.length ; x++) {
                if(arkadasListesi[x] != null) {
                    if(arkadasListesi[x] == Kullanici.getKullaniciListesi()[arkadasIndeks]) {
                        arkadasEkliMi = true;
                    }
                }
            }
            
            boolean bekleyenArkadasListesineVarMi = false;//eğer bu kullanıcıya zaten bir arkadaşlık isteği yollanmış ise değişken true değerini alır
            for(int x = 0 ; x < Kullanici.getKullaniciListesi()[arkadasIndeks].getGelenArkadaslikIstekleri().length ; x++) {
                if(Kullanici.getKullaniciListesi()[arkadasIndeks].getGelenArkadaslikIstekleri()[x] != null) {
                    if(Kullanici.getKullaniciListesi()[arkadasIndeks].gelenArkadaslikIstekleri[x] == this) {
                        bekleyenArkadasListesineVarMi = true;
                    }
                }
            }
            
            if(Kullanici.getKullaniciListesi()[arkadasIndeks] == Kullanici.this) { //eğer eklenmek istenile kullanıcı, kullanıcının kendisiyse kullanıcı uyarılır
                System.out.println("Kendinizle Arkadaş Olamazsınız...");
            }
            else if (bekleyenArkadasListesineVarMi) {//eğer kullanıcıya zaten arkadaşlık isteği yollanmış ise kullancı uyarılır
                System.out.println("Bu Kullanıcıya Zaten Bir Arkadaşlık İsteği Yollamışsınız...");
            }
            else if (arkadasEkliMi) { //arkadaşeklimi değişkeni true değerini almış ise döngü bu bloğa girerek kullanıcıyı uyarır
                System.out.println("Bu Kullanıcı İle Zaten Arkadaşsınız...");
            }
            else if (this.getArkadasSayisi() == this.getArkadasListesi().length) { //arkadaş limitine ulaşılmış ise kullanıcı uyarılır
                System.out.println("Arkadaş Limitinize Zaten Ulaştınız...");
            }
            else if(Kullanici.getKullaniciListesi()[arkadasIndeks].getArkadasSayisi() == Kullanici.getKullaniciListesi()[arkadasIndeks].getArkadasListesi().length) { //eklenen kişi arkadaş limitine ulaşmış ise kullanıcı uyarılır
                System.out.println("Bu Kullanıcı Arkadaş Limitine Ulaşmış...");
            }
            else {
                if(Kullanici.getKullaniciListesi()[arkadasIndeks].getGelenArkadasSayisi() == Kullanici.getKullaniciListesi()[arkadasIndeks].getGelenArkadaslikIstekleri().length) { //bekleyen arkadaşlık istekleri listesi dolu ise kullanıcı uyarılır.
                    System.out.println("Bu Kullanıcının Beklemede Olan Arkadaş İstek Limiti Dolmuştur...");
                }
                else {
                    for(int x = 0 ; x < Kullanici.getKullaniciListesi()[arkadasIndeks].getGelenArkadaslikIstekleri().length ; x++) {
                        if(Kullanici.getKullaniciListesi()[arkadasIndeks].getGelenArkadaslikIstekleri()[x] == null) { 
                            Kullanici.getKullaniciListesi()[arkadasIndeks].getGelenArkadaslikIstekleri()[x] = Kullanici.this; //arkadaşlık isteği gönderilen kullanıcın arrayindeki boş bir yere eklenir
                            System.out.println("Arkadaşlık İsteği Gönderildi...");
                            break;
                        }
                    }
                }
            }
        }
    }//İsmi girilen kullanıcıya arkadaşlık isteği yollar
    
    public void arkadasListele() {
        System.out.println("Arkadaşlarınız: "); 
        for(int x = 0 ; x < arkadasListesi.length ; x++) {
            if(arkadasListesi[x] != null) {
                System.out.print(arkadasListesi[x].adSoyad+", ");
            }
        }
        System.out.println("");
    }//Kullanıcının arkadaşlarını listeler
    
    public void arkadasSil(String adSoyad) {
        int arkadasIndex = -1;
        
        for(int x = 0 ; x < arkadasListesi.length ; x++) { //ad girilen kullanıcının indeksini tespit eder
            if(arkadasListesi[x] != null) {
                if(arkadasListesi[x].adSoyad.equals(adSoyad)) {
                    arkadasIndex = x;
                    break;
                }
            }
        }
        
        if(arkadasIndex == -1) { //bu isimde bir arkadaş yok ise kullanıcı uyarılır
            System.out.println("Böyle Bir Arkadaşınız Yok");
        }
        else { //bu kişi var ise arkadaş listesinden silinir,kullanıcın arkadaş listesinde mevcut kullanıcı da silinir.
            for(int x = 0 ; x < arkadasListesi[arkadasIndex].arkadasListesi.length ; x++) {
                if(arkadasListesi[arkadasIndex].arkadasListesi[x] == Kullanici.this) {
                    arkadasListesi[arkadasIndex].arkadasListesi[x] = null;
                    break;
                }
            }
            
            arkadasListesi[arkadasIndex] = null;
        }
        
    }//Kullanıcıyı arkadaş listesinden siler

    public static void kullaniciListele() {
        for(int x = 0 ; x < kullaniciListesi.length ; x++) {
            if(kullaniciListesi[x] != null) {
                System.out.println(kullaniciListesi[x].id+" "+kullaniciListesi[x].adSoyad);
            }
        }
    } //Tüm kullanıcıları listeler

    public void paylasimEkle(Paylasim paylasim) {
        for(int x = 0 ; x < paylasimListesi.length ; x++) {
            if(paylasimListesi[x] == null) {
                paylasimListesi[x] = paylasim;
                break;
            }
        }
    }//Gelen paylaşımı paylaşım listesine ekler
    
    public void paylasimlarimiListele() {
        for(int x = 0 ; x < paylasimListesi.length ; x++) { //kullanıcının yapmış olduğu paylaşımlar listelenir.
            if(paylasimListesi[x] != null) {
                System.out.println(paylasimListesi[x].getPaylasimID()+" "+paylasimListesi[x].getBaslik()+" "+paylasimListesi[x].getIcerik()+" "+paylasimListesi[x].getKullanici().getAdSoyad()+" "+paylasimListesi[x].getBegeniSayisi());
                for(int y = 0 ; y < paylasimListesi[x].getBegenenListesi().length ; y++) {
                    if(paylasimListesi[x].getBegenenListesi()[y] != null) {
                        System.out.print("Beğenenler: ");
                        System.out.println(paylasimListesi[x].getBegenenListesi()[y].getAdSoyad()+" ");
                    }
                }
            }
        }
    } //yapılan paylaşımları listeler
    
    public abstract void paylasimYap(); //premium ve standart kullanıcı arasında değişkenlik gösteren bu method abstract olarak tanımlanır, gövdesizdir.
    
    public void paylasimBegen() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Beğenilecek Paylaşımın ID'si: "); //beğenilecek paylaşımın id'si kullanıcıdan istenir
        String tercihID = scanner.nextLine();
        
        int tercihIndeksx = -1;
        int tercihIndeksy = -1;
        for(int x = 0 ; x < arkadasListesi.length ; x++) { //ilk döngü kullanıcın arkadaş listesinde dolaşır
            if(arkadasListesi[x] != null) {
                for(int y = 0 ; y < arkadasListesi[x].paylasimListesi.length ; y++) { //ikinci döngü kullanıcının arkadaşının paylaşımları arasında dolaşır
                    if(arkadasListesi[x].paylasimListesi[y] != null) {
                        if(arkadasListesi[x].paylasimListesi[y].getPaylasimID() == Integer.parseInt(tercihID)) {
                            tercihIndeksy = y; 
                            tercihIndeksx = x;
                            break; //beğenilmek istenilen paylaşımın indeksleri kayıt edilir
                        }
                    }
                }    
            }
        }
        
        if(tercihIndeksy == -1) { //eğer bu idye ait bir paylaşım yok ise kullanıcı uyarılır
            System.out.println("Bu ID'de Bir Paylaşım Yok ya da Bu Paylaşımı Siz Yapmışsınız...");
        }
        else { //eğer bu idye sahip bri paylaşım varsa paylaşım classındaki paylasim beğen methodu çağırılır.
            arkadasListesi[tercihIndeksx].paylasimListesi[tercihIndeksy].paylasimBegen(this);
        }
        
    } // idsi girilen paylaşımı beğenir
    
    public void paylasimSil() { //idsi girilen paylaşım kullanıcıya ait ise silinir
        Scanner scanner = new Scanner(System.in);
        System.out.print("Silinecek Paylaşımın ID'si: ");
        String tercihID = scanner.nextLine(); //silinmek istenilen paylaşımın idsini alır
        
        int tercihIndeks = -1;
        for(int x = 0 ; x <  paylasimListesi.length ; x++) { //paylaşım listesinde bu idye sahip bir paylaşım olup olmadığına bakılır
            if(paylasimListesi[x] != null) {
                if(paylasimListesi[x].getPaylasimID() == Integer.parseInt(tercihID)) {
                    tercihIndeks = x;
                    break;
                }
            }
        }
        
        if(tercihIndeks == -1) { //eğer bu paylaşım yok ise kullanıcı uyarılır
            System.out.println("Bu ID'Ye sahip Bir Paylaşımınız Yok...");
        }
        else { //eğer paylaşım var iste paylaşim listesinden silinir
            paylasimListesi[tercihIndeks] = null;
            Paylasim.getPaylasimListesi()[Integer.parseInt(tercihID)] = null; 
            System.out.println("Paylaşım Başarıyla Silindi...");
        }
    }// idsi girilen paylaşımı siler
    
    public void yorumGoruntule() { // yorumları görünütülenmek istenilen paylaşımın idsi alınır
        Scanner scanner = new Scanner(System.in);
        System.out.print("Yorumları Görüntülenmek İstenen Paylaşımın ID'si: ");
        String tercihID = scanner.nextLine();
        
        int tercihIndeksx = -1; //yorumu görüntülenmek istenilen paylaşım kullanıcın bir paylaşımı mı yoksa arkadaşının bir paylaşımı mı olup olmadığına bakılır.
        int tercihIndeksy = -1;
        boolean paylasimListesindeMi = false;
        boolean arkadasListesindeMi = false;
        
        for(int x = 0 ; x < paylasimListesi.length ; x++) { //ilk önce kullanıcın paylaşım listesinde aranır
            if(paylasimListesi[x] !=  null) {
                if(paylasimListesi[x].getPaylasimID() == Integer.parseInt(tercihID)) {
                    tercihIndeksx = x; //eğer paylaşım kullanıcıya ait ise paylasimListesindeMi değişkeni true değer alır
                    paylasimListesindeMi = true;
                    break;
                }
            }
        }
        for(int x = 0 ; x < arkadasListesi.length ; x++) {
            if(arkadasListesi[x] != null) {
                for(int y = 0 ; y < arkadasListesi[x].paylasimListesi.length ; y++) {
                    if(arkadasListesi[x].paylasimListesi[y] != null) {
                        if(arkadasListesi[x].paylasimListesi[y].getPaylasimID() == Integer.parseInt(tercihID)) {
                            tercihIndeksx = x;  //eğer paylaşım arkadaş listesine ait ise arkadasListesindeMi değişkeni true değer alır
                            tercihIndeksy = y;
                            arkadasListesindeMi = true;
                            break;
                        }
                    }
                }
            }
        }
        
        if(paylasimListesindeMi) { //paylaşımın kim tarafında yapılmış olduğuna göre gerekli paylaşımın yorumları görüntülenir.
            paylasimListesi[tercihIndeksx].yorumListele();        
        }
        else if(arkadasListesindeMi) {
            arkadasListesi[tercihIndeksx].paylasimListesi[tercihIndeksy].yorumListele();
        }
        else {
            System.out.println("Bu Paylaşımın Yorumlarına Erişiminiz Yok...");
        }
    } //idsi girilen paylaşımın yorumlarını listeler
    
    public void yorumYap() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Yorum Yapılacak Paylaşımın ID'si: ");
        String tercihID = scanner.nextLine(); //yorum görüntüle methodu ile benzer bir yapıya sahiptir.
        
        int tercihIndeksx = -1; 
        int tercihIndeksy = -1;
        boolean paylasimListesindeMi = false; 
        boolean arkadasListesindeMi = false;
        for(int x = 0 ; x < paylasimListesi.length ; x++) { //ilk döngü yorumun paylaşım listesinde olup olmadığna bakar
            if(paylasimListesi[x] !=  null) {
                if(paylasimListesi[x].getPaylasimID() == Integer.parseInt(tercihID)) {
                    tercihIndeksx = x;
                    paylasimListesindeMi = true; //eğer paylaşım kullanıcı tarafında yapılmışsa paylasimListesindeMi değişkeni true değer alır
                    break;
                }
            }
        }
        for(int x = 0 ; x < arkadasListesi.length ; x++) {
            if(arkadasListesi[x] != null) {
                for(int y = 0 ; y < arkadasListesi[x].paylasimListesi.length ; y++) {
                    if(arkadasListesi[x].paylasimListesi[y] != null) {
                        if(arkadasListesi[x].paylasimListesi[y].getPaylasimID() == Integer.parseInt(tercihID)) {
                            tercihIndeksx = x;
                            tercihIndeksy = y;
                            arkadasListesindeMi = true; // eğer paylaşım arkadaş listesindeki biri tarafından yapılmış ise arkadasListesindeMi değişkeni true değer alır
                            break;
                        }
                    }
                }
            }
        }
        
        if(paylasimListesindeMi) { //eğer paylaşım kullanıcı tarafından yapılmış ise bu kod bloğu çalışır
            System.out.print("Yapacağınız Yorumun İçeriği: "); 
            String icerik = scanner.nextLine();
            Yorum yorum = new Yorum(icerik, this, paylasimListesi[tercihIndeksx]);
            paylasimListesi[tercihIndeksx].yorumYap(yorum);
                    
        }
        else if(arkadasListesindeMi) { //eğer paylaşım bir arkadaş tarafında yapılmış ise bu kod bloğu çalışır
            System.out.print("Yapacağınız Yorumun İçeriği: ");
            String icerik = scanner.nextLine();
            Yorum yorum = new Yorum(icerik, this, arkadasListesi[tercihIndeksx].paylasimListesi[tercihIndeksy]);
            arkadasListesi[tercihIndeksx].paylasimListesi[tercihIndeksy].yorumYap(yorum);
        }
        else { //eğer paylaşım her ikisinde de değil ise kullancı uyarılır
            System.out.println("Bu Paylaşımın Yorumlarına Erişiminiz Yok...");
        }
    }// idsi girilen paylaşıma yorum yapar
    
    public void yorumSil() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Silinecek Yorumun ID'si: ");
        String tercihID = scanner.nextLine();
        
        int tercihIndeks = -1;
        for(int x = 0 ; x < Yorum.getYorumListesi().length ; x++) {
            if(Yorum.getYorumListesi()[x] != null) { //idsi girilen yorum var ve yorumu yapan kullanıcı, methodu kullanan kullanıcı ise yorumun indeksi kayıt edilir
                if((Yorum.getYorumListesi()[x].getYorumID() == Integer.parseInt(tercihID)) && (Yorum.getYorumListesi()[x].getKullanici() == this)) {
                    tercihIndeks = x;
                    break;
                }
            }
        }
        
        if(tercihIndeks == -1) { //eğer yorumun indeksi kayıt edilmemiş ise kullancı uyarılır
            System.out.println("Bu Yorum Size Ait Değil ya da Böyle Bir Yorum Yok...");
        }
        else { //eğer bu yorum var ve kullanıcıya ait ise ikinci bir döngü bu yorumu tüm yorumların saklandığı Yorum listesindeki yerini tespit eder
            int tercihIndeks2 = -1;
            for(int x = 0 ; x < Yorum.getYorumListesi()[tercihIndeks].getPaylasim().getYorumListesi().length ; x++) {
                if(Yorum.getYorumListesi()[tercihIndeks].getPaylasim().getYorumListesi()[x] != null) {
                    if(Yorum.getYorumListesi()[tercihIndeks].getPaylasim().getYorumListesi()[x].getYorumID() == Integer.parseInt(tercihID)) {
                        tercihIndeks2 = x;
                    }
                }
            }
            Yorum.getYorumListesi()[tercihIndeks].getPaylasim().getYorumListesi()[tercihIndeks2] = null;
            Yorum.getYorumListesi()[tercihIndeks] = null; //yorumlar gerekli listelerden kaldırılır
        }
    }// isdi girilen yorumu siler
    
    private void chatboxEkle(Chatbox chatbox) {
        for(int x = 0 ; x < chatboxListesi.length ; x++) {
            if(chatboxListesi[x] == null) {
                chatboxListesi[x] = chatbox;
                break;
            }
        }
    }// parametre olarak aldığı chatboxı kullanıcının chatbox listesine ekler
    
    public void chatboxOlustur() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Chatbox oluşturulmak istenen kullanıcının ID'si: ");
        String tercihID = scanner.nextLine();
        
        int tercihIndeks = -1; //chatbox oluşturulmak istenilen kullanıcın idsi arkadaş listesinde aranır.
        for(int x = 0 ; x < arkadasListesi.length ; x++) {
            if(arkadasListesi[x] != null) {
                if(arkadasListesi[x].getId() == Integer.parseInt(tercihID)) {
                    tercihIndeks = x;
                    break;
                }
            }
        }
        
        
        
        if(tercihIndeks == -1) { //eğer id bulunmaz ise kullanıcı uyarılır
            System.out.println("Arkadaş Listenizde Böyle Bir Kullanıcı Yok...");
        }
        else { // eğer id var ise ikinci döngü bu kullanıcıyla hali hazırda bir chatbox oluşturulup oluşturulmadığna bakar
            boolean chatboxVarmi = false;
            for(int x = 0 ; x < chatboxListesi.length ; x++) {
                if(chatboxListesi[x] != null) {
                    if((chatboxListesi[x].getKullanici1() == this && chatboxListesi[x].getKullanici2() == arkadasListesi[tercihIndeks]) || (chatboxListesi[x].getKullanici2() == this && chatboxListesi[x].getKullanici1() == arkadasListesi[tercihIndeks])) {
                        chatboxVarmi = true;
                        break;
                    }
                }
            }
            if(chatboxVarmi) { //eğer chatbox zaten oluşturulmuş ise kullanıcı uyarılır
                System.out.println("Bu Kullanıcı İle Zaten Bir Chatbox Oluşturmuşsunuz...");
            }
            else { //eğer chatbox oluşturulamdıysa chatbox oluşturulur
                Chatbox chatbox = new Chatbox(arkadasListesi[tercihIndeks], this);
                chatboxEkle(chatbox);
                arkadasListesi[tercihIndeks].chatboxEkle(chatbox);
                System.out.println("Chatbox Oluşturuldu...");
            }
            
        }
        
    }//kullanıcının bir arkadaşıyla chatbox oluşturmasını sağlar
    
    public void chatboxMesajYolla() {
        Scanner scanner = new Scanner(System.in);
        
        for(int x = 0 ; x < chatboxListesi.length ; x++) { //ilk döngü kullanıcının chatboxlarını görüntülemesi için onları listeler
            if(chatboxListesi[x] != null) {
                System.out.println(chatboxListesi[x].getId()+" "+chatboxListesi[x].getKullanici1().getAdSoyad()+" "+chatboxListesi[x].getKullanici2().getAdSoyad());
            }
        }
        System.out.println("");
        System.out.print("Mesaj Gönderilecek Chatbox ID'si: ");
        String tercihID = scanner.nextLine();
        
        int tercihIndeks = -1; //kullanıcının idsini girdği chatboxın chatbox listesinde olup olmadığına bakar
        for(int x = 0 ; x < chatboxListesi.length ; x++) {
            if(chatboxListesi[x] != null) {
                if(chatboxListesi[x].getId() == Integer.parseInt(tercihID)) {
                    tercihIndeks = x;
                    break;
                }
            }
        }
        
        
        if(tercihIndeks == -1) { //eğer bu idye sahip bir chatbox yok ise kullanıcıyı uyarır
            System.out.println("Bu ID'ye Sahip Bir Chatbox Yok...");
        }
        else { //eğer var ise bu mesaj chatboxın içeriğine kayıt edilir ve mesajdan sonra bi alt satıra geçilir
            System.out.print("Göderilecek Mesaj: ");
            String mesaj = scanner.nextLine();
            chatboxListesi[tercihIndeks].setYazisma(chatboxListesi[tercihIndeks].getYazisma()+this.getAdSoyad()+": "+mesaj+"\n");
        }
        
    }//oluşturulmuş chatboxa mesaj yollanmasını sağlar
    
    public void chatboxGoruntule() {
        Scanner scanner = new Scanner(System.in);
        for(int x = 0 ; x < chatboxListesi.length ; x++) { //kullanıcıya chatboxlistesindeki chatboxları listeler
            if(chatboxListesi[x] != null) {
                System.out.println(chatboxListesi[x].getId()+" "+chatboxListesi[x].getKullanici1().getAdSoyad()+" "+chatboxListesi[x].getKullanici2().getAdSoyad());
            }
        }
        System.out.println("");
        System.out.print("Mesajları Görüntülenecek Chatboxın ID'si: ");
        String tercihID = scanner.nextLine();
        
        int tercihIndeks = -1; //kullanıcının görüntülemek istediği chatboxın idsini chatbox listesinde arar, var ise kayıt eder
        for(int x = 0 ; x < chatboxListesi.length ; x++) {
            if(chatboxListesi[x] != null) {
                if(chatboxListesi[x].getId() == Integer.parseInt(tercihID)) {
                    tercihIndeks = x;
                    break;
                }
            }
        }
        
        if(tercihIndeks == -1) {//eğer id yok ise kullanıcı uyarılır
            System.out.println("Bu ID'ye Sahip Bir Chatbox Yok...");
        }
        else { //eğer bu idye sahip bir chatbox var ise içeriğini kullanıcıya gösterir
            System.out.println(chatboxListesi[tercihIndeks].getYazisma());
        } 
    }//oluşturulmuş chatboxdaki mesajlaşmayı kullanıcıya gösterir
    
    public void maxminBegeni() { //kullanıcının paylaşımları arasında dolaşarak en çok ve en az beğenilmiş paylaşımları kullanıcya gösterir
        boolean paylasimYapildiMi = false; // ilk döngü kullanıcının şimdiye kadar bir paylaşım yapıp yapmadığına bakar
        for(int x = 0 ; x < paylasimListesi.length ; x++) {
            if(paylasimListesi[x] != null) {
                paylasimYapildiMi = true;
                break;
            }
        }
        
        if(paylasimYapildiMi) { 
            int maxBegeni = -1;
            int minBegeni = 11;
            for(int x = 0 ; x < paylasimListesi.length ; x++) { // eğer kullanıcı paylaşım yapdıysa ilk döngü max ve min beğeni sayılarını kayıt eder
                if(paylasimListesi[x] != null) {
                    maxBegeni = Math.max(maxBegeni, paylasimListesi[x].getBegeniSayisi());
                    minBegeni = Math.min(minBegeni, paylasimListesi[x].getBegeniSayisi());
                }
            }
            
            int minIndeks = -1;
            int maxIndeks = -1;
            for(int x = 0 ; x < paylasimListesi.length ; x++) {
                if(paylasimListesi[x] != null) { //ikinci döngü bu sayılara ait paylaşımların indekslerini kayıt eder
                    if(paylasimListesi[x].getBegeniSayisi() == maxBegeni) {
                        maxIndeks = x;
                    }
                    if(paylasimListesi[x].getBegeniSayisi() == minBegeni) {
                        minIndeks = x;
                    }
                }
            }
            System.out.println("Max Beğeni: "+paylasimListesi[maxIndeks].getBaslik()); //son olarak kullanıcıya bu paylaşımlar gösterilir
            System.out.println("Min Beğeni: "+paylasimListesi[minIndeks].getBaslik());   
        }
        else { // eğer kullanıcı henüz bir paylaşım yapmadıysa kullanıcı uyarılır
            System.out.println("Herhangi Bir Paylaşım Yapmadınız...");
        }
    }//kullanıcın en çok ve en az beğenilmiş paylaşımlarını gösterir
    
    public void maxminYorum() { 
        boolean paylasimYapildiMi = false;
        for(int x = 0 ; x < paylasimListesi.length ; x++) { //ilk döngü kullanıcın paylaşım yapıp yapmadığına bakar
            if(paylasimListesi[x] != null) {
                paylasimYapildiMi = true;
                break;
            }
        }
        
        if(paylasimYapildiMi) {
            int maxYorum = -1;
            int minYorum = 11;
            for(int x = 0 ; x < paylasimListesi.length ; x++) { //eğer kullanıcı paylaşım yapmış ise kullanıcın en çok ve en az yorum yapılmış paylaşımlarının yorum sayısı kayıt edilir
                if(paylasimListesi[x] != null) {
                    maxYorum = Math.max(maxYorum, paylasimListesi[x].getYorumSayisi());
                    minYorum = Math.min(minYorum, paylasimListesi[x].getYorumSayisi());
                }
            }
            
            int minIndeks = -1;
            int maxIndeks = -1;
            for(int x = 0 ; x < paylasimListesi.length ; x++) { //kayıt edilen yorumların indeksleri bu döngü ile tespit edilir
                if(paylasimListesi[x] != null) {
                    if(paylasimListesi[x].getYorumSayisi() == maxYorum) {
                        maxIndeks = x;
                    }
                    if(paylasimListesi[x].getYorumSayisi() == minYorum) {
                        minIndeks = x;
                    }
                }
            }
            System.out.println("Max Yorum: "+paylasimListesi[maxIndeks].getBaslik()); //son olarak kullanıcıya en çok ve en az yorum yapılan paylaşımları gösterilir
            System.out.println("Min Yorum: "+paylasimListesi[minIndeks].getBaslik());
        }
        else { //herhangi bir paylaşım yapılmadıysa kullanıcı uyarılır
            System.out.println("Herhangi Bir Paylaşım Yapmadınız...");
        }
    }//kullanıcın en çok ve en az yorum yapılmış paylaşımlarını gösterir
    
    public void telefonGoster() {
        for(int x = 0 ; x < paylasimListesi.length ; x++) {
            if(paylasimListesi[x] != null) {
                for(int y = 0 ; y < paylasimListesi[x].getYorumListesi().length ; y++) {
                    if(paylasimListesi[x].getYorumListesi()[y] != null) {
                        boolean telefonVarMi = false; //eğer kullanıcın yorum içeriğinde "xxx 565 854 4587 xxx" şeklinde ya da sadece "555 568 1234" mesajı varsa bu tip mesajlar kullanıcıya gösterilir
                        if(paylasimListesi[x].getYorumListesi()[y].getIcerik().matches("(.*) \\d{3} \\d{3} \\d{4} (.*)") || paylasimListesi[x].getYorumListesi()[y].getIcerik().matches("\\d{3} \\d{3} \\d{4}")) {
                            telefonVarMi = true;
                        }
                        if(telefonVarMi) {
                            System.out.println(paylasimListesi[x].getBaslik()+" Başlıklı Paylaşımınızın "+paylasimListesi[x].getYorumListesi()[y].getYorumID()+" ID'li yorumunda Tel No Bulunuyor");
                        }
                    }
                }    
            }   
        }
    }//içinde 11 haneli telefon no bulunan yorumları gösterir
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public int getGelenArkadasSayisi() {
        int arkadasSayisi = 0; // gelen arkadaş istekleri listesindeki null olmayan tüm noktaları sayarak elde ettiği sayıyı kullanıcıya döndürür
            for(int x = 0 ; x < gelenArkadaslikIstekleri.length ; x++) {
                if(gelenArkadaslikIstekleri[x] != null) {
                    arkadasSayisi++;
                }
            }
        return arkadasSayisi;
    }
    
    public Kullanici[] getArkadasListesi() {
        return arkadasListesi;
    }

    public int getArkadasSayisi() {
        int arkadasSayisi = 0; //arkadaş listesindeki null olmayan noktaları sayarak gelen sayıyı kullanıcıya döndürür
        for(int x = 0 ; x < arkadasListesi.length ; x++) {
            if(arkadasListesi[x] != null) {
                arkadasSayisi++;
            }
        }
        return arkadasSayisi;
    }
    
    public String getAdSoyad() {
        return adSoyad;
    }

    public int getId() {
        return id;
    }

    public static Kullanici[] getKullaniciListesi() {
        return kullaniciListesi;
    }

    public Kullanici[] getGelenArkadaslikIstekleri() {
        return gelenArkadaslikIstekleri;
    }

    public Paylasim[] getPaylasimListesi() {
        return paylasimListesi;
    }  
}
