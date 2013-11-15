package fr.istic.tpjpa.shared;

public class PersonItfAutoBean extends com.google.web.bindery.autobean.shared.impl.AbstractAutoBean<fr.istic.tpjpa.shared.PersonItf> {
  private final fr.istic.tpjpa.shared.PersonItf shim = new fr.istic.tpjpa.shared.PersonItf() {
    public java.lang.Long getId()  {
      java.lang.Long toReturn = PersonItfAutoBean.this.getWrapped().getId();
      return toReturn;
    }
    public java.lang.String getName()  {
      java.lang.String toReturn = PersonItfAutoBean.this.getWrapped().getName();
      return toReturn;
    }
    public java.util.List getDevices()  {
      java.util.List toReturn = PersonItfAutoBean.this.getWrapped().getDevices();
      if (toReturn != null) {
        if (PersonItfAutoBean.this.isWrapped(toReturn)) {
          toReturn = PersonItfAutoBean.this.getFromWrapper(toReturn);
        } else {
          toReturn = new emul.java.util.ListAutoBean(getFactory(), toReturn).as();
        }
      }
      return toReturn;
    }
    public java.util.List getFriends()  {
      java.util.List toReturn = PersonItfAutoBean.this.getWrapped().getFriends();
      if (toReturn != null) {
        if (PersonItfAutoBean.this.isWrapped(toReturn)) {
          toReturn = PersonItfAutoBean.this.getFromWrapper(toReturn);
        } else {
          toReturn = new emul.java.util.ListAutoBean(getFactory(), toReturn).as();
        }
      }
      return toReturn;
    }
    public void setId(java.lang.Long id)  {
      PersonItfAutoBean.this.getWrapped().setId(id);
      PersonItfAutoBean.this.set("setId", id);
    }
    @Override public boolean equals(Object o) {
      return this == o || getWrapped().equals(o);
    }
    @Override public int hashCode() {
      return getWrapped().hashCode();
    }
    @Override public String toString() {
      return getWrapped().toString();
    }
  };
  { com.google.gwt.core.client.impl.WeakMapping.set(shim, com.google.web.bindery.autobean.shared.AutoBean.class.getName(), this); }
  public PersonItfAutoBean(com.google.web.bindery.autobean.shared.AutoBeanFactory factory) {super(factory);}
  public PersonItfAutoBean(com.google.web.bindery.autobean.shared.AutoBeanFactory factory, fr.istic.tpjpa.shared.PersonItf wrapped) {
    super(wrapped, factory);
  }
  public fr.istic.tpjpa.shared.PersonItf as() {return shim;}
  public Class<fr.istic.tpjpa.shared.PersonItf> getType() {return fr.istic.tpjpa.shared.PersonItf.class;}
  @Override protected fr.istic.tpjpa.shared.PersonItf createSimplePeer() {
    return new fr.istic.tpjpa.shared.PersonItf() {
      private final com.google.web.bindery.autobean.shared.Splittable data = fr.istic.tpjpa.shared.PersonItfAutoBean.this.data;
      public java.lang.Long getId()  {
        return (java.lang.Long) PersonItfAutoBean.this.getOrReify("id");
      }
      public java.lang.String getName()  {
        return (java.lang.String) PersonItfAutoBean.this.getOrReify("name");
      }
      public java.util.List getDevices()  {
        return (java.util.List) PersonItfAutoBean.this.getOrReify("devices");
      }
      public java.util.List getFriends()  {
        return (java.util.List) PersonItfAutoBean.this.getOrReify("friends");
      }
      public void setId(java.lang.Long id)  {
        PersonItfAutoBean.this.setProperty("id", id);
      }
    };
  }
  @Override protected void traverseProperties(com.google.web.bindery.autobean.shared.AutoBeanVisitor visitor, com.google.web.bindery.autobean.shared.impl.AbstractAutoBean.OneShotContext ctx) {
    com.google.web.bindery.autobean.shared.impl.AbstractAutoBean bean;
    Object value;
    com.google.web.bindery.autobean.gwt.client.impl.ClientPropertyContext propertyContext;
    fr.istic.tpjpa.shared.PersonItf as = as();
    value = as.getId();
    propertyContext = new com.google.web.bindery.autobean.gwt.client.impl.ClientPropertyContext(
      as,
      com.google.web.bindery.autobean.gwt.client.impl.ClientPropertyContext.Setter.beanSetter(PersonItfAutoBean.this, "id"),
      java.lang.Long.class
    );
    if (visitor.visitValueProperty("id", value, propertyContext)) {
    }
    visitor.endVisitValueProperty("id", value, propertyContext);
    value = as.getName();
    propertyContext = new com.google.web.bindery.autobean.gwt.client.impl.ClientPropertyContext(
      as,
      com.google.web.bindery.autobean.gwt.client.impl.ClientPropertyContext.Setter.beanSetter(PersonItfAutoBean.this, "name"),
      java.lang.String.class
    );
    if (visitor.visitValueProperty("name", value, propertyContext)) {
    }
    visitor.endVisitValueProperty("name", value, propertyContext);
    bean = (com.google.web.bindery.autobean.shared.impl.AbstractAutoBean) com.google.web.bindery.autobean.shared.AutoBeanUtils.getAutoBean(as.getDevices());
    propertyContext = new com.google.web.bindery.autobean.gwt.client.impl.ClientPropertyContext(
      as,
      com.google.web.bindery.autobean.gwt.client.impl.ClientPropertyContext.Setter.beanSetter(PersonItfAutoBean.this, "devices"),
      new Class<?>[] {java.util.List.class, fr.istic.tpjpa.shared.ElectronicDevice.class},
      new int[] {1, 0}
    );
    if (visitor.visitCollectionProperty("devices", bean, propertyContext)) {
      if (bean != null) { bean.traverse(visitor, ctx); }
    }
    visitor.endVisitCollectionProperty("devices", bean, propertyContext);
    bean = (com.google.web.bindery.autobean.shared.impl.AbstractAutoBean) com.google.web.bindery.autobean.shared.AutoBeanUtils.getAutoBean(as.getFriends());
    propertyContext = new com.google.web.bindery.autobean.gwt.client.impl.ClientPropertyContext(
      as,
      com.google.web.bindery.autobean.gwt.client.impl.ClientPropertyContext.Setter.beanSetter(PersonItfAutoBean.this, "friends"),
      new Class<?>[] {java.util.List.class, fr.istic.tpjpa.shared.Person.class},
      new int[] {1, 0}
    );
    if (visitor.visitCollectionProperty("friends", bean, propertyContext)) {
      if (bean != null) { bean.traverse(visitor, ctx); }
    }
    visitor.endVisitCollectionProperty("friends", bean, propertyContext);
  }
}
