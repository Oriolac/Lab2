public class ProjectManager extends Employee{

    private int idProject;

    public ProjectManager(String name, String nif, String email, int idProject) {
        super(name, nif, email);
        this.idProject = idProject;
    }

    public ProjectManager(String name, String nif, String email){
        super(name, nif, email);
    }

    public ProjectManager(String name, String nif, int idProject){
        super(name, nif);
        this.idProject = idProject;
    }

    public ProjectManager(String name, String nif){
        super(name, nif);
    }

    @Override
    public boolean equals(Object obj) {
        try{
            ProjectManager eng = (ProjectManager) obj;
            return this.getNif().equals(eng.getNif()) && this.getEmail().equals(eng.getEmail()) && this.getName().equals(eng.getName());
        } catch(ClassCastException e){
            return false;
        }
    }

    public void setIdProject(int idProject){
        this.idProject = idProject;
    }

    public int getIdProject(int idProject){
        return idProject;
    }
}
