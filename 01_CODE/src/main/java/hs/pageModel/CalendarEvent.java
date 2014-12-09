package hs.pageModel;

import java.io.Serializable;

/**
 * @author Administrator
 *
 */
/**
 * @author Administrator
 *
 */
public class CalendarEvent implements Serializable {

     /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String title;
    private String start;
    private String end;
    private String id;
    private String url;
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getStart() {
        return start;
    }
    public void setStart(String start) {
        this.start = start;
    }
    public String getEnd() {
        return end;
    }
    public void setEnd(String end) {
        this.end = end;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
}
