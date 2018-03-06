package cn.zucc.debug.frame.helper.stringcast;

import cn.zucc.debug.frame.helper.ReflectHelper.StringCast;

public class StringCastLong implements StringCast {

	@Override
	public Object cast(String value) {
		return new Long(value);
	}

}
