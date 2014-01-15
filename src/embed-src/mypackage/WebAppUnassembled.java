package mypackage;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.EventListener;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;

import com.google.common.collect.ObjectArrays;
import com.google.common.reflect.ClassPath;

public class WebAppUnassembled
{
	static {
		//this will log to to standard out instead of standard err.
		System.setProperty("org.slf4j.simpleLogger.logFile", "System.out");
		//have nice datetime stamps in the log lines
		System.setProperty("org.slf4j.simpleLogger.showDateTime", "true");
		System.setProperty("org.slf4j.simpleLogger.dateTimeFormat", "yyyy-MM-dd HH:mm:ss.SSS");
	}
	
    public static void main(String[] args) throws Exception
    {
        Server server = new Server(8080);
 
        WebAppContext context = new WebAppContext();
 
        context.setResourceBase("./web");
        loadAnnotations(context);
        server.setHandler(context);
 
        server.start();
        server.join();
    }
    
    

	private static void loadAnnotations(WebAppContext context) throws Exception {
		// Add annotations from classpath, using only file resources
		// (thus using only classes from this project's src folder).
		// NOTE: This mimics the annotation behaviour of loading a WAR,
		// but it is certainly not fully functional
		// (some annotations and/or annotate elements are not implemented).
		ClassPath classpath = ClassPath.from(WebAppUnassembled.class
				.getClassLoader());
		for (ClassPath.ClassInfo classInfo : classpath.getTopLevelClasses()) {
			if (classInfo.url().toString().startsWith("file:")) {
				String cname = classInfo.getName();
				Class<?> type = Class.forName(cname);

				WebServlet servletAnn = type.getAnnotation(WebServlet.class);
				if (servletAnn != null) {
					for (String pattern : ObjectArrays.concat(
							servletAnn.value(), servletAnn.urlPatterns(),
							String.class)) {
						System.out.println("pattern:" + pattern + " servlet:"
								+ cname);
						context.addServlet(new ServletHolder((HttpServlet) type
								.getConstructor().newInstance()), pattern);
					}
				}

				WebFilter filterAnn = type.getAnnotation(WebFilter.class);
				if (filterAnn != null) {
					for (String pattern : ObjectArrays.concat(
							filterAnn.value(), filterAnn.urlPatterns(),
							String.class)) {
						System.out.println("pattern:" + pattern + " filter:"
								+ cname);
						DispatcherType[] disTypes = filterAnn.dispatcherTypes();
						EnumSet<DispatcherType> dispatches = EnumSet
								.copyOf(Arrays.asList(disTypes));
						context.addFilter(new FilterHolder((Filter) type
								.getConstructor().newInstance()), pattern,
								dispatches);
					}
				}

				WebListener listenAnn = type.getAnnotation(WebListener.class);
				if (listenAnn != null) {
					System.out.println("listener:" + cname);
					context.addEventListener((EventListener) type
							.getConstructor().newInstance());
				}
			}
		}
	}

}