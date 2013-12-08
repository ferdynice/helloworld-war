# Helloworld WAR

### A simple example Java WAR project

Contains:
- Helloworld servlet
- an example static html file
- a most basic web.xml
- compile time servlet-api.jar (so doesn't need Java EE)
- Ant build.xml that builds the WAR
- Simple logging, with slf4j-api and sl4j-simple
- Jetty embedded server for running directly in Eclipe

It should work with any popular version of Java,
and it doesn't contain any dependendency injection specific frameworks,
such as Spring or Guice. (Although you can add this easily).

The nature is an Eclipse project,
it contains a minimal .classpath and .project file.

#### Building the WAR
To build a WAR that you can deploy (for example to Tomcat or Jetty),
you can run 'ant' from the project root.
By default the Java source and target version is **1.7**,
this can be changed in the build.xml file.

#### Running in Eclipse
Typically, during development, you want run the application directly
in Eclipse, for quick results and easy debugging.
This is possible thanks to embedding Jetty:
For this, run 'WebAppUnassembled' as a Java application.

#### LICENSE
Licensed under [Eclipse Public License](http://www.eclipse.org/legal/epl-v10.html). 
