package net.directory.configuration.core;

import net.directory.configuration.AppConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

    private final static Logger LOGGER = LoggerFactory.getLogger (WebConfig.class);

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext ();
        rootContext.register (AppConfiguration.class);

        servletContext.addListener (new ContextLoaderListener (rootContext));

        AnnotationConfigWebApplicationContext servletAppContext = new AnnotationConfigWebApplicationContext ();

        DispatcherServlet dispatcherServlet = new DispatcherServlet (servletAppContext);
        dispatcherServlet.setThrowExceptionIfNoHandlerFound (true);
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet ("dispatcher", dispatcherServlet);
        dispatcher.setLoadOnStartup (1);
        dispatcher.addMapping ("/");

        FilterRegistration.Dynamic encodingFilter = servletContext.addFilter ("encoding-filter", new CharacterEncodingFilter ());
        encodingFilter.setInitParameter ("encoding", "UTF-8");
        encodingFilter.setInitParameter ("forceEncoding", "true");
        encodingFilter.addMappingForUrlPatterns (null, true, "/*");
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[0];
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{};
    }
}
