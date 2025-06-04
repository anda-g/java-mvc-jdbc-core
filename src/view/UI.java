package view;

import controller.ProductController;
import model.dto.ProductCreateDto;
import model.dto.ProductResponseDto;
import model.dto.UpdateProductDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class UI {
    private final static Scanner scanner = new Scanner(System.in);
    private final static ProductController productController = new ProductController();
    private final static TableUI<ProductResponseDto> tableUI = new TableUI<>();
    public static void home(){
        do{
            switch (menu()){
                case 1 -> {
                    tableUI.getTableDisplay(productController.getAllProducts());
                }
                case 2 -> {
                    System.out.print("[+] Enter product name: ");
                    String name = scanner.nextLine();
                    System.out.print("[+] Enter expired year: ");
                    int year = Integer.parseInt(scanner.nextLine());
                    System.out.print("[+] Enter expired month: ");
                    int month = Integer.parseInt(scanner.nextLine());
                    System.out.print("[+] Enter expired day: ");
                    int day = Integer.parseInt(scanner.nextLine());

                    ProductCreateDto createDto = new ProductCreateDto(name, LocalDate.of(year, month, day));
                    ProductResponseDto responseDto = productController.insertNewProduct(createDto);
                    tableUI.getTableDisplay(List.of(responseDto));
                }
                case 3 -> {
                    System.out.print("[+] Enter product uuid: ");
                    String uuid = scanner.nextLine().trim();
                    System.out.print("[+] Enter new product name: ");
                    String name = scanner.nextLine().trim();
                    UpdateProductDto updateProductDto = new UpdateProductDto(name);
                    ProductResponseDto responseDto = productController.updateProductByUuid(uuid, updateProductDto);
                    tableUI.getTableDisplay(List.of(responseDto));
                }
                case 4 -> {
                    System.out.print("[+] Enter product uuid: ");
                    String uuid = scanner.nextLine().trim();
                    try{
                        ProductResponseDto responseDto = productController.getProductByUuid(uuid);
                        tableUI.getTableDisplay(List.of(responseDto));
                    }catch (NullPointerException e){
                        System.err.println("[+] Product not found");
                    }

                }
                case 5 -> {
                    System.out.print("[+] Enter product uuid: ");
                    String uuid = scanner.nextLine().trim();
                    int status = productController.deleteProductByUuid(uuid);
                    if(status > 0){
                        System.out.println("[+] Product deleted successfully");
                    }
                }
                case 6 -> {
                    return;
                }
            }
            System.out.print("[+] Any key to continue..."); scanner.nextLine();
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
        ║  4. Search product             ║
        ║  5. Delete product             ║
        ║  6. Exit                       ║
        ╚════════════════════════════════╝""");
        int choice = 0;
        do {
            System.out.print("[+] Enter choice: ");
            try{
                choice = Integer.parseInt(scanner.nextLine());
            }catch(NumberFormatException ignored){
                choice = 0;
            }

        }while (choice < 1 || choice >6 );
        return choice;
    }
}
