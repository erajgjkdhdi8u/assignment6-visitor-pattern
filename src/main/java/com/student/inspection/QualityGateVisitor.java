package com.student.inspection;
public class QualityGateVisitor implements ProjectVisitor {
 private final ProjectStatisticsVisitor stats=new ProjectStatisticsVisitor();private final SecurityAuditVisitor security=new SecurityAuditVisitor();
 public void visit(SourceCodeFile f){stats.visit(f);security.visit(f);} public void visit(TestFile f){stats.visit(f);}
 public void visit(ConfigurationFile f){stats.visit(f);security.visit(f);} public void visit(DependencyFile f){stats.visit(f);security.visit(f);} public void visit(Directory d){}
 public boolean securityPass(){return security.getCriticalIssues()==0;}
 public boolean complexityPass(){return stats.getAverageComplexity()<=10;}
 public boolean testsPass(){return stats.getTestSourceRatio()>=0.40;}
 public boolean isPassed(){return securityPass()&&complexityPass()&&testsPass();}
 private String state(boolean pass){return pass?"PASS":"FAIL";}
 public String report(){return "Security: "+state(securityPass())+"\nComplexity: "+state(complexityPass())+"\nTests: "+state(testsPass())+"\nFINAL RESULT: "+(isPassed()?"PASSED":"FAILED");}
}
