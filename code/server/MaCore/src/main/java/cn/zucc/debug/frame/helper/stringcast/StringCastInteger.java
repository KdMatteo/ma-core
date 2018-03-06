package cn.zucc.debug.frame.helper.stringcast;

import cn.zucc.debug.frame.helper.ReflectHelper.StringCast;

public class StringCastInteger implements StringCast {

	@Override
	public Object cast(String value) {
		return new Integer(value);
	}

}
