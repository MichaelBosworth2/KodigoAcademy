public class Grades {
    public static void main(String[] args) {
        GradesOperations gradesOper = new GradesOperations();
        gradesOper.createFile();
        gradesOper.gradeInput();
        gradesOper.readGrades();
    }
}
