package com.config;

import java.util.HashMap;
import java.util.Map;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

import Module.com.Account;


public class HibernateUtil {
	private static SessionFactory sf;
	private static StandardServiceRegistry registry;
	public static SessionFactory getSessionFactory()
	{
		try {
			if(sf==null)
			{
				// connection properties
				Map<String, Object> map=new HashMap<>();
				

				map.put(Environment.JAKARTA_JDBC_DRIVER, "com.mysql.cj.jdbc.Driver");
				map.put(Environment.JAKARTA_JDBC_URL, "jdbc:mysql://localhost:3306/Hib-BMS");
				map.put(Environment.JAKARTA_JDBC_USER, "root");
				map.put(Environment.JAKARTA_JDBC_PASSWORD, "root");
				//hibernate propeties
				map.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
				map.put(Environment.HBM2DDL_AUTO, "update");
				map.put(Environment.SHOW_SQL,"true");
			
				
				// create object of StandardServiceRegistryBuilder
				registry=new StandardServiceRegistryBuilder().applySettings(map).build();
				// create object of MetaDataSources
				MetadataSources mds=new MetadataSources(registry);
				mds.addAnnotatedClass(Account.class);
				// create object of MetaData
				  Metadata md  =  mds.getMetadataBuilder().build();
				  // create object of SessionFactory
				  sf=md.getSessionFactoryBuilder().build();
			}
		}
			catch (Exception e) {
				e.printStackTrace();
			}
		
		return sf;
	}

}

