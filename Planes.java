class Avion{
    
    protected String PlaneID;
    protected int totalEnginePower;
    
    public Avion(String PlaneID, int totalEnginePower){
        this.PlaneID=PlaneID;
        this.totalEnginePower=totalEnginePower;
    }
    
    public String getPlaneID(){
        return this.PlaneID;
    }
    
    public int getTotalEnginePower(){
        return this.totalEnginePower;
    }
    
    public void takeOff(){
        System.out.println(getPlaneID() + " - Initiating takeoff procedure - Starting engines - Accelerating down the runway - Taking off - Retracting gear - Takeoff complete");
    }
    
    public void fly(){
        System.out.println(getPlaneID() + " - Flying");
    }
    
    public void land(){
        System.out.println(getPlaneID() +" - Initiating landing procedure - Enabling airbrake - Lowering gear - Contacting runway - Decelerating - Stopping engines - Landing Complete");
    }
}

class AvionCalatori extends Avion{
    
    protected int maxPassengers;
    
    public AvionCalatori(String PlaneID, int totalEnginePower, int maxPassengers){
        super(PlaneID,totalEnginePower);
        this.maxPassengers=maxPassengers;
    }
    
    public int getMaxPassengers(){
        return this.maxPassengers;
    }
}

class Concorde extends AvionCalatori{
    
    public Concorde(String PlaneID, int totalEnginePower, int maxPassengers){
        super(PlaneID,totalEnginePower,maxPassengers);
    }
    
    public void goSuperSonic(){
        System.out.println(getPlaneID() + " - Supersonic mode activated");
    }
    
    public void goSubSonic(){
        System.out.println(getPlaneID() + " - Supersonic mode deactivated");
    }
}

class AvionLupta extends Avion{
    public AvionLupta(String PlaneID, int totalEnginePower){
        super(PlaneID,totalEnginePower);
    }
    
    public void launchMissile(){
        System.out.println(getPlaneID() + " - Initiating missile launch procedure - Acquiring target - Launching missile - Breaking away - Missile launch complete");
    }
}

class Mig extends AvionLupta{
    public Mig(String PlaneID, int totalEnginePower){
        super(PlaneID,totalEnginePower);
    }
    
    public void highSpeedGeometry(){
        System.out.println(getPlaneID() +" - High speed geometry selected");
    }
    
    public void normalGeometry(){
        System.out.println(getPlaneID() + " - Normal geometry selected");
    }
}

class TomCat extends AvionLupta{
    public TomCat(String PlaneID, int totalEnginePower){
        super(PlaneID,totalEnginePower);
    }
    
    public void refuel(){
        System.out.println(getPlaneID() + " - Initiating refueling procedure - Locating refueller - Catching up - Refueling - Refueling complete");
    }
}

class Planes{
    public static void main(String[] args){
        Avion avion, pasager, lupta, concorde, mig, tomcat;
        
        avion = new Avion("1", 1000);
        System.out.println(avion.getPlaneID() + " " + avion.getTotalEnginePower());
        avion.takeOff();
        avion.fly();
        avion.land();
        System.out.println("\n");
        
        pasager = new AvionCalatori("2",2000,200);
        System.out.println(pasager.getPlaneID() + " " + pasager.getTotalEnginePower() + " " +  ((AvionCalatori) pasager).getMaxPassengers());
        pasager.takeOff();
        pasager.fly();
        pasager.land();
        System.out.println("\n");
        
        concorde = new Concorde("3",3000,300);
        System.out.println(concorde.getPlaneID() + " " + concorde.getTotalEnginePower() + " " +  ((AvionCalatori) concorde).getMaxPassengers());
        concorde.takeOff();
        concorde.fly();
        concorde.land();
        ((Concorde) concorde).goSuperSonic();
        ((Concorde) concorde).goSubSonic();
        System.out.println("\n");
        
        lupta = new AvionLupta("4",400);
        System.out.println(lupta.getPlaneID() + " " + lupta.getTotalEnginePower());
        lupta.takeOff();
        lupta.fly();
        lupta.land();
        ((AvionLupta) lupta).launchMissile();
        System.out.println("\n");

        mig = new Mig("5", 500);
        System.out.println(mig.getPlaneID() + " " + mig.getTotalEnginePower());
        mig.takeOff();
        mig.fly();
        mig.land();
        ((AvionLupta) mig).launchMissile();
        ((Mig) mig).highSpeedGeometry();
        ((Mig) mig).normalGeometry();
        System.out.println("\n");
        
        tomcat = new TomCat("6",600);
        System.out.println(tomcat.getPlaneID() + " " + tomcat.getTotalEnginePower());
        tomcat.takeOff();
        tomcat.fly();
        tomcat.land();
        ((AvionLupta) tomcat).launchMissile();
        ((TomCat) tomcat).refuel();
    }
}


















