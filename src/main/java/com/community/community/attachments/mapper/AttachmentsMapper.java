package com.community.community.attachments.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.community.community.attachments.dto.AttachmentsRequestDto;
import com.community.community.attachments.dto.AttachmentsResponseDto;

public interface AttachmentsMapper {

	void save(AttachmentsRequestDto attachment);
	
	List<AttachmentsResponseDto> findByRefIdAndType(@Param("refId") Long refId, @Param("boardType") String boardType);
	
	AttachmentsResponseDto findById(Long attachmentId); // 첨부파일 단건 조회 추가

}
