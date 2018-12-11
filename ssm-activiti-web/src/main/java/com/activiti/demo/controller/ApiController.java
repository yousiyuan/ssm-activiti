package com.activiti.demo.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.activiti.demo.entity.Products;
import com.activiti.demo.service.ProductService;

//学习参考链接
//  https://www.cnblogs.com/daimajun/p/7152970.html
/**
 * 
 * 注解@ResponseBody使用说明：
 * 
 * 注解@ResponseBody是作用在方法上的，@ResponseBody 表示该方法的返回结果直接写入 HTTP response body 中，
 * 一般在异步获取数据时使用【也就是AJAX】，在使用 @RequestMapping后，返回值通常解析为跳转路径， 但是加上 @ResponseBody
 * 后返回结果不会被解析为跳转路径，而是直接写入 HTTP response body 中。 比如异步获取 json 数据，加上 @ResponseBody
 * 后，会直接返回 json 数据。
 * 
 * ==================================================================================================================
 * 
 * 注解@RequestBody使用说明：
 * 
 * 注解@RequestBody是作用在形参列表上，用于将前台发送过来固定格式的数据【xml 格式或者 json等】封装为对应的 JavaBean 对象，
 * 封装时使用到的一个对象是系统默认配置的 HttpMessageConverter进行解析，然后封装到形参上。
 * 
 * ==================================================================================================================
 * 
 * 注解@RequestParam使用说明：
 * 
 * 注解@RequestParam修饰方法中形参，获取请求中特定的请求参数值并赋值给形参，同时可以对特定的请求参数进行验证、设置默认值等等
 */
@Controller
@RequestMapping(value = "/api")
public class ApiController {

	@Autowired
	private ProductService productService;

	// 测试链接===》》http://localhost:8888/ssm/api/product.do?pid=3
	@RequestMapping(value = "/product", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Products queryProductById(Integer pid) {

		Products product = productService.queryForObject(pid);

		return product;
	}

	// 测试链接===》》http://localhost:8888/ssm/api/product2.do?pid=3
	@RequestMapping(value = "/product2", method = { RequestMethod.GET })
	public @ResponseBody Products queryProduct(@RequestParam(value = "pid") Integer id) {

		Products product = productService.queryForObject(id);

		return product;
	}

	// POST MAN 工具测试
	// 测试链接===》》http://localhost:8888/ssm/api/product1.do
	// 参数 {"pid":3}
	@RequestMapping(value = "/product1", method = { RequestMethod.POST })
	public @ResponseBody Products queryProductById(HttpServletRequest request, @RequestBody Map<String, Object> map) {

		Integer pid = Integer.valueOf(map.get("pid").toString());
		Products product = productService.queryForObject(pid);

		return product;
	}

}
