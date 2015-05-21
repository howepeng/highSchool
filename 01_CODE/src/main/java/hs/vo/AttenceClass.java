package hs.vo;

import hs.pageModel.Combobox;

import java.util.List;

public class AttenceClass {
    private String title;
    private List<Combobox> titles;
    private List<Combobox> headers;
    private String date;
    private List<AttenceClassRow> rows;
    public List<Combobox> getHeaders() {
        return headers;
    }

    public void setHeaders(List<Combobox> headers) {
        this.headers = headers;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<AttenceClassRow> getRows() {
        return rows;
    }

    public void setRows(List<AttenceClassRow> rows) {
        this.rows = rows;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

	public List<Combobox> getTitles() {
		return titles;
	}

	public void setTitles(List<Combobox> titles) {
		this.titles = titles;
	}
}
