package view;

import controller.ProductController;
import controller.UserController;
import model.dto.*;
import model.enities.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class UI {
    private final static Scanner scanner = new Scanner(System.in);
    private final static ProductController productController = new ProductController();
    private final static UserController userController = new UserController();
    private final static TableUI<ProductResponseDto> tableProduct = new TableUI<>();
    private final static TableUI<UserResponseDto> tableUser = new TableUI<>();
    public static void home(){
        do {
            switch (menuProgram()){
                case 1 -> {
                    productProgram();
                }
                case 2 -> {
                    userProgram();
                }
                case 3 -> {
                    return;
                }

            }
        }while (true);
    }
    public static void productProgram(){
        do{
            switch (menuProduct()){
                case 1 -> {
                    tableProduct.getTableDisplay(productController.getAllProducts());
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
                    tableProduct.getTableDisplay(List.of(responseDto));
                }
                case 3 -> {
                    System.out.print("[+] Enter product uuid: ");
                    String uuid = scanner.nextLine().trim();
                    System.out.print("[+] Enter new product name: ");
                    String name = scanner.nextLine().trim();
                    UpdateProductDto updateProductDto = new UpdateProductDto(name);
                    ProductResponseDto responseDto = productController.updateProductByUuid(uuid, updateProductDto);
                    tableProduct.getTableDisplay(List.of(responseDto));
                }
                case 4 -> {
                    System.out.print("[+] Enter product uuid: ");
                    String uuid = scanner.nextLine().trim();
                    try{
                        ProductResponseDto responseDto = productController.getProductByUuid(uuid);
                        tableProduct.getTableDisplay(List.of(responseDto));
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

    public static void userProgram(){
        do{
            switch (menuUser()){
                case 1 -> {
                    tableUser.getTableDisplay(userController.getAllUsers());
                }
                case 2 -> {
                    System.out.print("[+] Enter username: ");
                    String name = scanner.nextLine();
                    System.out.print("[+] Enter email: ");
                    String email = scanner.nextLine();
                    System.out.print("[+] Enter password: ");
                    String password = scanner.nextLine();

                    UserCreateDto createDto = new UserCreateDto(name.trim(), email.trim(), password.trim());
                    UserResponseDto responseDto = userController.insertNewUser(createDto);
                    tableUser.getTableDisplay(List.of(responseDto));
                }
                case 3 -> {
                    System.out.print("[+] Enter user uuid: ");
                    String uuid = scanner.nextLine().trim();
                    System.out.print("[+] Enter new user username: ");
                    String name = scanner.nextLine().trim();
                    System.out.print("[+] Enter new user password: ");
                    String password = scanner.nextLine().trim();
                    UpdateUserDto updateUserDto = new UpdateUserDto(name, password);
                    UserResponseDto responseDto = userController.updateUserByUuid(uuid, updateUserDto);
                    tableUser.getTableDisplay(List.of(responseDto));
                }
                case 4 -> {
                    System.out.print("[+] Enter user uuid: ");
                    String uuid = scanner.nextLine().trim();
                    try{
                        UserResponseDto responseDto = userController.getUserByUuid(uuid);
                        tableUser.getTableDisplay(List.of(responseDto));
                    }catch (NullPointerException e){
                        System.err.println("[+] User not found");
                    }

                }
                case 5 -> {
                    System.out.print("[+] Enter user uuid: ");
                    String uuid = scanner.nextLine().trim();
                    int status = userController.deleteUserByUuid(uuid);
                    if(status > 0){
                        System.out.println("[+] User deleted successfully");
                    }
                }
                case 6 -> {
                    return;
                }
            }
            System.out.print("[+] Any key to continue..."); scanner.nextLine();
        }while(true);
    }

    public static int menuProgram(){
        System.out.println("""
        ╔════════════════════════════════╗
        ║         PROGRAM MENU           ║
        ╠════════════════════════════════╣
        ║  1. Manage Product             ║
        ║  2. Manage User                ║
        ║  3. Exit                       ║
        ╚════════════════════════════════╝""");
        return inputChoice(1, 3);
    }

    public static int menuProduct(){
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
        return inputChoice(1, 6);
    }
    public static int menuUser(){
        System.out.println("""
        ╔════════════════════════════════╗
        ║         PROGRAM MENU           ║
        ╠════════════════════════════════╣
        ║  1. Get all users              ║
        ║  2. Add new user               ║
        ║  3. Update user                ║
        ║  4. Search user                ║
        ║  5. Delete user                ║
        ║  6. Exit                       ║
        ╚════════════════════════════════╝""");
        return inputChoice(1, 6);
    }

    private static int inputChoice(int start, int end) {
        int choice = 0;
        do {
            System.out.print("[+] Enter choice: ");
            try{
                choice = Integer.parseInt(scanner.nextLine());
            }catch(NumberFormatException ignored){
                choice = 0;
            }

        }while (choice < start || choice >end );
        return choice;
    }
}
