package view;

import controller.ProductController;
import model.dto.ProductCreateDto;
import model.dto.UpdateProductDto;

import java.time.LocalDate;
import java.util.Scanner;

public class UI {
    private final static Scanner scanner = new Scanner(System.in);
    private final static ProductController productController = new ProductController();
    public static void home(){
        do{
            switch (menu()){
                case 1 -> {
                    productController.getAllProducts().forEach(System.out::println);
                }
                case 2 -> {
                    System.out.println("[+] Enter product name: ");
                    String name = scanner.nextLine();
                }
                case 3 -> {
                    System.out.println("[+] Enter product name: ");
                    String name = scanner.nextLine();
                    UpdateProductDto updateProductDto = new UpdateProductDto(name);
                }
                case 4 -> {
                    return;
                }
            }
        }while(true);
    }

    public static int menu(){
        System.out.println("""
        ╔════════════════════════════════╗
        ║         PROGRAM MENU           ║
        ╠════════════════════════════════╣
        ║  1. Get all products           ║
        ║  2. Add new product            ║
        ║  3. Update product             ║
        ║  4. Exit                       ║
        ╚════════════════════════════════╝""");
        int choice = 0;
        do {
            System.out.print("[+] Enter choice: ");
            try{
                choice = Integer.parseInt(scanner.nextLine());
            }catch(NumberFormatException ignored){
                choice = 0;
            }

        }while (choice < 1 || choice > 4 );
        return choice;
    }
}
