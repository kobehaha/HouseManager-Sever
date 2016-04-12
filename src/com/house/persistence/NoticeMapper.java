package com.house.persistence;

import java.util.List;

import com.house.beans.Notice;

public interface NoticeMapper {

	public List<Notice> getAllNotices(int account) throws Exception;

	public int addNotice(Notice notice) throws Exception;

	public int deleteNoticeById(Notice notice) throws Exception;

}
