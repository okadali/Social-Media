/*
 * @file Main.java 
 * @description OOP sosyal medya uygulaması 
 * @assignment Proje 03 
 * @date 30.01.2021 
 * @author Ömer Kerem Adalı & omerkerem.adali@stu.fsm.edu.tr 
 */

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StandartKullanici k1 = new StandartKullanici("Omer1");
        StandartKullanici k2 = new StandartKullanici("Omer2");
        StandartKullanici k3 = new StandartKullanici("Omer3");
        StandartKullanici k4 = new StandartKullanici("Omer4");
        StandartKullanici k5 = new StandartKullanici("Omer5");
        StandartKullanici k6 = new StandartKullanici("Omer6");
        StandartKullanici k7 = new StandartKullanici("Omer7");
        StandartKullanici k8 = new StandartKullanici("Omer8");
        StandartKullanici k9 = new StandartKullanici("Omer9");
        PremiumKullanici k10 = new PremiumKullanici("Omer10");
        
        Scanner scanner = new Scanner(System.in);
        String secenek1 = "";
        while(!secenek1.equals("2")) {
            System.out.println("********************");
            System.out.println("* 1.Kullanıcı Seç  *");
            System.out.println("* 2.Programı Kapat *");
            System.out.println("********************");
            System.out.println("");
            
            System.out.print("Seçenek: ");
            secenek1 = scanner.nextLine();
            
            while(!secenek1.equals("1")&&!secenek1.equals("2")) {
                System.out.println("Geçersiz Seçenek Girdiniz...");
                System.out.print("Seçenek: ");
                secenek1 = scanner.nextLine();
            }
            
            if(secenek1.equals("1")) {
                Kullanici.kullaniciListele();
                System.out.print("ID: ");
                String tercihID = scanner.nextLine();
                
                while(!tercihID.equals("0")&&!tercihID.equals("1")&&!tercihID.equals("2")&&!tercihID.equals("3")&&!tercihID.equals("4")&&!tercihID.equals("5")&&!tercihID.equals("6")&&!tercihID.equals("7")&&!tercihID.equals("8")&&!tercihID.equals("9")) {
                    System.out.println("Geçersiz ID Girdiniz...");
                    System.out.print("ID: ");
                    tercihID = scanner.nextLine();
                }
                //Kullanici.getKullaniciListesi()[Integer.parseInt(tercihID)] => profiline giriş yapılan kişi
                String secenek2 = "";
                while(!secenek2.equals("q")) {
                    while(Kullanici.getKullaniciListesi()[Integer.parseInt(tercihID)].istekVarmi()) { //kullanıcın arkadaş isteği var ise bu döngü tüm istekler değerlendirilene kadar devam eder
                        Kullanici.getKullaniciListesi()[Integer.parseInt(tercihID)].arkadasIstegiDegerlendir();
                    }
                    System.out.println("");
                    System.out.println(Kullanici.getKullaniciListesi()[Integer.parseInt(tercihID)].getAdSoyad());
                    System.out.println("*************************************");
                    System.out.println("* 1. Arkadaş Ekle                   *");
                    System.out.println("* 2. Arkadaş Sil                    *");
                    System.out.println("* 3. Arkadaşlarım                   *");
                    System.out.println("* 4. Paylaşım Yap                   *");
                    System.out.println("* 5. Paylaşım Listele               *");
                    System.out.println("* 6. Paylaşım Beğen                 *");
                    System.out.println("* 7. Paylaşım Sil                   *");
                    System.out.println("* 8. Paylaşım Düzenle               *");
                    System.out.println("* 9. Yorum Görüntüle                *");
                    System.out.println("* 10. Yorum Yap                      *");
                    System.out.println("* 11. Yorum Sil                     *");
                    System.out.println("* 12. Chatbox Oluştur               *");
                    System.out.println("* 13. Chatbox İle Mesaj Yolla       *");
                    System.out.println("* 14. Chatbox Görüntüle             *");
                    System.out.println("* 15. Max ve Min Beğenilen Paylaşım *");
                    System.out.println("* 16. Max ve Min Yapılan Yorum      *");
                    System.out.println("* 17. Telefonlu Yorum Görüntüle     *");
                    System.out.println("* q.  Çıkış Yap                     *");
                    System.out.println("*************************************");
                    System.out.println("");
                    System.out.print("Seçenek: ");
                    secenek2 = scanner.nextLine();
                    
                    switch(secenek2) {
                        case "1":
                            System.out.print("Arkadaş İsmi: ");
                            String adSoyad = scanner.nextLine();
                            Kullanici.getKullaniciListesi()[Integer.parseInt(tercihID)].arkadasIstegiYolla(adSoyad);
                            break;
                        case "2":
                            System.out.print("Arkadaş İsmi: ");
                            String silinecekIsim = scanner.nextLine();
                            Kullanici.getKullaniciListesi()[Integer.parseInt(tercihID)].arkadasSil(silinecekIsim);
                            break;
                        case "3":
                            Kullanici.getKullaniciListesi()[Integer.parseInt(tercihID)].arkadasListele();
                            break;
                        case "4":
                            Kullanici.getKullaniciListesi()[Integer.parseInt(tercihID)].paylasimYap();
                            break;
                        case "5":
                            Kullanici.getKullaniciListesi()[Integer.parseInt(tercihID)].paylasimlarimiListele(); //bu method kullanıcının kendi paylaşımlarını listeler
                            for(int x = 0 ; x < Kullanici.getKullaniciListesi()[Integer.parseInt(tercihID)].getArkadasListesi().length ; x++) { // buradaki döngü kullanıcının arkadaş listesinde gezinerek o kullanıcların paylaşımlarını listeler
                                if(Kullanici.getKullaniciListesi()[Integer.parseInt(tercihID)].getArkadasListesi()[x] != null) {
                                    Kullanici.getKullaniciListesi()[Integer.parseInt(tercihID)].getArkadasListesi()[x].paylasimlarimiListele();
                                }
                            }
                            break;
                        case "6":
                            Kullanici.getKullaniciListesi()[Integer.parseInt(tercihID)].paylasimBegen();
                            break;
                        case "7":
                            Kullanici.getKullaniciListesi()[Integer.parseInt(tercihID)].paylasimSil();
                            break;
                        case "8": //kullanıcı premium ise özelliğe erişim sağlanır değil ise erişimi olmadığı kullanıcıya bildirilir
                            if(Kullanici.getKullaniciListesi()[Integer.parseInt(tercihID)] instanceof StandartKullanici) {
                                System.out.println("Bu Özelliği Premium Kullanıcılar Kullanabilir...");
                            }
                            else {
                                ((PremiumKullanici)Kullanici.getKullaniciListesi()[Integer.parseInt(tercihID)]).paylasimDuzenle();
                            }
                            break;
                        case "9":
                            Kullanici.getKullaniciListesi()[Integer.parseInt(tercihID)].yorumGoruntule();
                            break;
                        case "10":
                            Kullanici.getKullaniciListesi()[Integer.parseInt(tercihID)].yorumYap();
                            break;
                        case "11":
                            Kullanici.getKullaniciListesi()[Integer.parseInt(tercihID)].yorumSil();
                            break;
                        case "12":
                            Kullanici.getKullaniciListesi()[Integer.parseInt(tercihID)].chatboxOlustur();
                            break;
                        case "13":
                            Kullanici.getKullaniciListesi()[Integer.parseInt(tercihID)].chatboxMesajYolla();
                            break;
                        case "14":
                            Kullanici.getKullaniciListesi()[Integer.parseInt(tercihID)].chatboxGoruntule();
                            break;
                        case "15":
                            Kullanici.getKullaniciListesi()[Integer.parseInt(tercihID)].maxminBegeni();
                            break;
                        case "16":
                            Kullanici.getKullaniciListesi()[Integer.parseInt(tercihID)].maxminYorum();
                            break;
                        case "17":
                            Kullanici.getKullaniciListesi()[Integer.parseInt(tercihID)].telefonGoster();
                            break;
                    }
                }
            }  
        }
        System.out.println("İyi Günler...");
    }    
}
