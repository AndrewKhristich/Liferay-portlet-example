package spring.portlet.dto;

import spring.portlet.dto.object.Items;

import java.util.List;

public class HeadHunterDto {
    private String clusters;
    private List<Items> items;
    private int pages;
    private String arguments;
    private Integer found;
    private String alternate_url;
    private int per_page;
    private int page;

    public HeadHunterDto() {
    }

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }

    public int getPages() {
        return pages;
    }
}

