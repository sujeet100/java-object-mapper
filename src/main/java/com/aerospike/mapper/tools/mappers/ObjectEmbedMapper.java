package com.aerospike.mapper.tools.mappers;

import java.util.List;
import java.util.Map;

import com.aerospike.client.AerospikeException;
import com.aerospike.mapper.annotations.AerospikeEmbed.EmbedType;
import com.aerospike.mapper.tools.AeroMapper;
import com.aerospike.mapper.tools.ClassCache;
import com.aerospike.mapper.tools.ClassCacheEntry;
import com.aerospike.mapper.tools.TypeMapper;

public class ObjectEmbedMapper implements TypeMapper {

	private final Class<?> referencedClass;
	private final AeroMapper mapper;
	private final EmbedType type;
	
	public ObjectEmbedMapper(final Class<?> clazz, final EmbedType type, final AeroMapper mapper) {
		this.referencedClass = clazz;
		this.mapper = mapper;
		this.type = type;
	}
	
	@Override
	public Object toAerospikeFormat(Object value) {
		if (value == null) {
			return null;
		}
		// In this case we want to store a reference to the object.
		ClassCacheEntry entry = ClassCache.getInstance().loadClass(referencedClass, this.mapper);
		switch (type) {
		case LIST:	return entry.getList(value);
		case MAP:	return entry.getMap(value);
		default: 	throw new AerospikeException("Unspecified EmbedType");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object fromAerospikeFormat(Object value) {
		if (value == null) {
			return null;
		}
		ClassCacheEntry entry = ClassCache.getInstance().loadClass(referencedClass, this.mapper);
		try {
			Object instance = this.referencedClass.newInstance();
			
			switch (type) {
			case LIST:
				entry.hydrateFromList((List<Object>)value, instance);
				break;
			case MAP:
				entry.hydrateFromMap((Map<String,Object>)value, instance);
				break;
			default:
				throw new AerospikeException("Unspecified EmbedType");
			}
			return instance;
		} catch (Exception e) {
			throw new AerospikeException(e);
		}
	}

}