public class Chatbox {
    private Kullanici kullanici1; 
    private Kullanici kullanici2;
    private int id;
    private String yazisma;//chatboxta yapılan yazışmalar bu string değişkende tutulur
    
    private static int idBelirleyici = 0; 
    private static Chatbox[] chatboxListesi = new Chatbox[45]; 

    public Chatbox(Kullanici kullanici1, Kullanici kullanici2) { //chatboxda mesajlaşacak iki kullanıcı değişken olarak alınır
        this.kullanici1 = kullanici1;
        this.kullanici2 = kullanici2;
        id = idBelirleyici++;
        chatboxListesi[id] = this;
        yazisma = ""; 
    }

    
    
    
    
    
    
    public Kullanici getKullanici1() {
        return kullanici1;
    }

    public Kullanici getKullanici2() {
        return kullanici2;
    }

    public int getId() {
        return id;
    }

    public String getYazisma() {
        return yazisma;
    }
    public void setYazisma(String yazisma) {
        this.yazisma = yazisma;
    } 
}
