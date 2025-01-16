# Mini E-Ticaret Platformu

Bu proje, konsol tabanlı bir e-ticaret simülasyonudur. Kullanıcılar giriş yapabilir, ürünleri görüntüleyebilir, sepete ürün ekleyebilir ve satın alma işlemleri gerçekleştirebilir.

## Özellikler:
- Kullanıcı giriş yapabilir veya kayıt olabilir.
- Ürünler listelenebilir ve stok durumu görüntülenebilir.
- Sepete ürün ekleme ve satın alma işlemleri yapılabilir.
- Ürün ve kullanıcı bilgileri `data/users.txt` ve `data/products.txt` dosyalarında saklanır.

## Kurulum:
1. Projeyi klonlayın:
   ```bash
   git clone https://github.com/<kullanici-adi>/<repo-adi>.git

## Kullanım:
Uygulama başlatıldığında, şu menüyle karşılaşacaksınız:

--- E-Ticaret Platformu ---
1. Giriş Yap
2. Kayıt Ol
3. Çıkış

Giriş yaptıktan sonra:

--- Hoş Geldiniz, [Kullanıcı Adı] ---
1. Ürünleri Görüntüle
2. Sepete Ürün Ekle
3. Satın Al
4. Çıkış Yap

	•	Ürünleri Görüntüle: Mevcut ürünler ve stok durumu listelenir.
	•	Sepete Ürün Ekle: Ürün ID’si seçilerek sepete eklenir.
	•	Satın Al: Sepetteki ürünler satın alınır.
