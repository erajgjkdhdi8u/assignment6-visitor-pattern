package com.student.inspection;
import java.util.ArrayList;import java.util.List;
public class DependencyFile implements ProjectElement {
 public record Dependency(String name,String version,boolean vulnerable) { }
 private final String name; private final List<Dependency> dependencies=new ArrayList<>();
 public DependencyFile(String name){this.name=name;} public String getName(){return name;}
 public void addDependency(String name,String version,boolean vulnerable){dependencies.add(new Dependency(name,version,vulnerable));}
 public List<Dependency> getDependencies(){return List.copyOf(dependencies);}
 @Override public void accept(ProjectVisitor visitor){visitor.visit(this);}
}
