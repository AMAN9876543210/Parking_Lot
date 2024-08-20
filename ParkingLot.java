// this is the version we at the very early stage thought of and we regret it (lol) because of the rant/crap at the bottom.
//In the later versions we implemented core OOPs concepts that had helped us a lot and increased our sanity.
//Apparently inexecutable for some reason.


import java.util.*;
import java.time.*;
class Customer{
    private String name;
    private String phNum;
    private int customerID;
    public Instant inTime;
    private int slotNum;
    private int floorNum;
    Vehicle vehicle = new Vehicle();
    public String getName() {
        return name;
    }
    public String getPhNum() {
        return phNum;
    }
    public int getCustomerID() {
        return customerID;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPhNum(String phNum) {
        this.phNum = phNum;
    }
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
    public int getSlotNum() {
        return slotNum;
    }
    public void setSlotNum(int slotNum) {
        this.slotNum = slotNum;
    }
    public int getFloorNum() {
        return floorNum;
    }
    public void setFloorNum(int floorNum) {
        this.floorNum = floorNum;
    }
    void printTicket(Instant inTime){
        this.inTime = inTime;
        System.out.println("__________Your Ticket__________");
        System.out.println("Name: " + getName() + "\nPhone Number: " + getPhNum());
        System.out.println("CustomererId: " + getCustomerID());
        System.out.println("VehicleID: " + vehicle.getVehicleID());
        if(vehicle.getVehicleType() == 1){
            System.out.println("VehicleType: MotorCycle");
        }
        System.out.println("Park your vehicle at SlotNum: " + getSlotNum() + " in floorNum: " + getFloorNum());
        System.out.println("Thank You..\n\n");
    }
}
class Vehicle{
        private int vehicleType;
        private String vehicleID;
        private double rate;
        public String getVehicleID() {
            return vehicleID;
        }
        public int getVehicleType() {
            return vehicleType;
        }
        public void setVehicleID(String vehicleID) {
            this.vehicleID = vehicleID;
        }
        public void setVehicleType(int vehicleType) {
            this.vehicleType = vehicleType;
        }
        public double getRate() {
            return rate;
        }
        public void setRate(double rate) {
            this.rate = rate;
        }
}
class Floor{
    boolean[] slots = new boolean[100];
    int availability(int start, int end){
        for(int i = start; i <= end; i++){
            if(!slots[i]){
                slots[i] = true;
                return i;
            }
        }
        return -1;
    }
}
public class ParkingLot {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Floor floor0 = new Floor();
        Floor floor1 = new Floor();
        Floor floor2 = new Floor();
        Floor floor3 = new Floor();
        Customer[] customers = new Customer[300];
        for(int i = 0; i < 300; i++){
            customers[i] = new Customer();
        }
        int i = 0;
        while(true){
            System.out.println("Welcome");
            System.out.println("Enter your name");
            String name = sc.next();
            customers[i].setName(name);
            System.out.println("Enter your phNum");
            String phNum = sc.next();
            customers[i].setPhNum(phNum);
            System.out.println("Enter your VehicleID");
            String vehicleID = sc.next();
            customers[i].vehicle.setVehicleID(vehicleID);
            customers[i].setCustomerID(i+1);
            System.out.println("Choose your vehicleType:\n1.MotorCycle\n2.Car\n3.ElectricCar\n4.smallTruck\n5.bigTruck\n6.Handicapped");
            int vehicleType = sc.nextInt();
            customers[i].vehicle.setVehicleType(vehicleType);
            System.out.println("Would you like your vehicle to have a wash?\n1.Yes\n2.No");
            int ans1 = sc.nextInt();
            int ans2 = 0;
            if(vehicleType == 3){
                System.out.println("Would you like to charge your vehicle?\n1.Yes\n2.No");
                ans2 = sc.nextInt();
            }
            if(vehicleType == 6){
                //floor0
                if(floor0.availability(0, 49) != -1){
                    customers[i].setSlotNum(floor0.availability(0, 49));
                    customers[i].setFloorNum(0);
                }
            }
            if(vehicleType == 3){
                //floor3
                if(floor3.availability(0, 99) != -1){
                    customers[i].setSlotNum(floor3.availability(0, 99));
                    customers[i].setFloorNum(3);
                }
            }
            if(vehicleType == 4){
                //floor1
                if(floor1.availability(0, 49) != -1){
                    customers[i].setSlotNum(floor1.availability(0, 49));
                    customers[i].setFloorNum(1);
                }
            }
            if(vehicleType == 5){
                //floor0
                if(floor0.availability(50, 99) != -1){
                    customers[i].setSlotNum(floor0.availability(50, 99));
                    customers[i].setFloorNum(0);
                }
            }
            if(vehicleType == 1){
                //floor1 or floor2
                if(floor1.availability(50, 99) != -1){
                    customers[i].setSlotNum(floor1.availability(50, 99));
                    customers[i].setFloorNum(1);
                }else if(floor2.availability(50, 99) != -1){
                    customers[i].setSlotNum(floor2.availability(50, 99));
                    customers[i].setFloorNum(2);
                }
            }
            if(vehicleType == 2){
                //floor2
                if(floor2.availability(0, 49) != -1){
                    customers[i].setSlotNum(floor2.availability(0, 49));
                    customers[i].setFloorNum(2);
                }
            }
            customers[i].printTicket(Instant.now());
            i++;
        }
    }
}
