# Mood Based Study Planner

A Java desktop app that suggests study plans based on your current mood and logs your study sessions.

## Features
- Mood-based study suggestions (Happy, Sad, Focused, Anxious)
- Study timer with session tracking
- Sessions saved to a SQLite database using JDBC
- GUI built with Java AWT

## Concepts Practiced
OOP (interfaces, inheritance), custom exceptions, wrapper classes, multithreading, JDBC, AWT GUI, file handling

## Tech Stack
Java, AWT, SQLite (JDBC)

## How to Run
1. Install JDK 8 or higher
2. Open a terminal in the project folder
3. Compile: `javac -cp ".;sqlite-jdbc-3.36.0.3.jar" *.java`
4. Run: `java -cp ".;sqlite-jdbc-3.36.0.3.jar" Main`

## Screenshot
![Mood Study Planner](app.png)
(On Mac/Linux, use `:` instead of `;` in the classpath.)
