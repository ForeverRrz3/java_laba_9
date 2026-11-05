public class Schoolchild implements Comparable<Schoolchild> {
    private String lastname, name;
    private SubjectGrade subject;

    public Schoolchild(String lastname, String name, String subjectName, int grade){
        this.lastname = lastname;
        this.name = name;
        this.subject = new SubjectGrade(subjectName, grade);
    }

    public String getName() {
        return name;
    }

    public String getLastname(){
        return lastname;
    }

    public SubjectGrade getSubject() {
        return subject;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setSubject(SubjectGrade subject) {
        this.subject = subject;
    }

    @Override
    public int compareTo(Schoolchild other) {
        return lastname.compareTo(other.lastname);
    }

    @Override
    public String toString(){
        return lastname + " " + name + " | " + subject;
    }
}
