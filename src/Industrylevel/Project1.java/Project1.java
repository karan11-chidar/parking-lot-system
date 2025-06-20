package Industrylevel;

import java.time.Duration;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

interface Payment {
    void payment(double ammount);
}
class UPI implements Payment {
    public void payment (double ammount){
        System.out.println("Paid For UPI : "+ammount+"INR");
    }
}
class CreditCard implements Payment {
     public void payment(double ammount){
         System.out.println("Paid For CreditCard : "+ammount+"INR");
     }
}
class DebitCard implements Payment {
    public void payment(double ammount){
        System.out.println("Paid For DebitCard : "+ammount+"INR");
    }
}
class Cash implements Payment {
    public void payment (double ammount){
        System.out.println("Paid For Cash : "+ammount+"INR");
    }
}
class PaymentThrough{
   private Payment payment;
    PaymentThrough(Payment payment){
        this.payment = payment;
    }
    public void payment(double ammount){
        payment.payment(ammount);
    }
}
abstract class Vehicle4{
    private String numberplate ;
    private String type;
    Vehicle4(String numberplate, String type){
        this.numberplate = numberplate;
        this.type = type;
    }
    public void setNumberplate(String numberplate) {
        this.numberplate = numberplate;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumberplate() {
        return numberplate;
    }

    public String getType() {
        return type;
    }
}
class Car extends Vehicle4 {
    Car(String numberPlate){
        super(numberPlate,"Car");
    }
    Car(){
        super("Unknown","Car");
    }
}
class Bike extends Vehicle4{
    Bike(String numberPlate){
        super(numberPlate,"Bike");
    }
    Bike(){
        super("Unknown","Bike");
    }
}
class Truck extends Vehicle4 {
    Truck(String numberPlate){
        super(numberPlate,"Truck");
    }
    Truck(){
        super("Unknwon","Truck");
    }
}
class ParkingSpot {
    private int spotNumber;
    private boolean isOccupied;
    private String vehicleType;
    private Vehicle4 parkedVehicle;
    private Ticket ticket;
    ParkingSpot (int spotNumber,String vehicleType){
        this.spotNumber = spotNumber;
        this.vehicleType = vehicleType;
        this.isOccupied = false;
        this.parkedVehicle = null;
    }
    public void parkVehicle(Vehicle4 vehicle) {
        if (isOccupied) {
            System.out.println("\n Spot already occupied.");
        } else if (!vehicle.getType().equals(vehicleType)) {
            System.out.println("This spot is for " + vehicleType + " only.");
        } else {
            this.parkedVehicle = vehicle;
            this.isOccupied = true;
            String tiketId = "TKT-1"+UUID.randomUUID().toString().split("-")[0];
            this.ticket = new Ticket(tiketId,vehicle,spotNumber);
            System.out.println("Vehicle parked at spot " + spotNumber);
            ticket.printTicket();
        }
    }
    void UnparkVehicle (){
        if(isOccupied==false){
            System.out.println("\nSpot Already Empty");
        }
        else{
            if(isOccupied==true){
                ticket.Exit();
                ticket.printFinalBill();
            }
            parkedVehicle = null;
            isOccupied = false;
            System.out.println("Vehicle removed from spot "+spotNumber);
        }
    }
}
class Ticket {
    private String ticketId ;
    private Vehicle4 vehicleId;
    private int spotNumber;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private long duration;
    private double charges;
    private Payment paymentMethod;
    Ticket(String ticketId,Vehicle4 vehicleId,int spotNumber){
        this.ticketId = ticketId;
        this.vehicleId = vehicleId;
        this.spotNumber = spotNumber;
        this.entryTime = LocalDateTime.now();
    }
    public void printTicket() {
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = entryTime.format(myFormatObj);
        System.out.println("---- Parking Ticket ----");
        System.out.println("Ticket ID   : " + ticketId);
        System.out.println("Vehicle No  : " + vehicleId.getNumberplate());
        System.out.println("Vehicle Type: " + vehicleId.getType());
        System.out.println("Spot Number : " + spotNumber);
        System.out.println("Entry Time  : " + formattedDate);
    }
   public void Exit(){
        this.exitTime = LocalDateTime.now();
        this.duration = Duration.between(entryTime,exitTime).toHours();
        this.charges = duration * 10;
    }
    public void printFinalBill() {
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        System.out.println("\n---- Exit Receipt ----");
        System.out.println("Ticket ID    : " + ticketId);
        System.out.println("Vehicle No   : " + vehicleId.getNumberplate());
        System.out.println("Entry Time   : " + entryTime.format(formatter));
        System.out.println("Exit Time    : " + exitTime.format(formatter));
        System.out.println("Duration     : " + duration + " minutes");
        System.out.println("Total Charge : ₹" + this.charges);
        System.out.println("\n Chosie Payment Option :");
        int choise;
            System.out.println("For UPI :- 1");
            System.out.println("For CreditCard :- 2");
            System.out.println("For DebitCard :- 3");
            System.out.println("For Cash :- 4");
            System.out.println("Press Vaild Option :- ");
            choise = sc.nextInt();
            switch (choise) {
                case 1: {
                    paymentMethod = new UPI();
                    break;
                }
                case 2: {
                    paymentMethod = new CreditCard();
                    break;
                }
                case 3: {
                    paymentMethod = new DebitCard();
                    break;
                }
                case 4: {
                    paymentMethod = new Cash();
                    break;
                }
                default: {
                    System.out.println("Invalid choice. Defaulting to Cash.");
                    paymentMethod = new Cash();
                    break;
                }
            }
            PaymentThrough paymentThrough = new PaymentThrough(paymentMethod);
            paymentThrough.payment(charges);
            System.out.println("✅ Payment successful for Ticket ID: " + ticketId);
    }
    }
public class Project1 {
    public static void main(String[] args) {
    ParkingSpot spot1 = new ParkingSpot(101,"Car");
    Vehicle4 car = new Car("MP04-ZB-2004");
    spot1.parkVehicle(car);
    spot1.UnparkVehicle();
    Vehicle4 car2 = new Car("MP-04 ZV-3802");
    spot1.parkVehicle(car2);
    }
}