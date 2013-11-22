package fr.istic.tpjpa.shared;

public class MyFactoryImpl extends com.google.web.bindery.autobean.gwt.client.impl.AbstractAutoBeanFactory implements fr.istic.tpjpa.shared.MyFactory {
  @Override protected void initializeCreatorMap(com.google.web.bindery.autobean.gwt.client.impl.JsniCreatorMap map) {
    map.add(fr.istic.tpjpa.shared.HomeItf.class, getConstructors_fr_istic_tpjpa_shared_HomeItf());
    map.add(fr.istic.tpjpa.shared.PersonItf.class, getConstructors_fr_istic_tpjpa_shared_PersonItf());
    map.add(java.util.List.class, getConstructors_java_util_List());
    map.add(java.util.Iterator.class, getConstructors_java_util_Iterator());
    map.add(java.util.ListIterator.class, getConstructors_java_util_ListIterator());
  }
  private native com.google.gwt.core.client.JsArray<com.google.gwt.core.client.JavaScriptObject> getConstructors_fr_istic_tpjpa_shared_HomeItf() /*-{
    return [
      @fr.istic.tpjpa.shared.HomeItfAutoBean::new(Lcom/google/web/bindery/autobean/shared/AutoBeanFactory;),
      @fr.istic.tpjpa.shared.HomeItfAutoBean::new(Lcom/google/web/bindery/autobean/shared/AutoBeanFactory;Lfr/istic/tpjpa/shared/HomeItf;)
    ];
  }-*/;
  private native com.google.gwt.core.client.JsArray<com.google.gwt.core.client.JavaScriptObject> getConstructors_fr_istic_tpjpa_shared_PersonItf() /*-{
    return [
      @fr.istic.tpjpa.shared.PersonItfAutoBean::new(Lcom/google/web/bindery/autobean/shared/AutoBeanFactory;),
      @fr.istic.tpjpa.shared.PersonItfAutoBean::new(Lcom/google/web/bindery/autobean/shared/AutoBeanFactory;Lfr/istic/tpjpa/shared/PersonItf;)
    ];
  }-*/;
  private native com.google.gwt.core.client.JsArray<com.google.gwt.core.client.JavaScriptObject> getConstructors_java_util_List() /*-{
    return [
      ,
      @emul.java.util.ListAutoBean::new(Lcom/google/web/bindery/autobean/shared/AutoBeanFactory;Ljava/util/List;)
    ];
  }-*/;
  private native com.google.gwt.core.client.JsArray<com.google.gwt.core.client.JavaScriptObject> getConstructors_java_util_Iterator() /*-{
    return [
      ,
      @emul.java.util.IteratorAutoBean::new(Lcom/google/web/bindery/autobean/shared/AutoBeanFactory;Ljava/util/Iterator;)
    ];
  }-*/;
  private native com.google.gwt.core.client.JsArray<com.google.gwt.core.client.JavaScriptObject> getConstructors_java_util_ListIterator() /*-{
    return [
      ,
      @emul.java.util.ListIteratorAutoBean::new(Lcom/google/web/bindery/autobean/shared/AutoBeanFactory;Ljava/util/ListIterator;)
    ];
  }-*/;
  @Override protected void initializeEnumMap() {
  }
  public com.google.web.bindery.autobean.shared.AutoBean home() {
    return new fr.istic.tpjpa.shared.HomeItfAutoBean(MyFactoryImpl.this);
  }
  public com.google.web.bindery.autobean.shared.AutoBean person() {
    return new fr.istic.tpjpa.shared.PersonItfAutoBean(MyFactoryImpl.this);
  }
}
