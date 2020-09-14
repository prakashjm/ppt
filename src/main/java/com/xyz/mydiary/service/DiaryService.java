package com.xyz.mydiary.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xyz.mydiary.dto.DiaryDTO;
import com.xyz.mydiary.model.Diary;
import com.xyz.mydiary.model.UserDAO;

@Service
public class DiaryService {
	@Autowired
	private com.xyz.mydiary.repo.DiaryRepository repo;

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	AccountUtilityService accountUtilityService;

	public DiaryDTO save(DiaryDTO dto) {
		
		return covertToDaoToDTO(repo.save(covertToDTOToDAO(dto)));
		
	}
	
	
DiaryDTO covertToDaoToDTO(Diary source) {
		
		return modelMapper.map(source, DiaryDTO.class);

		
		
	}
	
Diary covertToDTOToDAO(DiaryDTO source) {
		
 		Diary obj =  modelMapper.map(source, Diary.class);

		obj.setUser(accountUtilityService.getCurrUser()); 
		return obj;
	}


public List <DiaryDTO> findByUser(){
	
	UserDAO user = accountUtilityService.getCurrUser();
	List <Diary> diaries=  repo.findByUser(user);
	List<DiaryDTO> diariesDTO = new ArrayList<>();
	modelMapper.map(diaries, diariesDTO);
	
	List<DiaryDTO> dtos = diaries
			  .stream()
			  .map(diary -> modelMapper.map(diary, DiaryDTO.class))
			  .collect(Collectors.toList());
	
	
	return dtos;
}


}
