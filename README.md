# 🚗 Parking Lot System – Java (OOPs Project)

This is a real-world Parking Lot System developed using Java and OOPs concepts.  
It handles vehicle parking, ticket generation, payment through different methods, and dynamic billing based on time.

---

## 📌 Features:
- Park & Unpark vehicles (Car, Bike, Truck)
- Unique ticket generation using UUID
- Billing based on time duration (per hour)
- Multiple payment options (UPI, Credit Card, Debit Card, Cash)
- Real-time Entry and Exit time with `LocalDateTime`

---

## 🔧 Technologies Used:
- Java
- Java OOPs (Abstraction, Inheritance, Interface, Polymorphism)
- java.time (LocalDateTime, Duration)
- Scanner (user input)

---

## 🧱 OOP Concepts Used:
- **Abstraction:** Abstract `Vehicle4` class
- **Inheritance:** `Car`, `Bike`, `Truck` classes extending `Vehicle4`
- **Interface & Polymorphism:** `Payment` interface implemented by `UPI`, `CreditCard`, etc.
- **Composition:** `PaymentThrough` uses `Payment` interface
- **Encapsulation:** Fields are private, exposed through getters/setters

---

## 🏃 How to Run:
```bash
cd parking-lot-system/src
javac Industrylevel/Project1.java
java Industrylevel.Project1


## 📸 Sample Output:
Vehicle parked at spot 101
---- Parking Ticket ----
Ticket ID : TKT-1a2b3c
Vehicle No : MP04-ZB-2004
Vehicle Type: Car
Spot Number : 101
Entry Time : 20-06-2025 15:10:32

---- Exit Receipt ----
Ticket ID : TKT-1a2b3c
Vehicle No : MP04-ZB-2004
Entry Time : 20-06-2025 15:10:32
Exit Time : 20-06-2025 17:10:32
Duration : 2 hours
Total Charge : ₹20

Choose Payment Option:

UPI

CreditCard

DebitCard

Cash

Paid For UPI : 20.0 INR
✅ Payment successful for Ticket ID: TKT-1a2b3c
