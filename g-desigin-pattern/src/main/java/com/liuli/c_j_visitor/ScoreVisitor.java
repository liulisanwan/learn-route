package com.liuli.c_j_visitor;

import java.util.Optional;

public interface ScoreVisitor {

    void visit(Student student);
}

// 学生访问者，不同的访问者对待同一个事情的反应是不同的
class StudentVisitor implements  ScoreVisitor{
    @Override
    public void visit(Student student) {
        Optional.ofNullable(student).ifPresent(s->{
            if(s.getScore()!=null && s.getScore()>=60){
                System.out.println("学生：及格了好开心，回家不用挨打了");
            }else{
                System.out.println("学生：没及格，回家挨打");
            }
        });
    }
}

// 老师访问者
class TeacherVisitor implements ScoreVisitor{
    @Override
    public void visit(Student student) {
        Optional.ofNullable(student).ifPresent(s->{
            if(s.getScore()!=null && s.getScore()>=60){
                System.out.println("老师：及格了，希望能考个好大学，给我送个红包");
            }else{
                System.out.println("老师：废物一个，下次给他安排旮旯角");
            }
        });
    }
}

// 父母访问者
class ParentsVisitor implements ScoreVisitor{
    @Override
    public void visit(Student student) {
        Optional.ofNullable(student).ifPresent(s->{
            if(s.getScore()!=null && s.getScore()>=60){
                System.out.println("父母：及格了，希望孩子有个好的未来");
            }else{
                System.out.println("父母：得想想怎么帮助他了");
            }
        });
    }
}