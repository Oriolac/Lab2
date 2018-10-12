public class Employee {
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

    @Override
    public boolean equals(Object obj){
        if(obj instanceof Employee){
            Employee emp = (Employee) obj;
            return this.name.equals(emp.getName()) && this.nif.equals(emp.getNif()) && this.email.equals(emp.getEmail());
        } else {
            return false;
        }
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

}
