package fr.istic.tpjpa.shared;


import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;

public interface MyFactory extends AutoBeanFactory {
  AutoBean<PersonItf> person();
}
