package com.aerospike.mapper.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.aerospike.mapper.annotations.AerospikeEmbed.EmbedType;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
/**
 * Bins marked with AerospikeExclude will not be mapped to the database, irrespective of other annotations. 
 */
public @interface AerospikeReference {
	/**
	 * Fields marked as being lazy references will not be read from Aerospike at runtime when the parent class is
	 * read. Instead, a new object with just the key populated will be created
	 * @return
	 */
	boolean lazy() default false;
	
	public static enum ReferenceType {
		ID,
		DIGEST
	}
	ReferenceType type() default ReferenceType.ID;

}
