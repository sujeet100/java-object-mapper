package com.aerospike.mapper.tools.mappers;

import com.aerospike.mapper.tools.TypeMapper;

public class DoubleMapper extends TypeMapper {

    @Override
    public Object toAerospikeFormat(Object value) {
        return value;
    }

    @Override
    public Object fromAerospikeFormat(Object value) {
        if (value == null) {
            return Double.valueOf(0);
        }
        return value;
    }
}
