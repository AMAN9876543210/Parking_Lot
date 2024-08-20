//This is our final parking lot version which is improved both conceptually and physically (lol).
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.time.*;

class Admin {
    //private int adminID;
    private final String password = "admin@123";
    public boolean validity(String password){
        if(this.password.compareTo(password) == 0){
            System.out.println("\n\n_____Successfully logged in_____\n\n");
            return true;
        }else{
            try {
                Thread.sleep(2000);
            }catch(InterruptedException e){
                Thread.currentThread().interrupt();
            }
            System.out.println("!!!!!Incorrect password, Please try again!!!!!\n\n\n");
            return false;
        }
    }
}

class ParkingVehicle {
    private String vehicleType;
    private String vehicleSize;
    private int vehicleSlotNo;
    private int tokenID;
    private int floorNo;
    private int rate;
    
    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public int getVehicleSlotNo() {
        return vehicleSlotNo;
    }

    public void setVehicleSlotNo(int vehicleNo) {
        this.vehicleSlotNo = vehicleNo;
    }

    public int getTokenID() {
        return tokenID;
    }

    public void setTokenID(int userId) {
        this.tokenID = userId;
    }

    public String getVehicleSize() {
        return vehicleSize;
    }

    public void setVehicleSize(String vehicleSize) {
        this.vehicleSize = vehicleSize;
    }

    public int getFloorNo() {
        return floorNo;
    }

    public void setFloorNo(int floorNo) {
        this.floorNo = floorNo;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    Instant inTime;
    Instant outTime;
    // Used to take the current time from System

    void printTicket(String name, String phNum){
        //Displays the User's ticket on Screen
        System.out.println("__________Your Ticket__________");
        try {
            Thread.sleep(1000);
        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }
        System.out.println("Name: " + name + "\nPhone Number: " + phNum);
        try {
            Thread.sleep(1000);
        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }
        System.out.println("Vehicle: " + getVehicleType() + "\nVehicle Size: " + getVehicleSize());
        try {
            Thread.sleep(1000);
        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }
        System.out.println("Vehicle parked in floor: " + getFloorNo());
        try {
            Thread.sleep(1000);
        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }
        System.out.println("UserId: " + getTokenID());
        try {
            Thread.sleep(1000);
        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }
        System.out.println("Vehicle Slot: " + this.getVehicleSlotNo());
        try {
            Thread.sleep(1000);
        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }
        System.out.println("* * * Thank You * * *\n\n\n");
    }
}


class Floor {
    boolean[] flr= new boolean[100];
    //A boolean array to determine whether the particular slot in the floor is empty or not
    // int availability(int size, int start, int end){
    //     int availability;
    //     availability = checkAvailability(size, start, end);
    //     return availability;
    // }

    int availability(int size, int start, int end){
        //Checks for availability of User's vehicle in the given floor and returns the position of empty slot
        if(size==1){
            for(int j=start; j<=end; j++){
                if(!flr[j]){
                    return j;
                }
            }
        }
        if(size==2) {
            for(int j=start; j<end; j++) {
                if (!flr[j] && !flr[j + 1]) {
                    return j;
                }
                if(j==end-1){
                    return -1;
                }
            }
        }
        return -1;
    }
    void SlotHandler(int j, int size, int option){
        if(option == 1){
            SlotAllocation(j, size);
        }else{
            SlotDeallocation(j, size);
        }
    }

    private void SlotAllocation(int j, int size) {
        // Changes the boolean array's elements to true for occupied slots
        flr[j]=true;
        if(size==2) flr[j+1]=true;
    }

    private void SlotDeallocation(int j, int size) {
        // Changes the boolean array's elements to false for emptied slots
        flr[j]=false;
        if(size==2) flr[j+1]=false;
    }
}


abstract class Vehicle {
    //Defines start index and end index of each vehicle in floor's boolean array
    //Defines amount of each vehicle in respective inherited class
    int startIndex;
    int endIndex;
    int amount;
}

class Car extends Vehicle{
     Car() {
        startIndex= 40;
        endIndex= 79;
        amount=60;
    }
}
class ElectricCar extends Vehicle{

    ElectricCar() {
        startIndex= 80;
        endIndex= 99;
        amount=80;
    }
    boolean avail;
    boolean Charge;
}
class Truck extends Vehicle{

    Truck() {
        startIndex= 0;
        endIndex= 39;
        amount=100;
    }
}
class MotorCycle extends Vehicle{

    MotorCycle() {
        startIndex= 0;
        endIndex= 39;
        amount=30;
    }
}
class Handicapped extends Vehicle{

    Handicapped() {
        startIndex= 40;
        endIndex= 79;
        amount=20;
    }
}
interface payment {
    public void calculateAmount(int rate, Instant inTime, Instant outTime);
}
class CashPayment implements payment{
    @Override
    public void calculateAmount(int rate,Instant inTime, Instant outTime){
        long time =Duration.between(inTime,outTime).toMinutes()+1;
        System.out.println("\nTotal Duration of Parking- "+ time+ " hours");
        System.out.println("Total Payable Amount = "+ (double)time*rate + " Rupees");
        System.out.println("Kindly pay cash at Exit-1 to attendant");
        System.out.println("* * * Thank You!! please visit again * * *\n\n\n");
    }
}

class CreditCardPayment implements payment{
    @Override
    public void calculateAmount(int rate, Instant inTime, Instant outTime){
        Scanner sc = new Scanner(System.in);
        long time =Duration.between(inTime,outTime).toMinutes()+1;
        System.out.println("\nTotal Duration of Parking- "+ time+ " hours");
        System.out.println("Total Payable Amount = "+ (double)time*rate + " Rupees");
        System.out.println("Please insert your card");
        try {
            Thread.sleep(2000);
        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }
        System.out.println("Enter PIN: ");
        int pin = sc.nextInt();
        try {
            Thread.sleep(2000);
        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }
        System.out.println("Congrats!! Payment Successful");
        System.out.println("* * * Thank You!! please visit again * * *\n\n\n");
    }
}
class NetBanking implements payment{
    @Override
    public void calculateAmount(int rate, Instant inTime, Instant outTime){
        Scanner sc = new Scanner(System.in);
        long time =Duration.between(inTime,outTime).toMinutes()+1;
        System.out.println("\nTotal Duration of Parking- "+ time+ " hours");
        System.out.println("Total Payable Amount = "+ (double)time*rate + " Rupees");
        System.out.println("Enter Bank Name: ");
        String bankName= sc.nextLine();
        System.out.println("\nDirecting to Payment Page...\n");
        try {
            Thread.sleep(2000);
        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }
        System.out.println("Enter UserID of the bank: ");
        String userID= sc.next();
        System.out.println("Enter Password: ");
        String password= sc.next();
        try {
            Thread.sleep(2000);
        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }
        System.out.println("Payment Successful");
        System.out.println("Kindly exit at 2nd Gate");
        System.out.println("* * * Thank You!! please visit again * * *\n\n\n");
    }
}
class UPI implements payment{
    @Override
    public void calculateAmount(int rate, Instant inTime, Instant outTime){
        long time =Duration.between(inTime,outTime).toMinutes()+1;
        System.out.println("\nTotal Duration of Parking- "+ time+ " hours");
        System.out.println("Total Payable Amount = "+ (double)time*rate + " Rupees");
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter UPI ID: ");
        String upiId= sc.next();
        System.out.println("Enter OTP: ");
        int otp= sc.nextInt();
        System.out.println("Enter UPI PIN: ");
        int pin= sc.nextInt();
        try {
            Thread.sleep(2000);
        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }
        System.out.println("Payment Successful\n");
        System.out.println("Kindly exit at 2nd Gate");
        System.out.println("Thank You..!!!\n\n\n");
    }
}

public class FinalParkingLot {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        boolean[] parkingData= new boolean[300]; //boolean array to check if the particular index of array is empty
        ParkingVehicle[] parkingVehicle= new ParkingVehicle [300];
        for(int i=0; i<300; i++){
            parkingVehicle[i]= new ParkingVehicle();
        }
        //array of user class to store the users' data
        Admin admin = new Admin();

        Floor floor1= new Floor();
        Floor floor2= new Floor();
        Floor floor3= new Floor();

        Car car= new Car();
        MotorCycle motorCycle= new MotorCycle();
        Truck truck= new Truck();
        ElectricCar electricCar= new ElectricCar();
        Handicapped handicapped= new Handicapped();

        int x=0;
        while(true){
            System.out.println("______WELCOME TO TOWN-SQUARE PARKING GARAGE!!!_____\n\nChoose the category\n1.User\n2.Admin");
            int category = sc.nextInt();
            if(category == 1){
                System.out.println("Select the action you want to perform-\n1.Park Vehicle\n2.Departure");
                int action= sc.nextInt();
                if(action==1){
                    for(int i=0; i<300; i++){
                        if(!parkingData[i]){
                            parkingData[i]=true;
                            x=i; //assigning index in array for user
                            break;
                        }
                    }
                    parkingVehicle[x].setTokenID(x+1);
                    System.out.println("Enter your Details-\nName: ");
                    String name = sc.next();
                    System.out.println("Phone Number: ");
                    String phNum = sc.next();
                    System.out.println("Select your vehicle type-\n1.Motor Cycle\n2.Car\n3.Truck\n4.Electrical Car\n5.Handicapped Bike");
                    int n= sc.nextInt();
                    int startIndex=0,endIndex=0;
                    if(n==1){
                        startIndex= motorCycle.startIndex;
                        endIndex=motorCycle.endIndex;
                        parkingVehicle[x].setRate(motorCycle.amount);
                        parkingVehicle[x].setVehicleType("Motor Cycle");
                    }
                    if(n==2) {
                        startIndex= car.startIndex;
                        endIndex= car.endIndex;
                        parkingVehicle[x].setRate(car.amount);
                        parkingVehicle[x].setVehicleType("Car");
                    }
                    if(n==3) {
                        startIndex= truck.startIndex;
                        endIndex=truck.endIndex;
                        parkingVehicle[x].setRate(truck.amount);
                        parkingVehicle[x].setVehicleType("Truck");
                    }
                    if(n==4) {
                        startIndex= electricCar.startIndex;
                        endIndex=electricCar.endIndex;
                        parkingVehicle[x].setVehicleType("ElectricCar");
                        if(electricCar.avail){
                            System.out.println("Do you want to charge your Car?\n1.Yes\n2.No");
                            int temp= sc.nextInt();
                            if(temp==1) {
                                electricCar.Charge= true;
                                parkingVehicle[x].setRate(electricCar.amount);
                            }
                            else {
                                electricCar.Charge= false;
                                parkingVehicle[x].setRate(electricCar.amount-30);
                            }
                        }else{
                            System.out.println("For your kind information, charging facility is currently unavailable");
                            parkingVehicle[x].setRate(electricCar.amount-30);
                        }
                    }
                    if(n==5) {
                        startIndex= handicapped.startIndex;
                        endIndex=handicapped.endIndex;
                        parkingVehicle[x].setRate(handicapped.amount);
                        parkingVehicle[x].setVehicleType("Handicapped");
                    }
                    int size = 0;boolean _avail = true;
                    if(n != 1 && n != 5){
                        System.out.println("Enter the size of vehicle: \n1.Small\n2.Large");
                        size = sc.nextInt();
                        if (size == 1) parkingVehicle[x].setVehicleSize("Small");
                        if (size == 2) parkingVehicle[x].setVehicleSize("Large");
                    }else{
                        parkingVehicle[x].setVehicleSize("Small");
                        size = 1;
                    }
                    if(n==3 || n==4 || n==5){
                        //trucks and handicapped in Floor-1
                        if(floor1.availability(size, startIndex, endIndex)!=-1) System.out.println("Slots are available in Floor 1");
                        else {System.out.println("Slots are unavailable");_avail = false;}
                    }
                    if(n==1 || n==2 || n==4){
                        //bikes, cars in Floor-2 and Floor-3
                        //electric cars in every floor
                        if(floor2.availability(size, startIndex, endIndex)!=-1) System.out.println("Slots are available in Floor 2");
                        if(floor3.availability(size, startIndex, endIndex)!=-1) System.out.println("Slots are available in Floor 3");
                        else {System.out.println("Slots are unavailable");_avail = false;}
                    }
                    if(_avail){
                        System.out.println("Enter the floor you want to choose: ");
                        int f=sc.nextInt();
                        parkingVehicle[x].setFloorNo(f);
                        if(f==1 && (n==3 || n==5)){
                            parkingVehicle[x].setVehicleSlotNo(floor1.availability(size,startIndex,endIndex));
                            floor1.SlotHandler(parkingVehicle[x].getVehicleSlotNo(),size, 1);
                        }
                        else if(f==2 && n!=3 && n!=5){
                            parkingVehicle[x].setVehicleSlotNo(floor2.availability(size,startIndex,endIndex));
                            floor2.SlotHandler(parkingVehicle[x].getVehicleSlotNo(),size, 1);
                        }
                        else if(f==3 && n!=3 && n!=5){
                            parkingVehicle[x].setVehicleSlotNo(floor3.availability(size,startIndex,endIndex));
                            floor3.SlotHandler(parkingVehicle[x].getVehicleSlotNo(),size, 1);
                        }

                        parkingVehicle[x].inTime= Instant.now();
                        parkingVehicle[x].printTicket(name, phNum);
                    }   
                }

                else if(action==2){
                    payment pay1 = new CashPayment();
                    payment pay2 = new CreditCardPayment();
                    payment pay3 = new NetBanking();
                    payment pay4 = new UPI();
                    System.out.println("Enter your UserId: ");
                    int k= sc.nextInt();
                    k--;
                    int rate = parkingVehicle[k].getRate();
                    System.out.println("Choose the payment gateway you want to use\n1.Cash payment\n2.Credit card payment\n3.Costumer Info Portal");
                    int input =sc.nextInt();
                    parkingVehicle[k].outTime= Instant.now();
                    if(input == 1) pay1.calculateAmount(rate, parkingVehicle[k].inTime, parkingVehicle[k].outTime);
                    else if(input == 2) pay2.calculateAmount(rate, parkingVehicle[k].inTime, parkingVehicle[k].outTime);
                    else if(input == 3){
                        System.out.println("Choose an option:\n1.NetBanking\n2.UPI payment");
                        int option = sc.nextInt();
                        if(option == 1) pay3.calculateAmount(rate, parkingVehicle[k].inTime, parkingVehicle[k].outTime);
                        else pay4.calculateAmount(rate, parkingVehicle[k].inTime, parkingVehicle[k].outTime);
                    }
                    parkingData[k]= false;
                    int tempS;
                    if(Objects.equals(parkingVehicle[k].getVehicleSize(), "Small")) tempS=1;
                    else tempS=2;

                    if(parkingVehicle[k].getFloorNo()==1) floor1.SlotHandler(parkingVehicle[k].getVehicleSlotNo(),tempS, 0);
                    else if(parkingVehicle[k].getFloorNo()==2) floor1.SlotHandler(parkingVehicle[k].getVehicleSlotNo(),tempS, 0);
                    else if(parkingVehicle[k].getFloorNo()==3) floor1.SlotHandler(parkingVehicle[k].getVehicleSlotNo(),tempS, 0);
                }
            }else{
                System.out.println("Please enter the password to proceed: ");
                String password = sc.next();
                if(admin.validity(password)){
                    System.out.println("Choose the action you want to perform:\n1.setAmounts\n2.setSlots\n3.setCharge");
                    int action = sc.nextInt();
                    if(action == 1){
                        System.out.println("Choose the vehicle you want to set the amount:\n1.MotorCycle\n2.Car\n3.Truck\n4.ElectricCar\n5.Handicapped");
                        int j = sc.nextInt();
                        System.out.println("Enter the new amount:");
                        int amount = sc.nextInt();
                        if(j == 1)motorCycle.amount = amount;
                        else if(j == 2)car.amount = amount;
                        else if(j == 3)truck.amount = amount;
                        else if(j == 4)electricCar.amount = amount;
                        else if(j == 5)handicapped.amount = amount;
                        try {
                            Thread.sleep(2000);
                        }catch(InterruptedException e){
                            Thread.currentThread().interrupt();
                        }
                        System.out.println("Amount got reset successfully\n\n\n");
                    }else if(action == 3){
                        System.out.println("Choose an option:\n1.Charging facility available\n2.Charging facility unavailable");
                        int option = sc.nextInt();
                        if(option == 1){
                            electricCar.avail = true;
                        }else if(option == 2){
                            electricCar.avail = false;
                        }
                        try {
                            Thread.sleep(2000);
                        }catch(InterruptedException e){
                            Thread.currentThread().interrupt();
                        }
                        System.out.println("Changed availability successfully\n\n\n");
                    }else if(action == 2){
                        System.out.println("Choose the vehicle you want to set the slots:\n1.MotorCycle\n2.Car\n3.Truck\n4.ElectricCar\n5.Handicapped");
                        int j = sc.nextInt();
                        if(j == 1){
                            System.out.println("Enter the new indices(make sure it lies in the range (" + motorCycle.startIndex + ", " + motorCycle.endIndex + ")): ");
                            int startIndex = sc.nextInt();
                            int endIndex = sc.nextInt();
                            motorCycle.startIndex = startIndex;
                            motorCycle.endIndex = endIndex;
                        }
                        else if(j == 2){
                            System.out.println("Enter the new indices(make sure it lies in the range (" + car.startIndex + ", " + car.endIndex + ")): ");
                            int startIndex = sc.nextInt();
                            int endIndex = sc.nextInt();
                            car.startIndex = startIndex;
                            car.endIndex = endIndex;
                        }
                        else if(j == 3){
                            System.out.println("Enter the new indices(make sure it lies in the range (" + truck.startIndex + ", " + truck.endIndex + ")): ");
                            int startIndex = sc.nextInt();
                            int endIndex = sc.nextInt();
                            truck.startIndex = startIndex;
                            truck.endIndex = endIndex;
                        }
                        else if(j == 4){
                            System.out.println("Enter the new indices(make sure it lies in the range (" + electricCar.startIndex + ", " + electricCar.endIndex + ")): ");
                            int startIndex = sc.nextInt();
                            int endIndex = sc.nextInt();
                            electricCar.startIndex = startIndex;
                            electricCar.endIndex = endIndex;
                        }
                        else if(j == 5){
                            System.out.println("Enter the new indices(make sure it lies in the range (" + handicapped.startIndex + ", " + handicapped.endIndex + ")): ");
                            int startIndex = sc.nextInt();
                            int endIndex = sc.nextInt();
                            handicapped.startIndex = startIndex;
                            handicapped.endIndex = endIndex;
                        }
                        try {
                            Thread.sleep(2000);
                        }catch(InterruptedException e){
                            Thread.currentThread().interrupt();
                        }
                        System.out.println("Indices got reset successfully\n\n\n");
                    }
                }
            }
        }
    }
}