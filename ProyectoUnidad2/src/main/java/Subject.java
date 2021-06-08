import com.aspose.words.Document;
import com.aspose.words.SaveFormat;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Subject {

    private final String subject;

    public Subject(String subject) {
        this.subject = subject;
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

    public void createFile() {
        try {
            File file = new File("src/main/resources/Report.txt");
            BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/resources/Report.txt"));
            if (file.createNewFile()) {
                System.out.println("Generating report...");
            }
            bw.append("michael.bosworth2@gmail.com");
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
        createFile();
        ArrayList<Student> tempList = new ArrayList<>();
        String[] tempSub = {"MATH", "HISTORY", "GRAMMAR"};
        try {
            for (String sub : tempSub) {
                Scanner scan = new Scanner(new FileReader("src/main/resources/" + sub + ".txt"));
                BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/resources/Report.txt", true));
                bw.newLine();
                bw.append(sub).append(" STATISTICS");
                bw.newLine();
                while (scan.hasNext()) {
                    Student tempStu = new Student(scan.next(), scan.nextInt());
                    bw.append(tempStu.getName()).append(" ").append(String.valueOf(tempStu.getGrade()));
                    bw.newLine();
                    tempList.add(tempStu);
                }
                bw.append("===================================");
                bw.newLine();
                bw.close();
                getMin(tempList);
                getMax(tempList);
                getAvg(tempList);
                getPopular(tempList);

                tempList.clear();
            }
        } catch (IOException e) {
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
        String message;

        for (Student student : studentList) {
            gradeSum = gradeSum + student.getGrade();
            count++;
        }
        gradeAvg = gradeSum / count;
        message = "Average grade is: " + gradeAvg;
        System.out.println(message);
        writeOnFile(message);
    }

    private void getPopular(List<Student> studentList) {
        int count = 0;
        int tempCount;
        int popular = studentList.get(0).getGrade();
        int temp;

        for (int i = 0; i < studentList.size(); i++) {
            temp = studentList.get(i).getGrade();
            tempCount = 0;
            for (Student n : studentList) {
                if (temp == n.getGrade()) {
                    tempCount++;
                }
                if (tempCount > count) {
                    popular = temp;
                    count = tempCount;
                }
            }
        }
        System.out.println("The most popular grade is: " + popular + " and is repeated " + count + " time(s).");
        getRepeated(studentList, popular, "Popular");
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

    public void getPdf() {
        Document document;
        try {
            document = new Document("src/main/resources/Report.txt");
            document.save("src/main/resources/Report.pdf", SaveFormat.PDF);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}