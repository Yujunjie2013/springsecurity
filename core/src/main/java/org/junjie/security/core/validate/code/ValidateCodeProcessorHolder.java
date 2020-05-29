/**
 * 
 */
package org.junjie.security.core.validate.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 校验码处理器管理器
 * 
 * @author zhailiang
 *
 */
@Component
public class ValidateCodeProcessorHolder {

	/**
	 * 依赖搜索
	 * 
	 * Spring启动时，会查找容器中所有的ValidateCodeProcessor接口的实现，并把Bean的名字作为key，放到map中
	 * 这里ValidateCodeProcessor有两个默认实现，ImageCodeProcessor、SmsCodeProcessor,通过@Component指定注册的名称
	 *
	 */
	@Autowired
	private Map<String, ValidateCodeProcessor> validateCodeProcessors;

	/**
	 * @param type
	 * @return
	 */
	public ValidateCodeProcessor findValidateCodeProcessor(ValidateCodeType type) {
		return findValidateCodeProcessor(type.toString().toLowerCase());
	}

	/**
	 * @param type
	 * @return
	 */
	public ValidateCodeProcessor findValidateCodeProcessor(String type) {
		String name = type.toLowerCase() + ValidateCodeProcessor.class.getSimpleName();
		ValidateCodeProcessor processor = validateCodeProcessors.get(name);
		if (processor == null) {
			throw new ValidateCodeException("验证码处理器" + name + "不存在");
		}
		return processor;
	}

}
