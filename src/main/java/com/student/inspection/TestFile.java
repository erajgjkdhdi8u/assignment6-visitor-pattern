package com.student.inspection;
public class TestFile implements ProjectElement {
 private final String name; private final int lines; private final String testedClass;
 public TestFile(String name,int lines,String testedClass){if(lines<0)throw new IllegalArgumentException("Negative lines");this.name=name;this.lines=lines;this.testedClass=testedClass;}
 public String getName(){return name;} public int getLines(){return lines;} public String getTestedClass(){return testedClass;}
 @Override public void accept(ProjectVisitor visitor){visitor.visit(this);}
}
