package org.gameofservices.cell;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class CellContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        CellContext.init();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        CellContext.stop();
    }
}
