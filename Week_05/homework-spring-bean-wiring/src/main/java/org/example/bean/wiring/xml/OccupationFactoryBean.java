package org.example.bean.wiring.xml;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.AbstractFactoryBean;

public class OccupationFactoryBean extends AbstractFactoryBean<Occupation> {

    @Override
    protected Occupation createInstance() throws Exception {
        return new Occupation("geekbang", "student");
    }

    @Override
    public Class<?> getObjectType() {
        return Occupation.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
