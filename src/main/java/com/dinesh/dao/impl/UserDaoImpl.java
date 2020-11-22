package com.dinesh.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dinesh.bean.AddBookBean;
import com.dinesh.bean.EditBookBean;
import com.dinesh.bean.LendBookBean;
import com.dinesh.bean.RegisterUserBean;
import com.dinesh.dao.UserDao;
import com.dinesh.entity.Author;
import com.dinesh.entity.Book;
import com.dinesh.entity.Publisher;
import com.dinesh.entity.User;
import com.dinesh.repository.AuthorRepository;
import com.dinesh.repository.BookRepository;
import com.dinesh.repository.PublisherRepository;
import com.dinesh.repository.UserRepository;

@Service
public class UserDaoImpl implements UserDao {
	
	public static final byte active = 0;
	public static final byte inactive = 1;
	
	public static final byte isDeleteFalse = 0;
	public static final byte isDeleteTrue = 1;
	
	public static final byte admin_user = -1;
	public static final byte normal_user = 1;
	
	@Autowired UserRepository userRepo;
	
	@Autowired PublisherRepository publisherRepo;
	
	@Autowired AuthorRepository authorRepo;
	
	@Autowired BookRepository bookRepo;

	@Override
	public Map<String, Object> registerUser(@Valid RegisterUserBean registerUserBean) {
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		try {
			User user = userRepo.findByUserEmailAndIsActive(registerUserBean.getEmailId().toUpperCase().trim(), active);
			if(user != null) {
				params.put("response", "User already registered");
				params.put("response_code", 2);
				return params;
			}
			
			User newUser = new User();
			newUser.setUserEmail(registerUserBean.getEmailId().toUpperCase().trim());
			newUser.setUserName(registerUserBean.getUserName().trim());
			newUser.setUserType(normal_user);
			newUser.setIsActive(active);
			newUser.setCreatedDate(new Date());
			userRepo.save(newUser);
			params.put("response", "User registered successfully");
			params.put("response_code", 0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return params;
	}

	@Override
	public Map<String, Object> addBookDetails(@Valid AddBookBean addBookBean) {
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		try {
			Publisher publisherObj = publisherRepo.findByPublisherName(addBookBean.getPublisherName().trim());
			Author authorObj = authorRepo.findByAuthorName(addBookBean.getAuthorName().trim());
			Book book = new Book();
			
			if(publisherObj == null){
				Publisher publisher = new Publisher();
				publisher.setPublisherName(addBookBean.getPublisherName());
				publisherRepo.save(publisher);
				book.setPublisher(publisher);
			} else {
				book.setPublisher(publisherObj);
			}
			
			if(authorObj == null){
				Author author = new Author();
				author.setAuthorName(addBookBean.getAuthorName());
				authorRepo.save(author);
				book.setAuthor(author);
			} else {				
				book.setAuthor(authorObj);
			}
			
			book.setBookName(addBookBean.getBookName());
			book.setBookCategory(addBookBean.getBookCategory());
			book.setIsDelete(isDeleteFalse);
			book.setBookImage(addBookBean.getBookImage());
			book.setIsBookLent(active);
			bookRepo.save(book);
			params.put("response", "New book added successfully");
			params.put("response_code", 0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return params;
	}

	@Override
	public Map<String, Object> deleteBookDetail(long bookId) {
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		try {
			Book book = bookRepo.findByBookId(bookId);
			if(book == null){
				params.put("response", "Book details not found");
				params.put("response_code", -1);
				return params;
			}
			
			book.setIsDelete(isDeleteTrue);
			bookRepo.save(book);
			params.put("response", "Book deleted.");
			params.put("response_code", 0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return params;
	}

	@Override
	public Map<String, Object> editBookDetail(@Valid EditBookBean editBookBean) {
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		try {
			Book book = bookRepo.findByBookId(editBookBean.getBookId());
			if(book == null){
				params.put("response", "Book details not found");
				params.put("response_code", -1);
				return params;
			}
			
			book.setBookImage(editBookBean.getBookImage());
			book.setBookName(editBookBean.getBookName());
			book.setBookCategory(editBookBean.getBookCategory());
			
			Publisher publisherObj = publisherRepo.findByPublisherName(editBookBean.getPublisherName().trim());
			Author authorObj = authorRepo.findByAuthorName(editBookBean.getAuthorName().trim());
			
			if(publisherObj == null){
				Publisher publisher = new Publisher();
				publisher.setPublisherName(editBookBean.getPublisherName());
				publisherRepo.save(publisher);
				book.setPublisher(publisher);
			} else {
				book.setPublisher(publisherObj);
			}
			
			if(authorObj == null){
				Author author = new Author();
				author.setAuthorName(editBookBean.getAuthorName());
				authorRepo.save(author);
				book.setAuthor(author);
			} else {				
				book.setAuthor(authorObj);
			}
			
			bookRepo.save(book);
			params.put("response", "Book details are edited.");
			params.put("response_code", 0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return params;
	}

	@Override
	public Map<String, Object> getBooksList() {
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		try {
			List<Book> bookList = bookRepo.findByIsDeleteOrderByBookIdAsc(isDeleteFalse);
			
			ArrayList<Object> responseList = new ArrayList<>();
			for (Book book : bookList) {
				Map<String, Object> response = new LinkedHashMap<String, Object>();
				response.put("bookId", book.getBookId());
				response.put("bookName", book.getBookName());
				response.put("bookCategory", book.getBookCategory());
				response.put("bookImage", book.getBookImage());
				response.put("authorName", book.getAuthor().getAuthorName());
				response.put("publisherName", book.getPublisher().getPublisherName());
				response.put("isBookLented", book.getIsBookLent());
				responseList.add(response);
			}
			params.put("response", responseList);
			params.put("response_code", 0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return params;
	}

	@Override
	public Map<String, Object> userLogin(String userEmail) {
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		try {
			User userObj = userRepo.findByUserEmailAndIsActive(userEmail.toUpperCase().trim(), active);
			if(userObj == null) {
				params.put("response", "User not registered");
				params.put("response_code", 2);
				return params;
			}
			Map<String, Object> response = new LinkedHashMap<String, Object>();
			response.put("userId", userObj.getUserId());
			response.put("userName", userObj.getUserName());
			response.put("userEmail", userObj.getUserEmail());
			response.put("userType", userObj.getUserType() == -1 ? "ADMIN" : "USER" );
			
			params.put("response", response);
			params.put("response_code", 0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return params;
	}

	@Override
	public Map<String, Object> getUsersList() {
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		try {
			List<User> usersList = userRepo.findByUserTypeOrderByUserIdAsc(normal_user);
			
			ArrayList<Object> responseList = new ArrayList<>();
			for (User user : usersList) {
				Map<String, Object> response = new LinkedHashMap<String, Object>();
				response.put("userId", user.getUserId());
				response.put("userName", user.getUserName());
				response.put("userEmail", user.getUserEmail());
				response.put("userIsActive", user.getIsActive());
				responseList.add(response);
			}
			params.put("response", responseList);
			params.put("response_code", 0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return params;
	}

	@Override
	public Map<String, Object> deleteUserDetail(long userId) {
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		try {
			User user = userRepo.findByUserId(userId);
			if(user == null){
				params.put("response", "User details not found");
				params.put("response_code", -1);
				return params;
			}
			
			if(user.getIsActive() == active){
				user.setIsActive(inactive);
				params.put("response", "User De-Actived.");
			} else {
				user.setIsActive(active);
				params.put("response", "User Activated.");
			}
			
			userRepo.save(user);
			params.put("response_code", 0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return params;
	}

	@Override
	public Map<String, Object> lendBookToUser(@Valid LendBookBean lendBookBean) {
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		try {
			User user = userRepo.findByUserIdAndIsActive(lendBookBean.getUserId(), active);
			if(user == null){
				params.put("response", "User details not found");
				params.put("response_code", -1);
				return params;
			}
			
			Book book = bookRepo.findByBookIdAndIsBookLent(lendBookBean.getBookId(), active);
			if(book == null){
				params.put("response", "Book is already lented");
				params.put("response_code", -1);
				return params;
			}
			
			book.setIsBookLent(inactive);
			book.setUser(user);
			bookRepo.save(book);
			params.put("response", "Book lented to user.");
			params.put("response_code", 0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return params;
	}

	@Override
	public Map<String, Object> getActiveUsersList() {
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		try {
			List<User> usersList = userRepo.findByIsActiveAndUserTypeOrderByUserIdAsc(active, normal_user);
			
			ArrayList<Object> responseList = new ArrayList<>();
			for (User user : usersList) {
				Map<String, Object> response = new LinkedHashMap<String, Object>();
				response.put("userId", user.getUserId());
				response.put("userName", user.getUserName());
				responseList.add(response);
			}
			params.put("response", responseList);
			params.put("response_code", 0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return params;
	}

}
