package com.student.inspection;
public class Main {
 public static Directory sampleProject(){
  Directory root=new Directory("fintech-project");Directory src=new Directory("src");Directory tests=new Directory("tests");Directory config=new Directory("config");
  src.add(new SourceCodeFile("PaymentService.java",120,8,false));src.add(new SourceCodeFile("UserService.java",90,12,true));
  tests.add(new TestFile("PaymentServiceTest.java",65,"PaymentService"));
  config.add(new ConfigurationFile("application.properties",true,false));
  DependencyFile pom=new DependencyFile("pom.xml");pom.addDependency("spring-core","6.2.1",false);pom.addDependency("legacy-helper","1.0",true);
  root.add(src);root.add(tests);root.add(config);root.add(pom);return root;
 }
 public static void main(String[] args){
  Directory project=sampleProject();
  SecurityAuditVisitor security=new SecurityAuditVisitor();ProjectStatisticsVisitor stats=new ProjectStatisticsVisitor();
  QualityGateVisitor quality=new QualityGateVisitor();ComplianceVisitor compliance=new ComplianceVisitor();DocumentationVisitor docs=new DocumentationVisitor();
  project.accept(security);project.accept(stats);project.accept(quality);project.accept(compliance);project.accept(docs);
  System.out.println("=== SECURITY ===\n"+security.report());System.out.println("=== STATISTICS ===\n"+stats.report());
  System.out.println("=== QUALITY ===\n"+quality.report());System.out.println("=== COMPLIANCE ===\n"+compliance.report());
  System.out.println("=== DOCUMENTATION ===\n"+docs.report());
 }
}
