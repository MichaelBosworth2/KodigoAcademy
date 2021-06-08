import java.io.*;
import java.util.Scanner;

public class Student {

    private String name;
    private int grade;

    public Student(String name, int grade) {
        this.name = name;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public int getGrade() {
        return grade;
    }

    public void addStudent(String subject) {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(" was successfully added to the ").append(subject).append(".txt file");
        String file = "src/main/resources/" + subject + ".txt";
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
            bw.append(name).append(" ").append(String.valueOf(grade));
            bw.newLine();
            bw.close();
            System.out.println(sb);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void addStudentFile(String subject) {
        Student student;
        try {
            Scanner scan = new Scanner(new FileReader("src/main/resources/studentList.txt"));
            while (scan.hasNext()) {
                student = new Student(scan.next(), scan.nextInt());
                student.addStudent(subject);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
