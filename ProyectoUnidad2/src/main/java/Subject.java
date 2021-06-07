import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Subject {

    private String subject;

    public Subject(String name) {
        this.subject = name;
    }

    public String getSubject() {
        return subject;
    }

    public void loadFile() {
        try {
            Scanner scan = new Scanner(new FileReader("src/main/resources/studentList.txt"));
            while (scan.hasNext()) {
                Student tempStu = new Student(scan.next(), scan.nextInt());
                addStudent(tempStu);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addStudent(Student student) {
        StringBuilder sb = new StringBuilder();
        sb.append(student.getName()).append(" was successfully added to the ").append(subject).append(".txt file");
        String file = "src/main/resources/" + subject + ".txt";
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
            bw.append(student.getName()).append(" ").append(String.valueOf(student.getGrade()));
            bw.newLine();
            bw.close();
            System.out.println(sb);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void getStats() {
        ArrayList<Student> tempList = new ArrayList<>();
        String[] tempSub = {"MATH", "HISTORY", "GRAMMAR"};
        try {
            for (String sub : tempSub) {
                Scanner scan = new Scanner(new FileReader(sub + ".txt"));
                while (scan.hasNext()) {
                    Student tempStu = new Student(scan.next(), scan.nextInt());
                    tempList.add(tempStu);
                }
                System.out.println("===============================");
                System.out.println(sub + " statistics:".toUpperCase());
                getMin(tempList);
                getMax(tempList);
                getAvg(tempList);
//                getPopular(tempList);
                tempList.clear();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void getMin(List<Student> studentList) {
        int minGrade = 10;
        for (Student student : studentList) {
            if (minGrade > student.getGrade()) {
                minGrade = student.getGrade();
            }
        }
        getRepeated(studentList, minGrade, "Min");
    }

    private void getMax(List<Student> studentList) {
        int maxGrade = 0;
        for (Student student : studentList) {
            if (maxGrade < student.getGrade()) {
                maxGrade = student.getGrade();
            }
        }
        getRepeated(studentList, maxGrade, "Max");
    }

    private void getAvg(List<Student> studentList) {
        double gradeSum = 0.0;
        double gradeAvg;
        int count = 0;

        for (Student student : studentList) {
            gradeSum = gradeSum + student.getGrade();
            count++;
        }
        gradeAvg = gradeSum / count;
        System.out.println("Average grade is: " + gradeAvg);
    }

    private void getRepeated(List<Student> list, int grade, String target) {
        ArrayList<String> names = new ArrayList<>();
        for (Student n : list) {
            if (n.getGrade() == grade) {
                names.add(n.getName());
            }
        }
        System.out.println(target + " grade is " + grade + ". And it was the score of " + names.size() + " students: ");
        for (String n : names) {
            System.out.println(n);
        }
    }

}
