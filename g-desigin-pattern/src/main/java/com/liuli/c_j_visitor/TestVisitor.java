package com.liuli.c_j_visitor;

/**
 * @author pcc
 */
public class TestVisitor {
    public static void main(String[] args) {
        Student student = new Student();
        student.setScore(59); // 假设考了59分

        StudentVisitor studentVisitor = new StudentVisitor();
        TeacherVisitor teacherVisitor = new TeacherVisitor();
        ParentsVisitor parentsVisitor = new ParentsVisitor();

        studentVisitor.visit(student);
        teacherVisitor.visit(student);
        parentsVisitor.visit(student);
    }
}