package com.house.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.house.beans.Notice;
import com.house.persistence.NoticeMapper;

@Service
public class NoticeService {

	@Autowired
	private NoticeMapper noticeMapper = null;

	public List<Notice> getAllNoticeByManagerID(int account) {
		List<Notice> list = null;
		try {
			list = noticeMapper.getAllNotices(account);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public boolean addNotice(Notice notice) {
		try {
			int count = noticeMapper.addNotice(notice);
			if (count > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteNoticeById(Notice notice) {
		try {
			int result = noticeMapper.deleteNoticeById(notice);
			if (result != -1) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}
}
