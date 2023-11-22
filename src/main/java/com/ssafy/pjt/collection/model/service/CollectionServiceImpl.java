package com.ssafy.pjt.collection.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.pjt.collection.model.BookmarkDto;
import com.ssafy.pjt.collection.model.CollectionDto;
import com.ssafy.pjt.collection.model.mapper.CollectionMapper;

@Service
public class CollectionServiceImpl implements CollectionService {

	private final CollectionMapper mapper;
	
	public CollectionServiceImpl(CollectionMapper mapper) {
		super();
		this.mapper = mapper;
	}

	@Override
	public List<CollectionDto> listCollection(String userId) throws Exception {
		// TODO Auto-generated method stub
		return mapper.listCollection(userId);
	}

	@Override
	public void registCollection(CollectionDto dto) throws Exception {
		// TODO Auto-generated method stub
		mapper.registCollection(dto);
	}

	@Override
	public CollectionDto detailCollectoin(int cId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteCollection(int cId) throws Exception {
		// TODO Auto-generated method stub
		mapper.deleteCollection(cId);
	}
	
	@Override
	public void updateCollection(CollectionDto dto) throws Exception {
		// TODO Auto-generated method stub
		mapper.updateCollection(dto);
	}

	@Override
	public List<BookmarkDto> listBookmark(int cid) throws Exception {
		// TODO Auto-generated method stub
		return mapper.listBookmark(cid);
	}

	@Override
	public void registBookmark(BookmarkDto dto) throws Exception {
		// TODO Auto-generated method stub
		mapper.registBookmark(dto);
	}

	@Override
	public void deleteBookmark(int savedId) throws Exception {
		// TODO Auto-generated method stub
		mapper.deleteBookmark(savedId);
	}



}
