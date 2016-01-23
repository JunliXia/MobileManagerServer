package com.mm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.mm.tool.MyOpcode;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

//通知
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)  
@Table(name = "Notice")
public class CEntityNotice {
	private int m_iNoticeId; // 通知号,主键,自增
	private String m_sNoticeTime; // 通知时间
	private String m_sNoticeTitle; // 通知题目
	private String m_sNoticeContent; // 通知内容

	public CEntityNotice() {
	}

	public static class BuildJsonObject {
		private JSONObject outjson = new JSONObject();

		public BuildJsonObject() {
		}

		public BuildJsonObject NoticeId(int m_iNoticeId) {
			outjson.put(MyOpcode.Notice.NoticeId, m_iNoticeId);
			return this;
		}




		public BuildJsonObject NoticeTitle(String m_sNoticeTitle) {
			outjson.put(MyOpcode.Notice.NoticeTitle, m_sNoticeTitle);
			return this;
		}

		public BuildJsonObject NoticeContent(String m_sNoticeContent) {
			outjson.put(MyOpcode.Notice.NoticeContent, m_sNoticeContent);
			return this;
		}

		public BuildJsonObject Operation(int operation) {
			outjson.put(MyOpcode.Operation.OPERATION, operation);
			return this;
		}

		public BuildJsonObject Check(boolean check) {
			outjson.put(MyOpcode.Operation.CHECK, check);
			return this;
		}

		public BuildJsonObject MyJSONArray(JSONArray array) {
			outjson.put(MyOpcode.Notice.NoticeList, array);
			return this;
		}

		public BuildJsonObject ToSingle(CEntityNotice cEntityNotice) {
			outjson.put(MyOpcode.Notice.NoticeId, cEntityNotice.m_iNoticeId);
			outjson.put(MyOpcode.Notice.NoticeTime, cEntityNotice.m_sNoticeTime);
			outjson.put(MyOpcode.Notice.NoticeTitle, cEntityNotice.m_sNoticeTitle);
			outjson.put(MyOpcode.Notice.NoticeContent, cEntityNotice.m_sNoticeContent);

			return this;
		}

		public JSONObject build() {
			return outjson;
		}

	}

	public static class Builder {
		private int m_iNoticeId; // 通知号,主键,自增
		private String m_sNoticeTime; // 通知时间
		private String m_sNoticeTitle; // 通知题目
		private String m_sNoticeContent; // 通知内容

		public Builder() {
		}

		public Builder NoticeId(int m_iNoticeId) {
			this.m_iNoticeId = m_iNoticeId;
			return this;
		}

		public Builder NoticeTime(String m_sNoticeTime) {
			this.m_sNoticeTime = m_sNoticeTime;
			return this;
		}

		public Builder NoticeTitle(String m_sNoticeTitle) {
			this.m_sNoticeTitle = m_sNoticeTitle;
			return this;
		}

		public Builder NoticeContent(String m_sNoticeContent) {
			this.m_sNoticeContent = m_sNoticeContent;
			return this;
		}

		public CEntityNotice build() {
			return new CEntityNotice(this);
		}
	}

	private CEntityNotice(Builder b) {
		m_iNoticeId = b.m_iNoticeId;
		m_sNoticeTime = b.m_sNoticeTime;
		m_sNoticeTitle = b.m_sNoticeTitle;
		m_sNoticeContent = b.m_sNoticeContent;
	}

	@Id
	@GeneratedValue
	@Column(name = "NoticeId")
	public int getM_iNoticeId() {
		return m_iNoticeId;
	}

	@Column(name = "NoticeContent")
	public String getM_sNoticeContent() {
		return m_sNoticeContent;
	}

	@Column(name = "NoticeTime")
	public String getM_sNoticeTime() {
		return m_sNoticeTime;
	}

	@Column(name = "NoticeTitle")
	public String getM_sNoticeTitle() {
		return m_sNoticeTitle;
	}

	public void setM_iNoticeId(int mINoticeId) {
		m_iNoticeId = mINoticeId;
	}

	public void setM_sNoticeContent(String mSNoticeContent) {
		m_sNoticeContent = mSNoticeContent;
	}

	public void setM_sNoticeTime(String mSNoticeTime) {
		m_sNoticeTime = mSNoticeTime;
	}

	public void setM_sNoticeTitle(String mSNoticeTitle) {
		m_sNoticeTitle = mSNoticeTitle;
	}

}
