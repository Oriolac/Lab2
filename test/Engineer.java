public class Engineer extends Employee {
    private int numDept;

    public Engineer(String name, String nif, int numDept) {
        super(name, nif);
        this.numDept = numDept;
    }

    public Engineer(String name, String nif){
        super(name,nif);
    }

    public Engineer(String name, String nif, String email, int numDept){
        super(name,nif,email);
        this.numDept = numDept;
    }

    public Engineer(String name, String nif, String email){
        super(name, nif, email);
    }

    public int getNumDept(){
        return this.numDept;
    }

    public void setNumDept(int numDept){
        this.numDept = numDept;
    }
}
