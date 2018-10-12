public class Employee implements Comparable<Employee>{
    private String name;
    private String nif;
    private String email;

    public Employee(String name, String nif){
        this.name = name;
        this.nif = nif;
    }

    public Employee(String name, String nif, String email){
        this.name = name;
        this.nif = nif;
        this.email = email;
    }


    public String getNif(){
        return this.nif;
    }

    public String getName(){
        return this.name;
    }

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    /**
     * Compares two Employee objects
     * @param o The Employee to be compared
     * @return The value 0 if the employees have the same nif;
     * a value less than 0 if this Employee has a lower than the argument Employee's nif;
     * and a value greater than 0 if this Employee has a greater nif than the argument Employee's nif.
     */
    @Override
    public int compareTo(Employee o) {
        return Integer.parseInt(this.nif) - Integer.parseInt(o.nif);
    }
}
