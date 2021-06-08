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
        String target = subject + ".txt";
        File file = new File("src/main/resources/" + target);
        try {
            if (file.createNewFile()) {
                System.out.println("File not found, but was created!");
            } else {
                System.out.println(target + " has been selected");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public void createFile() {
//        try {
//            File file = new File("src/main/resources/Report.txt");
//            BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/resources/Report.txt"));
//            if (file.createNewFile()) {
//                System.out.println("Generating report...");
//                bw.append("michael.bosworth2@gmail.com");
//                bw.newLine();
//                bw.close();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public void writeOnFile(String result) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/resources/Report.txt", true));
            bw.append(result);
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }

    public void getStats() {
        ArrayList<Student> tempList = new ArrayList<>();
        String[] tempSub = {"math", "history", "grammar"};
        try {
            for (String sub : tempSub) {
                Scanner scan = new Scanner(new FileReader("src/main/resources/" + sub + ".txt"));
                while (scan.hasNext()) {
                    if (!scan.nextLine().contains("@gmail.com")) {
                        Student tempStu = new Student(scan.next(), scan.nextInt());
                        tempList.add(tempStu);
                    }
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
        String message;
        for (Student n : list) {
            if (n.getGrade() == grade) {
                names.add(n.getName());
            }
        }
        message = target + " grade is " + grade + ". And it was the score of " + names.size() + " students: ";
        System.out.println(message);
        writeOnFile(message);
        for (String n : names) {
            System.out.println(n);
            writeOnFile(n);
        }
    }

}
