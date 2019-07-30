package com.onrugi.dao;

import java.util.List;

import com.onrugi.entity.Dictionary;

public interface DictionaryDao {
	List<DictionaryDao> findWordByKeyword(String keyword, String transType);

	List<String> getAllTranslationTypes();

	List<DictionaryDao> findWordByKeywordWithPaging(String keyword, String transType, int pageLimit, int pageIndex);

	void deleteWordById(int id);

	void addWord(Dictionary dictionary);

	void updateWordMeaning(int id, String meaning);

	List<DictionaryDao> findWordById(int id);
}
