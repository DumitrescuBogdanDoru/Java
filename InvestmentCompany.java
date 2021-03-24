interface Risky {
    public double getRisk();
}

abstract class Proiect implements Risky {
    private String titlu, obiectiv;
    private long fonduri;
    private Manager manager;
    protected Membru[] membri = new Membru[100];
    protected int nrMembri = 0;
    protected long maxMembri;
    
    public Proiect(String titlu, String obiectiv, long fonduri, Manager manager, int maxMembri) {
        this.titlu = titlu;
        this.obiectiv = obiectiv;
        this.fonduri = fonduri;
        this.manager = manager;
        this.maxMembri = maxMembri;
    }
    
    abstract public void addMembru(Membru m);
    
    public String getTitlu() {
        return titlu;
    }
    
    public String getObiectiv() {
        return obiectiv;
    }
    
    public long getFonduri() {
        return fonduri;
    }
    
    public Manager getManager() {
        return manager;
    }
    
    public Membru[] getMembri() {
        return membri;
    }
    
    public int getNrMembri() {
        return nrMembri;
    }
    
    public long getMaxMembrii() {
        return maxMembri;
    }
    
    public String toString() {
        return "Proiect: " + this.titlu + "\ncu obiectivul:" + this.obiectiv + "\ncu managerul: " + this.manager.getNume() + "\ncu numarul de angajati: " + this.nrMembri;
    }
}

abstract class ProiectMilitarComercial extends Proiect {
    
    private String deadline;
    private static final int maxMembri = 15;
    
    public ProiectMilitarComercial(String titlu, String obiectiv, long fonduri, Manager manager, int maxMembri, String deadline) {
        super(titlu, obiectiv, fonduri, manager, ProiectMilitarComercial.maxMembri);
        this.deadline = deadline;
    }
    
    public void addMembru(Membru m) {
        if(super.nrMembri < maxMembri) {
            membri[nrMembri] = m;
            nrMembri++;
        } else {
            System.out.println("Numar maxim de membrii atins!\n");
        }
    }
}

class ProiectMilitar extends ProiectMilitarComercial {
    private String parola;
    
    public ProiectMilitar(String titlu, String obiectiv, long fonduri, Manager manager, int maxMembri, String deadline, String parola) {
        super(titlu, obiectiv, fonduri, manager, maxMembri, deadline);
        this.parola = parola;
    }
    
    public double getRisk() {
        return (double) super.getNrMembri()/this.parola.length()/super.getFonduri();
    }
}

class ProiectComercial extends ProiectMilitarComercial {
    private long fonduriMarketing;
    private int nrEchipe;
    
    public ProiectComercial(String titlu, String obiectiv, long fonduri, Manager manager, int maxMembri, String deadline, int nrEchipe){
        super(titlu, obiectiv, fonduri, manager, maxMembri, deadline);
        this.nrEchipe = nrEchipe;
        fonduriMarketing = super.getFonduri()/2;
    }
    
    public long getFonduriMarketing() {
        return fonduriMarketing;
    }
    
    public int getNrEchipe() {
        return nrEchipe;
    }
    
    public double getRisk() {
        return (double) (3*this.nrEchipe)/super.getNrMembri()/this.fonduriMarketing;
    }
}

class ProiectOpenSource extends Proiect {
    private String listaMail;
    
    public ProiectOpenSource(String titlu, String obiectiv, long fonduri, Manager manager, int maxMembri, String listaMail) {
        super(titlu, obiectiv, fonduri, manager, maxMembri);
        this.listaMail=listaMail;
    }
    
    public void addMembru(Membru m) {
        if(nrMembri < membri.length) {
            membri[nrMembri] = m;
            nrMembri++;
        } else {
            Membru[] aux = new Membru[membri.length+100];
            for(int i = 0; i < nrMembri; i++) {
                aux[i] = membri[i];
            }
            membri = aux;
            membri[nrMembri] = m;
            nrMembri++;
            super.maxMembri = membri.length;
        }
    }
    
    public double getRisk() {
        return (double) getNrMembri()/getFonduri(); 
    }
}

abstract class Programator {
    private String nume;
    private int varsta;
    
    public Programator(String nume, int varsta) {
        this.nume = nume;
        this.varsta = varsta;
    }
    
    public String getNume(){
        return nume;
    }
    
    public int getVarsta() {
        return varsta;
    }
}

class Membru extends Programator {
    public Membru(String nume, int varsta) {
        super(nume, varsta);
    }
}

class Manager extends Programator {
    public Manager(String nume, int varsta) {
        super(nume, varsta);
    }
} 

public class InvestmentCompany {
    private Proiect[] proiecte = new Proiect[100];
    private int nrProiecte = 0;
    
    public void addProiect(Proiect p) {
        if(nrProiecte < proiecte.length) {
            proiecte[nrProiecte] = p;
            nrProiecte++;
        } else {
            Proiect[] aux = new Proiect[proiecte.length+100];
            for(int i = 0; i < nrProiecte; i++) {
                aux[i] = proiecte[i];
            }
            proiecte = aux;
            proiecte[nrProiecte] = p;
            nrProiecte++;
        }
    }
    
    public Proiect getInvestitieBuna() {
        double riskMic = proiecte[0].getRisk();
        int gasit = 0;
        for(int i = 0; i < nrProiecte; i++) {
            if(riskMic < proiecte[i].getRisk()) {
                riskMic = proiecte[i].getRisk();
                gasit = i;
            }
        }
        return proiecte[gasit];
    }
    
    public static void main(String[] args) {
        InvestmentCompany ic = new InvestmentCompany();
        
        Proiect p1 = new ProiectMilitar("Avioane Invizibile", "10 avioane functionale", 14000000, new Manager("Dorel",40), 10, "10.10.2021", "avioane1234");
        p1.addMembru(new Membru("Adi",25));
        p1.addMembru(new Membru("Andrei",29));
        
        Proiect p2 = new ProiectComercial("Mancare gratis", "20 meniuri", 5000, new Manager("Bobita",35), 30, "12.12.2022", 1);
        p2.addMembru(new Membru("Bojan",32));
        p2.addMembru(new Membru("Bobo",22));
        
        Proiect p3 = new ProiectOpenSource("Open Windows10", "windows mai ieftin", 10000, new Manager("Bill",72), 10, "Mail1, Mail2");
        p3.addMembru(new Membru("Bogdan",21));
        p3.addMembru(new Membru("Cebuc",23));
        p3.addMembru(new Membru("Cecenia",50));
        
        ic.addProiect(p1);
        ic.addProiect(p2);
        ic.addProiect(p3);
        
        System.out.println(ic.getInvestitieBuna());
    }
}
