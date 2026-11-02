package com.student.inspection;
public class ProjectStatisticsVisitor implements ProjectVisitor {
 private int files,lines,tests,sources,dependencies,complexitySum;
 public int getTotalFiles(){return files;} public int getLines(){return lines;} public int getTests(){return tests;} public int getSources(){return sources;}
 public int getDependencies(){return dependencies;}
 public double getAverageComplexity(){return sources==0?0:(double)complexitySum/sources;}
 public double getTestSourceRatio(){return sources==0?0:(double)tests/sources;}
 public void visit(SourceCodeFile f){files++;sources++;lines+=f.getLines();complexitySum+=f.getComplexity();}
 public void visit(TestFile f){files++;tests++;lines+=f.getLines();}
 public void visit(ConfigurationFile f){files++;}
 public void visit(DependencyFile f){files++;dependencies+=f.getDependencies().size();}
 public void visit(Directory d){}
 public String report(){return String.format(java.util.Locale.US,"Files: %d, LOC: %d, tests: %d, dependencies: %d, avg complexity: %.2f, test/source: %.2f",files,lines,tests,dependencies,getAverageComplexity(),getTestSourceRatio());}
}
