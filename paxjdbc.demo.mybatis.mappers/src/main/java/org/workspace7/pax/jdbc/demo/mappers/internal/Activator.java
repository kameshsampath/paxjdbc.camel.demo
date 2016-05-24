package org.workspace7.pax.jdbc.demo.mappers.internal;

import org.apache.ibatis.session.SqlSessionFactory;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by kameshs on 24/05/16.
 */
public class Activator implements BundleActivator {

    private static final Logger LOGGER = LoggerFactory.getLogger(Activator.class);

    private BundleContext bundleContext;
    private ServiceTracker serviceTracker;

    @Override
    public void start(BundleContext bundleContext) throws Exception {

        LOGGER.info("Bundle Activated");

        this.bundleContext = bundleContext;

        serviceTracker = new ServiceTracker(bundleContext, SqlSessionFactory.class.getName()
                , new SqlSessionFactoryCustomizer());
        serviceTracker.open();

    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        serviceTracker.close();
        serviceTracker = null;
    }

    private class SqlSessionFactoryCustomizer implements ServiceTrackerCustomizer<SqlSessionFactory, SqlSessionFactory> {
        @Override
        public SqlSessionFactory addingService(ServiceReference serviceReference) {

            LOGGER.info("SQL Session Factory Added ");

            SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) bundleContext.getService(serviceReference);

            if (sqlSessionFactory != null) {
                LOGGER.info("SQL Session Factory is live ");

                sqlSessionFactory.getConfiguration().addMappers("org.workspace7.fuse.paxjdbc.demo.mappers");
            }

            return sqlSessionFactory;
        }

        @Override
        public void modifiedService(ServiceReference serviceReference, SqlSessionFactory o) {
            LOGGER.info("SQL Session Factory Modified ");
        }

        @Override
        public void removedService(ServiceReference serviceReference, SqlSessionFactory o) {
            LOGGER.info("SQL Session Factory Removed ");
        }
    }
}
