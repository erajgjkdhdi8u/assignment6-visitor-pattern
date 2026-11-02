# Assignment 6 - Smart Code Inspection & Compliance Engine

Java 17, Maven, JUnit 5. Pattern: Visitor.

## Run

```bash
mvn test
mvn package
java -cp target/classes com.student.inspection.Main
```

You can also open this Maven project in IntelliJ IDEA and run `Main.main()`.

## Project structure

- `ProjectElement`: common interface for project files and directories.
- `SourceCodeFile`, `TestFile`, `ConfigurationFile`, `DependencyFile`, `Directory`: five element classes.
- `ProjectVisitor`: interface with an overloaded `visit()` method for each element.
- `SecurityAuditVisitor`: security problems and score.
- `ProjectStatisticsVisitor`: file counts, LOC, complexity, tests, dependencies.
- `QualityGateVisitor`: release checks.
- `ComplianceVisitor`: dependencies, credentials, required tests.
- `DocumentationVisitor`: extra visitor that prints documentation reminders.
- `Directory.accept()` visits the directory and recursively visits its children.

The sample project is `fintech-project` with `src`, `tests`, `config` and `pom.xml`.
The demo deliberately contains some problems, so the final quality result is FAILED.

## Assignment questions

1. **What problem does Visitor solve?** It keeps operations such as security and statistics separate from the file classes.
2. **Where are Element and Visitor?** `ProjectElement` with its five implementing classes; `ProjectVisitor` with five visitor implementations.
3. **What is double dispatch?** A specific element's `accept(visitor)` calls `visitor.visit(this)`, selecting the overload for that element.
4. **Why not put operations inside elements?** Each file class would contain unrelated checks; adding a new operation would require changing all file classes.
5. **Is it easier to add a Visitor or Element?** A visitor is easier because existing elements stay unchanged. A new element requires another `visit` overload in the interface and every visitor.
6. **When is Visitor a bad choice?** When the number of element types changes frequently.
7. **Visitor vs Strategy?** Strategy swaps algorithms for one task. Visitor performs different operations over a fixed family of element types.
8. **SOLID?** Single Responsibility, Open/Closed for new visitors, and Dependency Inversion through the `ProjectVisitor` interface.

## New element challenge

For `DatabaseMigrationFile`, we must implement `ProjectElement` and `accept()`, add `visit(DatabaseMigrationFile)` to `ProjectVisitor`, and implement that method in all five visitors. Otherwise, the existing visitor classes will not compile. `Directory` stays unchanged.

## Simplified rules

- The example uses stored flags, not real parsing of Java source code.
- Security score starts at 100 and loses 20 for each problem, down to 0.
- Average complexity is based on source files only.
- Quality passes when average complexity is at most 10, test/source ratio is at least 0.40, and there are no critical security issues.
- Compliance checks blank dependency versions, prohibited dependencies, credentials and missing tests.
- DocumentationVisitor is intentionally simple: it creates reminders for source filenames not containing "doc".

## UML

- [Class diagram](docs/class-diagram.puml)
- [Sequence diagram](docs/sequence-diagram.puml)

The additional DocumentationVisitor was added in its own commit.
