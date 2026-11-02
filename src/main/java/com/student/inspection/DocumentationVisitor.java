package com.student.inspection;
import java.util.ArrayList;import java.util.List;
public class DocumentationVisitor implements ProjectVisitor {
 private final List<String> undocumented=new ArrayList<>();
 public List<String> getUndocumented(){return List.copyOf(undocumented);}
 public void visit(SourceCodeFile f){if(!f.getName().toLowerCase().contains("doc"))undocumented.add("Review documentation for "+f.getName());}
 public void visit(TestFile f){} public void visit(ConfigurationFile f){} public void visit(DependencyFile f){} public void visit(Directory d){}
 public String report(){return "Documentation reminders: "+undocumented.size()+" "+undocumented;}
}
