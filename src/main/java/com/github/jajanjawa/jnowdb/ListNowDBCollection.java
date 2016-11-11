package com.github.jajanjawa.jnowdb;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class ListNowDBCollection<Response> implements ParameterizedType {

    private Class<?> clazz;

    public ListNowDBCollection(Class<Response> clazz) {
	this.clazz = clazz;
    }

    @Override
    public Type[] getActualTypeArguments() {
	return new Type[] { clazz };
    }

    @Override
    public Type getOwnerType() {
	return null;
    }

    @Override
    public Type getRawType() {
	return List.class;
    }

}
