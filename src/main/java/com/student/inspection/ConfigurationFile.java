package com.student.inspection;
public class ConfigurationFile implements ProjectElement {
 private final String name; private final boolean insecure; private final boolean exposedCredentials;
 public ConfigurationFile(String name,boolean insecure,boolean exposedCredentials){this.name=name;this.insecure=insecure;this.exposedCredentials=exposedCredentials;}
 public String getName(){return name;} public boolean isInsecure(){return insecure;} public boolean hasExposedCredentials(){return exposedCredentials;}
 @Override public void accept(ProjectVisitor visitor){visitor.visit(this);}
}
