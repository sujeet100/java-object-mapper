package com.aerospike.mapper.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface AerospikeRecord {
	String namespace() default "";
	String set();
	int ttl() default 0;
	/**
	 * Determine whether to add all the bins or not. If true, all bins will be added without having to map them via @AerospikeBin
	 * @return
	 */
	boolean mapAll() default false;
	int version() default 1;
}