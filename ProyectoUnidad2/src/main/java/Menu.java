import java.util.Scanner;

/* Class with multiple menus */
public class Menu {
    private final Scanner scan = new Scanner(System.in);
    private final Subject math = new Subject("math");
    private final Subject history = new Subject("history");
    private final Subject grammar = new Subject("grammar");
    private Subject subjectHolder;
    private Student temp;
    private String subject = "No file selected";

    /* Main Menu */
    public void mainMenu() {
        String option;
        String intro = """
                
                Please use your keyboard to make a selection. Input 0 to exit.\s
                --------------------------------------------------------------\s
                1: Load File (Subject).\s
                2: Add Student.\s
                3: Generate and Send Reports.\s
                0: Exit.""";
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
                    subjectPrompt();
                    break;
                case "2":
                    addPrompt();
                    break;
                case "3":
                    if (subjectHolder ==null) {
                        System.out.println("No file has been selected yet!");
                    } else {
                        subjectHolder.getStats();
                        subjectHolder.getPdf();
                        EmailUtil eu = new EmailUtil();
                        eu.email();
                    }
                    break;
            }
        }
    }

    /* Subject selection menu */
    private void subjectPrompt() {
        String option;
        String select = """
                
                Select the subject you would like to load.\s
                1: Math.\s
                2: History.\s
                3: Grammar.\s
                0: Return""";
        System.out.println(select);
        option = scan.next();
        switch (option) {
            case "1" -> {
                subjectHolder = math;
                subjectHolder.loadFile();
                subject = math.getSubject();
            }
            case "2" -> {
                subjectHolder = history;
                subjectHolder.loadFile();
                subject = history.getSubject();
            }
            case "3" -> {
                subjectHolder = grammar;
                subjectHolder.loadFile();
                subject = grammar.getSubject();
            }
        }
    }

    /* Adding Student Menu */
    private void addPrompt() {
        String option;
        String select = """
                
                Select your method of input.\s
                1: Keyboard\s
                2: File Read\s
                0: Return.""";
        label:
        while (true) {
            System.out.println(select);
            option = scan.next();
            switch (option) {
                case "0":
                    break label;
                case "1":
                    if (subjectHolder ==null) {
                        System.out.println("No subject has been selected yet!");
                    } else {
                        studentPrompt(subjectHolder);
                    }
                    break label;
                case "2":
                    if (subjectHolder ==null) {
                        System.out.println("No subject has been selected yet!");
                    } else {
                        temp = new Student("temp", 1);
                        temp.addStudentFile(subjectHolder.getSubject());
                    }
                    break label;
            }
        }
    }

    /* Student input */
    private void studentPrompt(Subject subject) {
        String name;
        double grade;

        System.out.println("Please provide the student's name");
        name = scan.next();
        System.out.println("Provide " + name +"'s grade");
        grade = scan.nextDouble();
        if (!(grade < 0 || grade > 10)) {
            temp = new Student(name, grade);
            temp.addStudent(subject.getSubject());
        } else {
            System.out.println("Grade must be between 0 and 10, please try again.");
        }
    }
}