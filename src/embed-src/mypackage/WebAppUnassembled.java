package mypackage;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class WebAppUnassembled
{
    public static void main(String[] args) throws Exception
    {
        Server server = new Server(8080);
 
        WebAppContext context = new WebAppContext();
        context.setResourceBase("./web");

        server.setHandler(context);
 
        server.start();
        server.join();
    }
}