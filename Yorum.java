public class Yorum {
    private int yorumID;
    private String icerik;
    private Kullanici kullanici;
    private Paylasim paylasim;
    
    private static int yorumIDBelirleyici = 0;
    private static Yorum[] yorumListesi = new Yorum[30]; // oluşturulan her yorum bu listeye eklenir 
    
    

    public Yorum(String icerik, Kullanici kullanici,Paylasim paylasim) { //yorum yapılacak paylaşım, yorumun içeriği ve yorumu yapan kullanıcı parametre olarak alınır
        yorumID = yorumIDBelirleyici++;//kullanıcı classında kullanılan idbelirleme sistemi burada da kullanırlır
        yorumListesi[yorumID] = this;
        this.icerik = icerik;
        this.kullanici = kullanici;
        this.paylasim = paylasim;
    }

    public static void yorumListele() { //static yorumListele methodu ile şimdiye kadar yapılmış tüm yorumlarınların idleri,kullanıcı ad soyadları ve içerikleri listelenir
        for(int x = 0 ; x < yorumListesi.length ; x++) {
            if(yorumListesi[x] != null) { 
                System.out.println(yorumListesi[x].yorumID+" "+yorumListesi[x].getKullanici().getAdSoyad()+" "+yorumListesi[x].getIcerik());
            }
        }
    }
    
    
    
    
    
    public String getIcerik() {
        return icerik;
    }

    public Kullanici getKullanici() {
        return kullanici;
    }

    public Paylasim getPaylasim() {
        return paylasim;
    }

    public int getYorumID() {
        return yorumID;
    }

    public static Yorum[] getYorumListesi() {
        return yorumListesi;
    } 
}
