import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GradesOperations {
    private final Map<String, Integer> studentList = new HashMap<>();   // Creates an instance of Map class to hold name (key) and grade (value)
    private final Scanner scanner = new Scanner(System.in);

    /* Creates a new text file */
    public void createFile() {
        try {
            File myFile = new File("StudentScores.txt");
            System.out.println("Creating file...");
            if (myFile.createNewFile()) {
                System.out.println("File created: " + myFile.getName()); // If the file is created, print this
            } else {
                System.out.println("File already exists");
            }
            System.out.println();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /* Save user input data to file */
    public void saveListOnFile() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("StudentScores.txt"));
            for (Map.Entry<String, Integer> me : studentList.entrySet()) {  // Iterates over the Map studentList, and saves the key and value to file.
                bw.write(me.getKey() + ", " + me.getValue());
                bw.newLine();
            }
            bw.newLine();
            bw.close();
            System.out.println("List successfully updated!");
        } catch (IOException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }

    /* Appends a string to the file without writing over the data */
    public void writeOnFile(String result) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("StudentScores.txt", true));  // By making appending true, we can add data to file safely
            bw.append(result);
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }

    /* User input for students and grades data */
    /* ONLY ACCEPTS ONE STRING PER STUDENT'S NAME */
    public void gradeInput() {
        System.out.println("Please enter the first name of the student, followed by the grade");
        for (int i = 0; i < 10; i++) {
            System.out.print("Student's name:");
            String sName = scanner.next().trim();
            System.out.print(sName + "'s grade:");
            int sGrade = scanner.nextInt();
            studentList.put(sName, sGrade); // Add student's name and grade to the Map studentList
        }
        System.out.println();
        saveListOnFile();   // We save the information to file by calling this method
        System.out.println();
    }

    /* Read file and iterates every line for the grade Integer */
    public void readGrades() {
        try {
            Scanner input = new Scanner(new FileReader("StudentScores.txt"));
            ArrayList<Integer> tempList = new ArrayList<>();

            while (input.hasNext()) {
                input.next().chars().filter(Character::isDigit);    // Filter everything but integers
                tempList.add(input.nextInt());
            }
            getMin(tempList);   // Call this method to obtain the min grade
            getMax(tempList);   // Call this method to obtain the max grade
            getAvg(tempList);   // Call this method to obtain the avg grade
            mostRepeated(tempList); // Call this method to obtain the most repeated grade
            leastRepeated(tempList);    // Call this method to obtain the least repeated grade

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /* Find the lowest grade on the list */
    public void getMin(ArrayList<Integer> sList) {
        int minGrade = 10;
        for (int tg : sList) {
            if (minGrade > tg) {
                minGrade = tg;
            }
        }
        System.out.println("Min grade is: " + minGrade);
        writeOnFile("Min grade is: " + minGrade);
    }

    /* Find the highest grade on the list */
    public void getMax(ArrayList<Integer> sList) {
        int maxGrade = 0;
        for (int tg : sList) {
            if (maxGrade < tg) {
                maxGrade = tg;
            }
        }
        System.out.println("Max grade is: " + maxGrade);
        writeOnFile("Max grade is: " + maxGrade);
    }

    /* Find the average grade of all the grades on the list */
    public void getAvg(ArrayList<Integer> sList) {
        double gradeSum = 0.0;
        double gradeAve;
        int gTimes = 0;

        for (double tg : sList) {
            gradeSum = gradeSum + tg;
            gTimes++;
        }
        gradeAve = gradeSum / gTimes;
        System.out.println("Average grade is: " + gradeAve);
        writeOnFile("Average grade is: " + gradeAve);
    }

    /* Find the most repeated grade on file */
    public void mostRepeated(ArrayList<Integer> sList) {
        int count = 0;
        int tempCount;
        int popular = sList.get(0);
        int temp;

        for (int i = 0; i < sList.size(); i++) {
            temp = sList.get(i);
            tempCount = 0;
            for (int num : sList) {
                if (temp == num) {
                    tempCount++;
                }
                if (tempCount > count) {
                    popular = temp;
                    count = tempCount;
                }
            }
        }
        System.out.println("The most popular grade is: " + popular + " and is repeated " + count + " time(s).");
        writeOnFile("The most popular grade is: " + popular + " and is repeated " + count + " time(s).");
    }

    /* Find the most repeated grade on file */
    public void leastRepeated(ArrayList<Integer> sList) {
        int count = 10;
        int tempCount;
        int unpopular = sList.get(0);
        int temp;

        for (int i = 0; i < sList.size(); i++) {
            temp = sList.get(i);
            tempCount = 0;
            for (Integer integer : sList) {
                if (temp == integer) {
                    tempCount++;
                }
            }
            if (tempCount <= count) {
                unpopular = temp;
                count = tempCount;
            }
        }
        System.out.println("The least popular grade is: " + unpopular + " and is repeated " + count + " time(s).");
        writeOnFile("The least popular grade is: " + unpopular + " and is repeated " + count + " time(s).");
    }
}