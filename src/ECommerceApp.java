import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ECommerceApp {
    private static ArrayList<User> users = new ArrayList<>();
    private static ArrayList<Product> products = new ArrayList<>();
    private static User currentUser = null;

    public static void main(String[] args) {
        loadUsers();
        loadProducts();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            if (currentUser == null) {
                System.out.println("\n--- E-Ticaret Platformu ---");
                System.out.println("1. Giriş Yap");
                System.out.println("2. Kayıt Ol");
                System.out.println("3. Çıkış");
                System.out.print("Seçiminiz: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        login(scanner);
                        break;
                    case 2:
                        register(scanner);
                        break;
                    case 3:
                        System.out.println("Çıkış yapılıyor...");
                        return;
                    default:
                        System.out.println("Geçersiz seçim. Tekrar deneyin.");
                }
            } else {
                System.out.println("\n--- Hoş Geldiniz, " + currentUser.getUsername() + " ---");
                System.out.println("1. Ürünleri Görüntüle");
                System.out.println("2. Sepete Ürün Ekle");
                System.out.println("3. Satın Al");
                System.out.println("4. Çıkış Yap");
                System.out.print("Seçiminiz: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        listProducts();
                        break;
                    case 2:
                        addToCart(scanner);
                        break;
                    case 3:
                        checkout();
                        break;
                    case 4:
                        currentUser = null;
                        System.out.println("Oturum kapatıldı.");
                        break;
                    default:
                        System.out.println("Geçersiz seçim. Tekrar deneyin.");
                }
            }
        }
    }

    private static void login(Scanner scanner) {
        System.out.print("Kullanıcı Adı: ");
        String username = scanner.nextLine();
        System.out.print("Şifre: ");
        String password = scanner.nextLine();

        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                currentUser = user;
                System.out.println("Giriş başarılı!");
                return;
            }
        }
        System.out.println("Hatalı kullanıcı adı veya şifre.");
    }

    private static void register(Scanner scanner) {
        System.out.print("Yeni Kullanıcı Adı: ");
        String username = scanner.nextLine();
        System.out.print("Şifre: ");
        String password = scanner.nextLine();

        users.add(new User(username, password));
        saveUsers();
        System.out.println("Kayıt başarılı! Giriş yapabilirsiniz.");
    }

    private static void listProducts() {
        if (products.isEmpty()) {
            System.out.println("Henüz ürün eklenmedi.");
        } else {
            for (Product product : products) {
                System.out.println(product);
            }
        }
    }

    private static void addToCart(Scanner scanner) {
        listProducts();
        System.out.print("Sepete eklemek istediğiniz ürün ID'si: ");
        int productId = scanner.nextInt();
        scanner.nextLine();

        for (Product product : products) {
            if (product.getId() == productId) {
                if (product.getStock() > 0) {
                    product.setStock(product.getStock() - 1);
                    System.out.println("Ürün sepete eklendi: " + product.getName());
                    saveProducts();
                    return;
                } else {
                    System.out.println("Ürün stokta yok.");
                    return;
                }
            }
        }
        System.out.println("Geçersiz ürün ID'si.");
    }

    private static void checkout() {
        System.out.println("Satın alma işlemi tamamlandı. Sepetiniz boşaltıldı!");
    }

    private static void loadUsers() {
        try (BufferedReader reader = new BufferedReader(new FileReader("data/users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                users.add(new User(parts[0], parts[1]));
            }
        } catch (IOException e) {
            System.out.println("Kullanıcılar yüklenirken hata oluştu.");
        }
    }

    private static void saveUsers() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/users.txt"))) {
            for (User user : users) {
                writer.write(user.getUsername() + ";" + user.getPassword());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Kullanıcılar kaydedilirken hata oluştu.");
        }
    }

    private static void loadProducts() {
        try (BufferedReader reader = new BufferedReader(new FileReader("data/products.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                products.add(new Product(
                        Integer.parseInt(parts[0]),
                        parts[1],
                        Double.parseDouble(parts[2]),
                        Integer.parseInt(parts[3])
                ));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Ürün dosyası bulunamadı. Varsayılan ürünler ekleniyor...");
            addDefaultProducts();
            saveProducts();
        } catch (IOException e) {
            System.out.println("Ürünler yüklenirken hata oluştu.");
        }
    }

    private static void addDefaultProducts() {
        products.add(new Product(1, "Telefon", 9999.99, 10));
        products.add(new Product(2, "Laptop", 19999.99, 5));
        products.add(new Product(3, "Kulaklık", 499.99, 20));
        products.add(new Product(4, "Akıllı Saat", 2999.99, 15));
        products.add(new Product(5, "Tablet", 7999.99, 8));
        System.out.println("Varsayılan ürünler başarıyla eklendi.");
    }

    private static void saveProducts() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/products.txt"))) {
            for (Product product : products) {
                writer.write(product.getId() + ";" + product.getName() + ";" + product.getPrice() + ";" + product.getStock());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Ürünler kaydedilirken hata oluştu.");
        }
    }
}