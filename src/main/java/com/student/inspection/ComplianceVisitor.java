package com.student.inspection;
import java.util.ArrayList;import java.util.List;import java.util.Set;
public class ComplianceVisitor implements ProjectVisitor {
 private static final Set<String> PROHIBITED=Set.of("bad-library","unsafe-lib");
 private final List<String> violations=new ArrayList<>();private final List<String> sources=new ArrayList<>();private final List<String> tested=new ArrayList<>();
 public List<String> getViolations(){List<String> all=new ArrayList<>(violations);for(String s:sources)if(!tested.contains(s))all.add(s+": no tests");return all;}
 public boolean isCompliant(){return getViolations().isEmpty();}
 public void visit(SourceCodeFile f){sources.add(f.getName().replace(".java",""));if(f.hasHardcodedPassword())violations.add(f.getName()+": credential in source");}
 public void visit(TestFile f){tested.add(f.getTestedClass().replace(".java",""));}
 public void visit(ConfigurationFile f){if(f.hasExposedCredentials())violations.add(f.getName()+": exposed credentials");}
 public void visit(DependencyFile f){for(var d:f.getDependencies()){if(d.version()==null||d.version().isBlank())violations.add(d.name()+": missing version");if(PROHIBITED.contains(d.name()))violations.add(d.name()+": prohibited dependency");}}
 public void visit(Directory d){}
 public String report(){return isCompliant()?"Compliance: PASS":"Compliance: FAIL "+getViolations();}
}
