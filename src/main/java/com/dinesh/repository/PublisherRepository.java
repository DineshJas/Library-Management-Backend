package com.dinesh.repository;

import org.springframework.data.repository.CrudRepository;

import com.dinesh.entity.Publisher;

public interface PublisherRepository extends CrudRepository<Publisher, Long> {

	Publisher findByPublisherName(String publisherName);

}
