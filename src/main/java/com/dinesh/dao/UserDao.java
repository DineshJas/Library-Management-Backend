package com.dinesh.dao;

import java.util.Map;

import javax.validation.Valid;

import com.dinesh.bean.AddBookBean;
import com.dinesh.bean.EditBookBean;
import com.dinesh.bean.RegisterUserBean;

public interface UserDao {

	Map<String, Object> registerUser(@Valid RegisterUserBean registerUserBean);

	Map<String, Object> addBookDetails(@Valid AddBookBean addBookBean);

	Map<String, Object> deleteBookDetail(long bookId);

	Map<String, Object> editBookDetail(@Valid EditBookBean editBookBean);

	Map<String, Object> getBooksList();

	Map<String, Object> userLogin(String userEmail);

	Map<String, Object> getUsersList();

	Map<String, Object> deleteUserDetail(long userId);

}
