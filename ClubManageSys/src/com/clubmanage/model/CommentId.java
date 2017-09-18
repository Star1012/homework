package com.clubmanage.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * CommentId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class CommentId implements java.io.Serializable
{

	// Fields

	private String commentId;
	private String activityId;

	// Constructors

	/** default constructor */
	public CommentId()
	{
	}

	/** full constructor */
	public CommentId(String commentId, String activityId)
	{
		this.commentId = commentId;
		this.activityId = activityId;
	}

	// Property accessors

	@Column(name = "commentID", nullable = false)
	public String getCommentId()
	{
		return this.commentId;
	}

	public void setCommentId(String commentId)
	{
		this.commentId = commentId;
	}

	@Column(name = "activityID", nullable = false)
	public String getActivityId()
	{
		return this.activityId;
	}

	public void setActivityId(String activityId)
	{
		this.activityId = activityId;
	}

	public boolean equals(Object other)
	{
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CommentId))
			return false;
		CommentId castOther = (CommentId) other;

		return ((this.getCommentId() == castOther.getCommentId()) || (this
				.getCommentId() != null && castOther.getCommentId() != null && this
				.getCommentId().equals(castOther.getCommentId())))
				&& ((this.getActivityId() == castOther.getActivityId()) || (this
						.getActivityId() != null
						&& castOther.getActivityId() != null && this
						.getActivityId().equals(castOther.getActivityId())));
	}

	public int hashCode()
	{
		int result = 17;

		result = 37 * result
				+ (getCommentId() == null ? 0 : this.getCommentId().hashCode());
		result = 37
				* result
				+ (getActivityId() == null ? 0 : this.getActivityId()
						.hashCode());
		return result;
	}

}