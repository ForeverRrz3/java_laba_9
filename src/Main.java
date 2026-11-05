import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        HashMap<Integer, SchoolClass> classes = new HashMap<>();
        HashMap<String, ArrayList<Schoolchild>> subjects = new HashMap<>();

        Scanner file = new Scanner(new File("data_school.txt"), "UTF-8");
        while (file.hasNext()) {
            String lastname = file.next();
            String name = file.next();
            int number = file.nextInt();
            String subject = file.next();
            int grade = file.nextInt();
            Schoolchild student = new Schoolchild(lastname, name, subject, grade);

            if (!classes.containsKey(number)){
                classes.put(number, new SchoolClass(number));
            }
            classes.get(number).addStudent(student);
            if (!subjects.containsKey(subject)){
                subjects.put(subject, new ArrayList<>());
            }
            subjects.get(subject).add(student);
        }
        file.close();


        for (SchoolClass schoolClass : classes.values()) {
            PrintWriter writer = new PrintWriter("class_" + schoolClass.getNumber() + ".txt", "UTF-8");
            System.out.println("Класс " + schoolClass.getNumber());
            writer.println("Класс " + schoolClass.getNumber());
            for (Schoolchild student : schoolClass.getStudents()) {
                System.out.println(student);
                writer.println(student);
            }
            writer.close();
        }

        Scanner input = new Scanner(System.in);


        System.out.print("Введите оценку: ");
        int grade = input.nextInt();
        for (SchoolClass schoolClass : classes.values()) {
            System.out.println("Класс " + schoolClass.getNumber());
            for (Schoolchild student : schoolClass.getStudents()) {
                if (student.getSubject().getGrade() == grade) {
                    System.out.println(student);
                }
            }
        }


        System.out.println("Классы по успеваемости:");
        ArrayList<SchoolClass> sortedClasses = new ArrayList<>(classes.values());
        Collections.sort(sortedClasses);
        for (SchoolClass schoolClass : sortedClasses) {
            System.out.println(schoolClass.getNumber() + " класс: " + schoolClass.getAverageGrade());
        }

        
        System.out.print("Введите предмет: ");
        String subject = input.next();
        ArrayList<Schoolchild> students = subjects.get(subject);
        if (students == null) {
            System.out.println("Предмет не найден");
        } else {
            Collections.sort(students);
            for (Schoolchild student : students){
                System.out.println(student);
            }
        }


        System.out.print("Введите номер класса: ");
        int number = input.nextInt();
        SchoolClass schoolClass = classes.get(number);
        if (schoolClass == null) {
            System.out.println("Класс не найден");
        } else {
            PrintWriter writer = new PrintWriter("reports_" + number + ".txt", "UTF-8");
            for (String subjectName : subjects.keySet()) {
                writer.println("Предмет: " + subjectName);
                for (Schoolchild student : schoolClass.getStudents()) {
                    if (student.getSubject().getName().equals(subjectName)){
                        writer.println(student);
                    }
                }
                writer.println();
            }
            writer.close();
            System.out.println("Ведомости записаны в reports_" + number + ".txt");
        }


        System.out.print("Введите фамилию и имя: ");
        String lastname = input.next();
        String name = input.next();
        boolean found = false;
        for (SchoolClass currentClass : classes.values()) {
            for (Schoolchild student : currentClass.getStudents()) {
                if (student.getLastname().equalsIgnoreCase(lastname) && student.getName().equalsIgnoreCase(name)) {
                    System.out.println("Класс: " + currentClass.getNumber());
                    found = true;
                    break;
                }
            }
            if (found){
                break;
            }
        }
        if (!found){
            System.out.println("Ученик не найден");
        }


        double bestAverage = -1;
        ArrayList<String> bestSubjects = new ArrayList<>();
        for (String subjectName : subjects.keySet()) {
            double sum = 0;
            for (Schoolchild student : subjects.get(subjectName)){
                sum += student.getSubject().getGrade();
            }
            double average = sum / subjects.get(subjectName).size();
            if (average > bestAverage) {
                bestAverage = average;
                bestSubjects.clear();
            }
            if (average == bestAverage) {
                bestSubjects.add(subjectName);
            }
        }
        for (String best : bestSubjects) {
            System.out.println("Лучший предмет: " + best + " - " + bestAverage);
        }
        input.close();
     }
}
