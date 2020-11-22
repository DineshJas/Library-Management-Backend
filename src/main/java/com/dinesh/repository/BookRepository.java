package com.dinesh.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.dinesh.entity.Book;

@Transactional
public interface BookRepository extends CrudRepository<Book, Long> {

	Book findByBookId(long bookId);

	List<Book> findByIsDeleteOrderByBookIdAsc(byte isdeletetrue);
	
	Book findByBookIdAndIsBookLent(long bookId, byte isdeletetrue);

}
