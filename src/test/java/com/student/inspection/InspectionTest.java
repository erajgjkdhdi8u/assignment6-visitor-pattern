package com.student.inspection;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class InspectionTest {
 private Directory passing(){Directory d=new Directory("ok");d.add(new SourceCodeFile("Pay.java",20,5,false));d.add(new TestFile("PayTest.java",8,"Pay"));d.add(new ConfigurationFile("app.properties",false,false));return d;}
 @Test void sourceCount(){ProjectStatisticsVisitor v=new ProjectStatisticsVisitor();new SourceCodeFile("A.java",10,3,false).accept(v);assertEquals(1,v.getSources());}
 @Test void testCount(){ProjectStatisticsVisitor v=new ProjectStatisticsVisitor();new TestFile("ATest.java",7,"A").accept(v);assertEquals(1,v.getTests());}
 @Test void configCount(){ProjectStatisticsVisitor v=new ProjectStatisticsVisitor();new ConfigurationFile("app",false,false).accept(v);assertEquals(1,v.getTotalFiles());}
 @Test void dependencyCount(){DependencyFile f=new DependencyFile("pom.xml");f.addDependency("lib","1",false);ProjectStatisticsVisitor v=new ProjectStatisticsVisitor();f.accept(v);assertEquals(1,v.getDependencies());}
 @Test void detectsPassword(){SecurityAuditVisitor v=new SecurityAuditVisitor();new SourceCodeFile("A.java",10,5,true).accept(v);assertEquals(1,v.getCriticalIssues());}
 @Test void detectsVulnerability(){DependencyFile f=new DependencyFile("pom.xml");f.addDependency("old","1",true);SecurityAuditVisitor v=new SecurityAuditVisitor();f.accept(v);assertEquals(80,v.getScore());}
 @Test void qualityPass(){QualityGateVisitor v=new QualityGateVisitor();passing().accept(v);assertTrue(v.isPassed());}
 @Test void qualityFailTests(){QualityGateVisitor v=new QualityGateVisitor();new SourceCodeFile("A.java",10,5,false).accept(v);assertFalse(v.testsPass());}
 @Test void qualityFailComplexity(){QualityGateVisitor v=new QualityGateVisitor();new SourceCodeFile("A.java",10,15,false).accept(v);assertFalse(v.complexityPass());}
 @Test void complianceMissingVersion(){DependencyFile f=new DependencyFile("pom.xml");f.addDependency("lib","",false);ComplianceVisitor v=new ComplianceVisitor();f.accept(v);assertFalse(v.isCompliant());}
 @Test void complianceMissingTests(){ComplianceVisitor v=new ComplianceVisitor();new SourceCodeFile("A.java",1,1,false).accept(v);assertTrue(v.getViolations().stream().anyMatch(x->x.contains("no tests")));}
 @Test void complianceProhibited(){DependencyFile f=new DependencyFile("pom.xml");f.addDependency("bad-library","1",false);ComplianceVisitor v=new ComplianceVisitor();f.accept(v);assertFalse(v.isCompliant());}
 @Test void nestedDirectories(){Directory root=new Directory("root");Directory child=new Directory("inner");child.add(new SourceCodeFile("A.java",30,3,false));root.add(child);ProjectStatisticsVisitor v=new ProjectStatisticsVisitor();root.accept(v);assertEquals(30,v.getLines());}
 @Test void emptyDirectory(){ProjectStatisticsVisitor v=new ProjectStatisticsVisitor();new Directory("empty").accept(v);assertEquals(0,v.getTotalFiles());assertEquals(0,v.getAverageComplexity());}
 @Test void additionalVisitor(){DocumentationVisitor v=new DocumentationVisitor();new SourceCodeFile("A.java",2,2,false).accept(v);assertEquals(1,v.getUndocumented().size());}
 @Test void negativeLineEdgeCase(){assertThrows(IllegalArgumentException.class,()->new TestFile("Bad",-2,"A"));}
 @Test void zeroSourceRatio(){ProjectStatisticsVisitor v=new ProjectStatisticsVisitor();new TestFile("T",5,"X").accept(v);assertEquals(0,v.getTestSourceRatio());}
 @Test void compliancePassing(){ComplianceVisitor v=new ComplianceVisitor();passing().accept(v);assertTrue(v.isCompliant());}
 @Test void rejectSelfDirectory(){Directory d=new Directory("x");assertThrows(IllegalArgumentException.class,()->d.add(d));}
}
