package com.dinesh.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dinesh.bean.AddBookBean;
import com.dinesh.bean.EditBookBean;
import com.dinesh.bean.RegisterUserBean;
import com.dinesh.dao.UserDao;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserDao userDao;
	
	@RequestMapping(method = RequestMethod.POST, value="/register")
	public Map<?, ?> registerUser(@RequestBody  @Valid RegisterUserBean registerUserBean, BindingResult result){
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		try {
			if (result.hasErrors()) {
				System.out.println(result);
				params.put("response", result);
				params.put("response_code", -1);
				return params;
			}
			params = userDao.registerUser(registerUserBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return params;
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/add/book")
	public Map<?, ?> addBook(@RequestBody  @Valid AddBookBean addBookBean, BindingResult result){
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		try {
			if (result.hasErrors()) {
				System.out.println(result);
				params.put("response", result);
				params.put("response_code", -1);
				return params;
			}
			params = userDao.addBookDetails(addBookBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return params;
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value="/delete/book")
	public Map<?, ?> deleteBook(@RequestParam("bookId") long bookId){
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		try {
			if(bookId == 0){
				params.put("response", "Book Id value needed");
				params.put("response_code", -1);
				return params;
			}
			
			params = userDao.deleteBookDetail(bookId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return params;
	}
	
	@RequestMapping(method = RequestMethod.PUT, value="/edit/book")
	public Map<?, ?> editBook(@RequestBody  @Valid EditBookBean editBookBean, BindingResult result){
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		try {
			if(editBookBean.getBookId() == 0){
				params.put("response", "Book Id value needed");
				params.put("response_code", -1);
				return params;
			}
			
			params = userDao.editBookDetail(editBookBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return params;
	}

	@RequestMapping(method = RequestMethod.GET, value="/get/books")
	public Map<?, ?> getBooksList(){
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		try {
			params = userDao.getBooksList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return params;
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/get/users/list")
	public Map<?, ?> getUsersList(){
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		try {	
			params = userDao.getUsersList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return params;
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/login")
	public Map<?, ?> userLogin(@RequestParam("userEmail") String userEmail){
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		try {
			if(userEmail.trim().isEmpty()){
				params.put("response", "Please enter valid email id");
				params.put("response_code", -1);
				return params;
			}
			params = userDao.userLogin(userEmail);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return params;
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value="/delete/user")
	public Map<?, ?> deleteUser(@RequestParam("userId") long userId){
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		try {
			if(userId == 0){
				params.put("response", "User Id value needed");
				params.put("response_code", -1);
				return params;
			}
			
			params = userDao.deleteUserDetail(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return params;
	}
	
//	@RequestMapping(method = RequestMethod.PUT, value="/edit/book")
//	public Map<?, ?> editBook(@RequestBody  @Valid EditBookBean editBookBean, BindingResult result){
//		Map<String, Object> params = new LinkedHashMap<String, Object>();/
//		try {

//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return params;
//	}
}
