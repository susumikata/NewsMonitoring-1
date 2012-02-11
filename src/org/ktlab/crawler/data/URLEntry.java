package org.ktlab.crawler.data;

import java.util.Date;

import com.google.code.morphia.annotations.Entity;

@Entity
public class URLEntry {
  private String url_id;
  private String domain_id;
  private boolean download;
  private int deep;
  private Date date;

  public String getUrl_id() {
    return url_id;
  }

  public void setUrl_id(String url_id) {
    this.url_id = url_id;
  }

  public String getDomain_id() {
    return domain_id;
  }

  public void setDomain_id(String domain_id) {
    this.domain_id = domain_id;
  }

  public boolean isDownload() {
    return download;
  }

  public void setDownload(boolean download) {
    this.download = download;
  }

  public int getDeep() {
    return deep;
  }

  public void setDeep(int deep) {
    this.deep = deep;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

}
