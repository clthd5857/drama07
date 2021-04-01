package com.spring5.drama07.board.vo;

//import java.sql.Clob;
import java.sql.Timestamp;

import org.springframework.stereotype.Component;

@Component("commentVO")
public class CommentVO {
	private int commentArticleNO;
	//private Clob commentContent;
	private String commentContent;
	private Timestamp commentWriteDate;
	private Timestamp commentModDate;
	private String id;
	private String guestId;
	private int articleNO;
	
	public CommentVO() {
	}

	public CommentVO(int commentArticleNO, String commentContent, Timestamp commentWriteDate, Timestamp commentModDate,
			String id, String guestId, int articleNO) {
		this.commentArticleNO = commentArticleNO;
		this.commentContent = commentContent;
		this.commentWriteDate = commentWriteDate;
		this.commentModDate = commentModDate;
		this.id = id;
		this.guestId = guestId;
		this.articleNO = articleNO;
	}

	public int getCommentArticleNO() {
		return commentArticleNO;
	}

	public void setCommentArticleNO(int commentArticleNO) {
		this.commentArticleNO = commentArticleNO;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public Timestamp getCommentWriteDate() {
		return commentWriteDate;
	}

	public void setCommentWriteDate(Timestamp commentWriteDate) {
		this.commentWriteDate = commentWriteDate;
	}

	public Timestamp getCommentModDate() {
		return commentModDate;
	}

	public void setCommentModDate(Timestamp commentModDate) {
		this.commentModDate = commentModDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGuestId() {
		return guestId;
	}

	public void setGuestId(String guestId) {
		this.guestId = guestId;
	}

	public int getArticleNO() {
		return articleNO;
	}

	public void setArticleNO(int articleNO) {
		this.articleNO = articleNO;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + articleNO;
		result = prime * result + commentArticleNO;
		result = prime * result + ((commentContent == null) ? 0 : commentContent.hashCode());
		result = prime * result + ((commentModDate == null) ? 0 : commentModDate.hashCode());
		result = prime * result + ((commentWriteDate == null) ? 0 : commentWriteDate.hashCode());
		result = prime * result + ((guestId == null) ? 0 : guestId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CommentVO other = (CommentVO) obj;
		if (articleNO != other.articleNO)
			return false;
		if (commentArticleNO != other.commentArticleNO)
			return false;
		if (commentContent == null) {
			if (other.commentContent != null)
				return false;
		} else if (!commentContent.equals(other.commentContent))
			return false;
		if (commentModDate == null) {
			if (other.commentModDate != null)
				return false;
		} else if (!commentModDate.equals(other.commentModDate))
			return false;
		if (commentWriteDate == null) {
			if (other.commentWriteDate != null)
				return false;
		} else if (!commentWriteDate.equals(other.commentWriteDate))
			return false;
		if (guestId == null) {
			if (other.guestId != null)
				return false;
		} else if (!guestId.equals(other.guestId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CommentVO [commentArticleNO=" + commentArticleNO + ", commentContent=" + commentContent
				+ ", commentWriteDate=" + commentWriteDate + ", commentModDate=" + commentModDate + ", id=" + id
				+ ", guestId=" + guestId + ", articleNO=" + articleNO + "]";
	}
	
}	