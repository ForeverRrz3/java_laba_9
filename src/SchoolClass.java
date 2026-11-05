import java.util.ArrayList;
import java.util.List;

public class SchoolClass implements Comparable<SchoolClass>{
    private int number;
    private ArrayList<Schoolchild> students = new ArrayList<>();

    public SchoolClass(int number) {
        this.number = number;
    }

    public void addStudent(Schoolchild student) {
        students.add(student);
    }

    public int getNumber() {
        return number;
    }

    public ArrayList<Schoolchild> getStudents() {
        return students;
    }

    public double getAverageGrade() {
        if (students.isEmpty()){
            throw new IllegalStateException("В классе нет учеников");
        }
        double sum = 0;
        for (Schoolchild child : students){
            sum += child.getSubject().getGrade();
        }
        return sum/students.size();
    }

    @Override
    public int compareTo(SchoolClass other){
        return Double.compare(other.getAverageGrade(), getAverageGrade());
    }
}
