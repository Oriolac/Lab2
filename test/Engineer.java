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

    @Override
    public boolean equals(Object obj) {
        try{
            Engineer eng = (Engineer) obj;
            return this.getNif().equals(eng.getNif()) && this.getEmail().equals(eng.getEmail()) && this.getName().equals(eng.getName()) && this.getNumDept() == eng.getNumDept();
        } catch(ClassCastException e){
            return false;
        }
    }

    public int getNumDept(){
        return this.numDept;
    }

    public void setNumDept(int numDept){
        this.numDept = numDept;
    }
}
