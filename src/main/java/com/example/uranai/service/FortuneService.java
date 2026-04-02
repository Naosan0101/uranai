package com.example.uranai.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.uranai.entity.Fortune;
import com.example.uranai.repository.FortuneMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FortuneService {
	
	private final FortuneMapper fortuneMapper;
	
	public Fortune getFortune(Integer year, String mood) {
		List<Fortune> list = fortuneMapper.selectAll();
		
		int moodOffset = 0;
		
		if("最高".equals(mood)) {
			moodOffset = 1;
		} else if("普通".equals(mood)) {
			moodOffset = 2;
		} else if("最悪".equals(mood)) {
			moodOffset = 3;
		}
		
		LocalDate today = LocalDate.now();
	    int todayValue = today.getDayOfYear();  // 1〜365

		
		int size = list.size();
		int monthOffset = ((year / 10) + moodOffset + todayValue) % size;
		int dayOffset = ((year % 100) + moodOffset + todayValue) % size;
		
		Fortune month = list.get(monthOffset);
		Fortune day = list.get(dayOffset);
		
		Fortune result = new Fortune();
		result.setMonthResult(month.getMonthResult());
		result.setDayResult(day.getDayResult());
		
		return result;
		
	}

}
