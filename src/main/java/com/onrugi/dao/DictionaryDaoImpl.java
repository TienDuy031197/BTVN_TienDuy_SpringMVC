package com.onrugi.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.onrugi.entity.Dictionary;

@Repository
@Transactional
public class DictionaryDaoImpl implements DictionaryDao {

	@Autowired
	SessionFactory sessionFactory;

	public List<DictionaryDao> findWordByKeyword(String keyword, String transType) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<String> getAllTranslationTypes() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<DictionaryDao> findWordByKeywordWithPaging(String keyword, String transType, int pageLimit,
			int pageIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteWordById(int id) {
		// TODO Auto-generated method stub

	}

	@Transactional
	public void addWord(Dictionary dictionary) {
		Session session = sessionFactory.getCurrentSession();
		session.save(dictionary);
	}

	public void updateWordMeaning(int id, String meaning) {
		// TODO Auto-generated method stub

	}

	public List<DictionaryDao> findWordById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void addWord(String word, String meaning, String type) {
		// TODO Auto-generated method stub

	}

}
