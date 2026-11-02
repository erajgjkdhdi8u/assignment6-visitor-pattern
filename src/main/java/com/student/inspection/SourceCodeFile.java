package com.student.inspection;
public class SourceCodeFile implements ProjectElement {
 private final String name; private final int lines; private final int complexity; private final boolean hardcodedPassword;
 public SourceCodeFile(String name,int lines,int complexity,boolean hardcodedPassword){
  if(lines<0 || complexity<0) throw new IllegalArgumentException("Negative metrics");
  this.name=name;this.lines=lines;this.complexity=complexity;this.hardcodedPassword=hardcodedPassword;
 }
 public String getName(){return name;} public int getLines(){return lines;}
 public int getComplexity(){return complexity;} public boolean hasHardcodedPassword(){return hardcodedPassword;}
 @Override public void accept(ProjectVisitor visitor){visitor.visit(this);}
}
