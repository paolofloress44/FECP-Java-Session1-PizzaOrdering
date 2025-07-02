package org.example;



import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static ArrayList<String> pizzas = new ArrayList<>();
    private static ArrayList<Integer> quantities = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("1. Add Order \n2. Update Order\n3. Remove Order\n4. View Orders\n5. Exit");
        int choice;
        do{
            System.out.print("Choose option: ");
            choice = getUserChoice();
            switch(choice){
                case 1:
                    handleAddOrder();
                    break;
                case 2:
                    handleUpdateOrder();
                    break;
                case 3:
                    handleRemoveOrder();
                    break;
                case 4:
                    printOrders(pizzas, quantities);
                    break;
                case 5:
                    System.out.println("---Thank you!---");
                    break;
                default:
                    System.out.println("Invalid option");
            }
        } while (choice != 5);
        sc.close();
    }
    private static int getUserChoice(){
        while(!sc.hasNextInt()){
            System.out.println("Invalid input");
            sc.next();
            System.out.println("Choose option: ");
        }
        int choice = sc.nextInt();
        sc.nextLine();
        return choice;
    }
    private static void handleAddOrder(){
        System.out.print("Pizza Type: ");
        String pizzaType = sc.nextLine();
        int quantity = -1;
        while (quantity <= 0){
            System.out.print("Quantity: ");
            while(!sc.hasNextInt()){
                System.out.println("Invalid input.");
                sc.next();
                System.out.print("Quantity: ");
            }
            quantity = sc.nextInt();
            sc.nextLine();
            if (quantity <= 0){
                System.out.println("Invalid input.");
            }
        }
        addOrder(pizzas, quantities, pizzaType, quantity);
    }
    private static void handleUpdateOrder(){
        if(pizzas.isEmpty()){
            System.out.println("No orders to update.");
            return;
        }
        printOrders(pizzas, quantities);
        System.out.print("Order number to update: ");
        int orderNumberToUpdate = getUserChoice();
        int index = orderNumberToUpdate - 1;

        if(index >= 0 && index < pizzas.size()){
            System.out.print("New quantity: ");
            int newQuantity = -1;
            while (newQuantity <= 0){
                while(!sc.hasNextInt()){
                    System.out.println("Invalid input");
                    sc.next();
                    System.out.println("New quantity");
                }
                newQuantity = sc.nextInt();
                sc.nextLine();
                if(newQuantity <= 0){
                    System.out.println("Invalid input");
                }
            }
            updateOrder(quantities, index, newQuantity);
        } else {
            System.out.println("Invalid order number");
        }
    }
    private static void handleRemoveOrder(){
        if (pizzas.isEmpty()){
            System.out.println("No orders to remove.");
            return;
        }
        printOrders(pizzas, quantities);
        System.out.println("Order Number to remove: ");
        int orderNumberToRemove = getUserChoice();
        int index = orderNumberToRemove - 1;
        removeOrder(pizzas, quantities, index);
    }
    public static void addOrder(ArrayList<String> pizzas, ArrayList<Integer> quantities, String pizzaType, int quantity){
        if (quantity<=0){
            System.out.println("Invalid Input. Must be positive");
            return;
        }
        pizzas.add(pizzaType);
        quantities.add(quantity);
    }
    public static void updateOrder(ArrayList<Integer> quantities, int index, int newQuantity){
        if (index >= 0 && index < quantities.size()) {
            if (newQuantity <= 0) {
                System.out.println("Error: New quantity must be positive. Order not updated.");
                return;
            }
            quantities.set(index, newQuantity);
        } else {
            System.out.println("Error: Invalid index for update. Order not updated.");
        }
    }
    public static void removeOrder(ArrayList<String> pizzas, ArrayList<Integer> quantities, int index){
        if(index>=0 && index<pizzas.size()){
            pizzas.remove(index);
            quantities.remove(index);
        }else{
            System.out.println("Invalid index for removal. Order not removed.");
        }
    }
    public static void printOrders(ArrayList<String> pizzas, ArrayList<Integer> quantities){
        System.out.println("--- Current Orders ---");
        if(pizzas.isEmpty()){
            System.out.println("No orders placed yet.");
        }else{
            for(int i=0;i<pizzas.size();i++){
                System.out.println((i + 1) + ". " + pizzas.get(i) + " x " + quantities.get(i));
            }
        }
    }
}