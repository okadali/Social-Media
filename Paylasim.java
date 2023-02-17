public class Paylasim {
    private String baslik;
    private String icerik;
    private Kullanici kullanici;
    private int begeniSayisi;
    private Kullanici[] begenenListesi;
    private Yorum[] yorumListesi;
    private int paylasimID;
    
    private static Paylasim[] paylasimListesi = new Paylasim[100];
    private static int paylasimIndex = 0;

    public Paylasim(String baslik, String icerik, Kullanici kullanici) {
        this.baslik = baslik;
        this.icerik = icerik;
        this.kullanici = kullanici;
        paylasimID = paylasimIndex;
        begeniSayisi = 0;
        yorumListesi = new Yorum[30];
        begenenListesi = new Kullanici[10];
        kullanici.paylasimEkle(this); //paylaşım oluşturulduğu zaman paylaşımı oluşturan kullanıcının paylaşım listesine eklenir
        paylasimListesi[paylasimIndex++] = this; //kullanıcı classındaki aynı sistemle id belirlenir
    }
    
    public static void paylasimListele() { //şu ana kadar yapılmış tüm paylaşımların idlerini,baslıklarını,iceriklerini,kullanıcı adsoyadını ve beğeni saysısını listeler
        for(int x = 0 ; x < paylasimListesi.length ; x++) {
            if(paylasimListesi[x] != null) {
                System.out.println(paylasimListesi[x].getPaylasimID()+" "+paylasimListesi[x].baslik+" "+paylasimListesi[x].icerik+" "+paylasimListesi[x].getKullanici().getAdSoyad()+" "+paylasimListesi[x].begeniSayisi);
            }
        }
    }
    
    public void paylasimBegen(Kullanici kullanici) {
        boolean paylasimBegenildiMi = false; 
        
        int yIndeks = -1;//ilk döngü paylaşımın hali hazırda beğenilip beğenilmediğine bakar
        for(int y = 0 ; y < this.begenenListesi.length ; y++) {
            if(begenenListesi[y] != null) {
                if(begenenListesi[y] == kullanici) {
                    paylasimBegenildiMi = true;
                    yIndeks = y;
                    break;
                }
            }
        }
        
        if(paylasimBegenildiMi) { //eğer paylaşım zaten beğenildiyse bu beğeni geri çekilir ve kullanıcı beğenenler listesinden kaldırılır
            begenenListesi[yIndeks] = null;
            begeniSayisi--;
            System.out.println("Beğeninizi Geri Çektiniz...");
        }
        else {
            for(int x = 0 ; x < begenenListesi.length ; x++) { //eğer paylaşım hali hazırda beğenilmediyse beğenilir ve kullanıcı beğenenler listesine eklenir
                if(begenenListesi[x] == null) {
                    begenenListesi[x] = kullanici;
                    begeniSayisi++;
                    System.out.println("Paylaşımı Beğendiniz...");
                    break;
                }
            }
        }
    }
    
    public void yorumListele() {
        for(int x = 0 ; x < yorumListesi.length ; x++) { //paylaşımın yorumlarının idlerini,iceriklerklerini ve kullanıcı ad soyadını listeler
            if(yorumListesi[x] != null) {
                System.out.println(yorumListesi[x].getYorumID()+" "+yorumListesi[x].getKullanici().getAdSoyad()+" "+yorumListesi[x].getIcerik());
            }
        }
    }
    
    public void yorumYap(Yorum yorum) { //parametre olarak aldığı yorumu yorum listesindeki boş bir yere ekler
        for(int x = 0 ; x < yorumListesi.length ; x++) {
            if(yorumListesi[x] == null) {
                yorumListesi[x] = yorum;
                break;
            }
        }
    }
   


    
    public Kullanici getKullanici() {
        return kullanici;
    }

    public String getBaslik() {
        return baslik;
    }

    public String getIcerik() {
        return icerik;
    }
    public void setIcerik(String icerik) {
        this.icerik = icerik;
    }
    
    public int getBegeniSayisi() {
        return begeniSayisi;
    }
    public void setBegeniSayisi(int begeniSayisi) {
        this.begeniSayisi = begeniSayisi;
    }
    
    public int getPaylasimID() {
        return paylasimID;
    }

    public Kullanici[] getBegenenListesi() {
        return begenenListesi;
    }

    public static Paylasim[] getPaylasimListesi() {
        return paylasimListesi;
    }

    public Yorum[] getYorumListesi() {
        return yorumListesi;
    }
    
    public int getYorumSayisi() { //yorum listesindeki null olmayan her değeri sayar ve geri döndürür
        int yorumSayisi = 0;
        for(int x = 0 ; x < this.getYorumListesi().length ; x++) {
            if(this.getYorumListesi()[x] != null) {
                yorumSayisi++;
            }
        }
        
        return yorumSayisi;
    }
}
