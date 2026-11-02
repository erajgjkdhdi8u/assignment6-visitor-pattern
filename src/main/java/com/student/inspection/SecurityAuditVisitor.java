package com.student.inspection;
import java.util.ArrayList;import java.util.List;
public class SecurityAuditVisitor implements ProjectVisitor {
 private final List<String> issues=new ArrayList<>();
 public List<String> getIssues(){return List.copyOf(issues);} public int getCriticalIssues(){return issues.size();}
 public int getScore(){return Math.max(0,100-issues.size()*20);}
 public void visit(SourceCodeFile f){if(f.hasHardcodedPassword())issues.add(f.getName()+": hardcoded password");if(f.getComplexity()>20)issues.add(f.getName()+": high-risk complexity");}
 public void visit(TestFile f){} public void visit(ConfigurationFile f){if(f.isInsecure())issues.add(f.getName()+": insecure settings");if(f.hasExposedCredentials())issues.add(f.getName()+": exposed credentials");}
 public void visit(DependencyFile f){for(var d:f.getDependencies())if(d.vulnerable())issues.add(d.name()+": vulnerable dependency");}
 public void visit(Directory d){}
 public String report(){return "Security score: "+getScore()+"/100; issues: "+issues;}
}
