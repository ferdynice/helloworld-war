# Helloworld WAR

### A example Java WAR project / template project

Contains:
- Helloworld servlet (using Annotations)
- an example static html file
- a most basic web.xml
- compile time servlet-api.jar (so doesn't need Java EE)
- Ant build.xml that builds the WAR
- Simple logging, with slf4j-api and sl4j-simple
- Jetty embedded server for running directly in Eclipe

It should work with Java 6, 7 and upwards.
It doesn't contain any dependendency injection specific frameworks,
such as Spring or Guice. (Although you can add this easily).

The nature is an Eclipse project,
it contains a minimal .classpath and .project file.

#### Building the WAR
To build a WAR that you can deploy (for example to Tomcat or Jetty),
you can run 'ant' from the project root.
By default the Java source and target version is **1.7**,
this can be changed in the build.xml file.

The WAR is tested to work with Apache Tomcat 7 and Jettyrunner 9.

#### Running in Eclipse
Typically, during development, you want run the application directly
in Eclipse, for quick results and easy debugging.
(Including working breakpoints and hot code swap).

This is possible thanks to embedding Jetty.
For this, run 'WebAppUnassembled' as a Java application.

Note that it is not trivial to dynamically load the annotated servlets this way.
(Most examples around the web involve around creating a war file, which defeat some purposes).
However WebAppUnassembled tries a best effort to do this by using Classpath scanning,
still picking up the essential annotations with basic options.
The annotations are WebServlet, WebFilter and WebListener.

It uses the Google Guava helper library, which is a nice utility to have anyway.
(If you want to call it from within your web app itself, move the library to lib/runtime
or it won't be included in the war file when you build it).

#### LICENSE
Licensed under [Eclipse Public License](http://www.eclipse.org/legal/epl-v10.html).

