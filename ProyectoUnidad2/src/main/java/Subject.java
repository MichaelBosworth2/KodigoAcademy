import com.aspose.words.Document;
import com.aspose.words.SaveFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Data @AllArgsConstructor @Getter
public class Subject {

    private final String subject;

    /* Checks if subject file is created, if it is not, it created it */
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

    /* Generates a report.txt file, and adds the email address to the first line */
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

    /* Appends a string to the report.txt file */
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

    /* Generates the statistics for all subjects and appends it to the report.txt file */
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

    /* Get minimum grade from a list of Student */
    private void getMin(List<Student> studentList) {
        int minGrade = 10;
        for (Student student : studentList) {
            if (minGrade > student.getGrade()) {
                minGrade = student.getGrade();
            }
        }
        getRepeated(studentList, minGrade, "Min");
    }

    /* Get maximum grade from a list of Student */
    private void getMax(List<Student> studentList) {
        int maxGrade = 0;
        for (Student student : studentList) {
            if (maxGrade < student.getGrade()) {
                maxGrade = student.getGrade();
            }
        }
        getRepeated(studentList, maxGrade, "Max");
    }

    /* Get average grade from a list of Student */
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

    /* Get most popular grade from a list of Student */
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

    /* Iterate over a list of Student and finds all students with the grade set as parameter */
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

    /* Generate PDF file */
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