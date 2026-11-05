public class SubjectGrade {
    private String name;
    private int grade;

    public SubjectGrade(String name, int grade){
        this.grade = grade;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return name + " — " + grade;
    }
}
