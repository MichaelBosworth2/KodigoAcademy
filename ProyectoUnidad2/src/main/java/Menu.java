import java.util.Scanner;

public class Menu {
    private Scanner scan = new Scanner(System.in);
    private Subject math = new Subject("math");
    private Subject history = new Subject("history");
    private Subject grammar = new Subject("grammar");
    private Subject subOpt;
    private String subject = "No file selected";

    public void mainMenu() {
        String option;
        String intro = """
                
                Please use your keyboard to make a selection. Input 0 to exit.\s
                --------------------------------------------------------------\s
                1: Load File (Subject).\s
                2: Add Student.\s
                3: Generate and Send Reports.""";
        label:
        while (true) {
            String activeSub = "\nSelected Subject: " + subject.toUpperCase();
            System.out.println(activeSub);
            System.out.println(intro);
            option = scan.next();
            switch (option) {
                case "0":
                    break label;
                case "1":
                    subPrompt();
                    break;
                case "2":
                    addPrompt();
                    break;
                case "3":
                    if (subOpt==null) {
                        System.out.println("No file has been selected yet!");
                    } else {
                        subOpt.getStats();
                    }
                    break;
            }
        }
    }

    private void subPrompt() {
        String option;
        String select = """
                
                Select the subject you would like to load.\s
                1: Math.\s
                2: History.\s
                3: Grammar.""";
        System.out.println(select);
        option = scan.next();
        switch (option) {
            case "1" -> {
                subOpt = math;
                subject = math.getSubject();
                System.out.println("math.txt has been selected");
            }
            case "2" -> {
                subOpt = history;
                subject = history.getSubject();
                System.out.println("history.txt has been selected");
            }
            case "3" -> {
                subOpt = grammar;
                subject = grammar.getSubject();
                System.out.println("grammar.txt has been selected");
            }
        }
    }

    private void addPrompt() {
        String option;
        String select = """
                
                Select your method of input.\s
                1: Keyboard\s
                2: File Read""";
        label:
        while (true) {
            System.out.println(select);
            option = scan.next();
            switch (option) {
                case "0":
                    break label;
                case "1":
                    if (subOpt==null) {
                        System.out.println("No subject has been selected yet!");
                    } else {
                        studentPrompt(subOpt);
                    }
                    break label;
                case "2":
                    if (subOpt==null) {
                        System.out.println("No subject has been selected yet!");
                    } else {
                        subOpt.loadFile();
                    }
                    break label;
            }
        }
    }

    private void studentPrompt(Subject subject) {
        Student temp;
        String name;
        int grade;

        System.out.println("Please provide the student's name");
        name = scan.next();
        System.out.println("Provide " + name +"'s grade");
        grade = scan.nextInt();
        temp = new Student(name, grade);
        subject.addStudent(temp);
    }
}
