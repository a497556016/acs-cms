package com.henede.admin.dto;

import java.io.Serializable;
import java.util.List;

public class MenuDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4219834863593927163L;
	private Integer id;
	private String text;
	private String link;
	private boolean group;
	private String icon;
	private String acl;
	private String translate;
	private List<MenuDto> children;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public boolean isGroup() {
		return group;
	}
	public void setGroup(boolean group) {
		this.group = group;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getAcl() {
		return acl;
	}
	public void setAcl(String acl) {
		this.acl = acl;
	}
	public String getTranslate() {
		return translate;
	}
	public void setTranslate(String translate) {
		this.translate = translate;
	}
	public List<MenuDto> getChildren() {
		return children;
	}
	public void setChildren(List<MenuDto> children) {
		this.children = children;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	
}
