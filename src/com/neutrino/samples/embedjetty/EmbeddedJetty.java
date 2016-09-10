package com.neutrino.samples.embedjetty;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServlet;

import org.eclipse.jetty.annotations.AnnotationConfiguration;
import org.eclipse.jetty.plus.webapp.EnvConfiguration;
import org.eclipse.jetty.plus.webapp.PlusConfiguration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.FragmentConfiguration;
import org.eclipse.jetty.webapp.MetaInfConfiguration;
import org.eclipse.jetty.webapp.TagLibConfiguration;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.webapp.WebInfConfiguration;
import org.eclipse.jetty.webapp.WebXmlConfiguration;

public class EmbeddedJetty {
    public static void main(String[] args) throws Exception {
        int port = 8888;
        Server server = new Server(port);

        WebAppContext context = new WebAppContext();

        context.setConfigurations(new Configuration[] {
                            new AnnotationConfiguration(), 
                            new WebXmlConfiguration(),
                            new WebInfConfiguration(), 
                            new TagLibConfiguration(),
                            new PlusConfiguration(), 
                            new MetaInfConfiguration(),
                            new FragmentConfiguration(), 
                            new EnvConfiguration() });

        context.setContextPath("/");
        context.setResourceBase("/");
//        
//        HttpServlet servlet = new HttpServlet() {
//		};
//        
        
        context.addServlet(HelloServlet.class, "/hello");
        	
        // Tell the classloader to use the "server" classpath over the
        // webapp classpath. (this is so that jars and libs in your
        // server classpath are used, requiring no WEB-INF/lib 
        // directory to exist)
        context.setParentLoaderPriority(true);

        server.setHandler(context);
        // Start the server thread
        server.start();
        // Wait for the server thread to stop (optional)
        server.join();
    }
}