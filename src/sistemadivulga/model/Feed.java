/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemadivulga.model;

import java.util.ArrayList;
import java.util.List;
/*
 * Stores an RSS feed
 */
public class Feed {

  final String title;
  final String link;
  final String description;
  final String language;
  final String copyright;
  final String pubDate;
  final String src;

    public String getSrc() {
        return src;
    }

  final List<FeedMessage> entries = new ArrayList<FeedMessage>();

  public Feed(String title, String link, String description, String language,
      String copyright, String pubDate, String src) {
    this.title = title;
    this.link = link;
    this.description = description;
    this.language = language;
    this.copyright = copyright;
    this.pubDate = pubDate;
    this.src = src;
  }

  public List<FeedMessage> getMessages() {
    return entries;
  }

  public String getTitle() {
    return title;
  }

  public String getLink() {
    return link;
  }

  public String getDescription() {
    return description;
  }

  public String getLanguage() {
    return language;
  }

  public String getCopyright() {
    return copyright;
  }

  public String getPubDate() {
    return pubDate;
  }
  
  @Override
  public String toString() {
    return "Feed [copyright=" + copyright + ", description=" + description
        + ", language=" + language + ", link=" + link + ", pubDate="
        + pubDate + ", title=" + title + "]";
  }

} 