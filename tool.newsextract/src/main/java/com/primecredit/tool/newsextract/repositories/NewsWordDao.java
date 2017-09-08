package com.primecredit.tool.newsextract.repositories;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

import com.primecredit.tool.newsextract.domain.NewsWord;

public interface NewsWordDao extends GraphRepository<NewsWord>{
	public NewsWord findByName(@Param("name") String name);
}
