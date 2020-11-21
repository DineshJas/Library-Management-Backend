package com.dinesh.repository;

import org.springframework.data.repository.CrudRepository;

import com.dinesh.entity.Author;

public interface AuthorRepository extends CrudRepository<Author, Long> {

	Author findByAuthorName(String authorName);

}
