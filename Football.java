import java.util.Random;
import java.util.Date;

class CoordinateGenerator {
    private Random randomGenerator;
    
    public CoordinateGenerator() {
        Date now = new Date();
        long sec = now.getTime();
        randomGenerator = new Random(sec);
    }
    
    public int generateX() {
        int x = randomGenerator.nextInt(101);
        if(x < 5) {
            x = 0;
        } else if(x > 95) {
            x = 100;
        } else {
            x = randomGenerator.nextInt(99) + 1;
        }
        return x;
    }
    
    public int generateY() {
        int y = randomGenerator.nextInt(101);
        if(y < 5) {
            y = 0;
        } else if(y > 95) {
            y = 50;
        } else {
            y = randomGenerator.nextInt(49) + 1;
        }
        return y;
    }
}

class Out extends Exception {
    public Out(String mesaj) {
        super(mesaj);
    }
}

class Corner extends Exception {
    public Corner(String mesaj) {
        super(mesaj);
    }
}

class Gol extends Exception {
    public Gol(String mesaj) {
        super(mesaj);
    }
}

class Minge {
    private int x,y;
    private static final CoordinateGenerator cg = new CoordinateGenerator();
    
    public Minge(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public void suteaza() throws Out, Gol, Corner {
        this.x = cg.generateX();
        this.y = cg.generateY();
        
        if(this.y == 0 || this.y == 50) {
            throw new Out("Out!\n");
        } else if((this.x == 0 || this.x == 100) && (this.y >= 20 && this.y <= 30)) {
            throw new Gol("Gol!\n");
        } else if((this.x == 0 || this.x == 100) && ((this.y >= 0 && this.y <=20) || (this.y >= 30 && this.y <= 50))) {
            throw new Corner("Corner!\n");
        }
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
}

class Joc {
    private String echipa1, echipa2;
    private int nrGoluriEchipa1 = 0, nrGoluriEchipa2 = 0, nrOut = 0, nrCorner = 0;
    
    public Joc(String echipa1, String echipa2) {
        this.echipa1 = echipa1;
        this.echipa2 = echipa2;
    }
    
    public String getEchipa1() {
        return this.echipa1;
    }
    
    public String getEchipa2() {
        return this.echipa2;
    }
    
    public void simuleaza() {
        Minge minge = new Minge(50,25);
        
        for(int i = 0; i < 1000; i++) {
            try {
                minge.suteaza();
                System.out.println("\nMinge(" + minge.getX() + "," + minge.getY() + ")");
            } catch (Out out) {
                minge = new Minge(minge.getX(),minge.getY());
                nrOut++;
            } catch(Gol gol) {
                if(minge.getX() == 0) {
                    nrGoluriEchipa2++;
                } else {
                    nrGoluriEchipa1++;
                }
                minge = new Minge(50,25);
                System.out.println(this.nrGoluriEchipa1 + " - " + this.nrGoluriEchipa2);
            } catch(Corner corner){
                if(minge.getY() < 20) {
                    minge = new Minge(minge.getX(),0);
                } else {
                    minge = new Minge(minge.getX(),50);
                }
                nrCorner++;
            }
        }
    }
    
    public String toString() {
        String rezultat = "\nFinal\n";
        rezultat = rezultat + this.echipa1 + " " + this.nrGoluriEchipa1 + " - " + this.nrGoluriEchipa2 + " " + this.echipa2;
        rezultat = rezultat + "\nNumar Out: " + this.nrOut + "\nNumar Corner: " + this.nrCorner;
        return rezultat;
    }   
}

public class Football{
    public static void main(String[] args) {
        Joc joc1 = new Joc("Romania","Islanda");
        joc1.simuleaza();
        System.out.println(joc1);
    }
}


































