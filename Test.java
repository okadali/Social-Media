public class Test {
    public static void main(String[] args) {
        StandartKullanici kullanici1 = new StandartKullanici("Kullanici1");
        PremiumKullanici kullanici3 = new PremiumKullanici("Kullanici3");
        
        
        // #2 Arkadaşlık isteği yollama ve değerlendirme
        kullanici1.arkadasIstegiYolla("Kullanici3");
        kullanici3.arkadasIstegiDegerlendir();
        
        // #1 Arkadaş listeleme
        System.out.println("***************Arkadaş listeleme************");
        kullanici1.arkadasListele();
        kullanici3.arkadasListele();
        
        // #4 Arkadaş silme
        System.out.println("***************Arkadaş silme************");
        kullanici1.arkadasSil("Kullanici3");
        kullanici1.arkadasListele();
        kullanici3.arkadasListele();
        
        // #5 Paylaşım yapmak
        System.out.println("*************Paylaşım yapmak**************");
        kullanici1.paylasimYap(); //30 karakterden uzun
        kullanici1.paylasimlarimiListele();
        
        // #6-13 Paylaşım Düzenleme ve Standart/Premium
        System.out.println("**************Paylaşım Düzenleme ve Standart/Premium*************");
        kullanici3.paylasimYap(); 
        kullanici3.paylasimlarimiListele();
        kullanici3.paylasimDuzenle();
        kullanici3.paylasimlarimiListele();
        
        // #7 Paylaşım Sil
        System.out.println("***************Paylaşım Sil************");
        kullanici1.paylasimSil();
        kullanici1.paylasimlarimiListele();
        
        // #9 Paylaşım beğen ve yorum yap
        System.out.println("***************Paylaşım beğen ve yorum yap************");
        kullanici1.arkadasIstegiYolla("Kullanici3");
        kullanici3.arkadasIstegiDegerlendir();
        kullanici1.paylasimBegen();
        kullanici1.yorumYap();
        
        // #8-10 Paylaşım ve Yorum Listele
        System.out.println("**************Paylaşım ve Yorum Listele*************");
        kullanici3.paylasimlarimiListele();
        kullanici3.yorumGoruntule();
        kullanici1.yorumSil();
        kullanici3.yorumGoruntule();
        
        // #11 Chatbox Oluşturma
        System.out.println("**************Chatbox Oluşturma*************");
        Kullanici.kullaniciListele();
        kullanici1.chatboxOlustur();
        
        // #12 Özel Mesaj Gönderme
        System.out.println("**************Özel Mesaj Gönderme*************");
        kullanici1.chatboxMesajYolla();
        kullanici3.chatboxMesajYolla();
        kullanici1.chatboxGoruntule();
        kullanici3.chatboxGoruntule();
        
        // #14 Max ve min beğenilen paylaşım
        System.out.println("**************Max ve min beğenilen paylaşım*************");
        kullanici3.paylasimYap(); 
        kullanici3.maxminBegeni();
        
        // #15 Max ve min yorum yapılan paylaşım
        System.out.println("**************Max ve min yorum yapılan paylaşım*************");
        kullanici1.yorumYap(); //tel no gir
        kullanici3.maxminYorum();
        
        // #16 Tel no olan paylaşım
        System.out.println("**************Tel no olan paylaşımlar*************");
        kullanici3.telefonGoster();
    }
}
