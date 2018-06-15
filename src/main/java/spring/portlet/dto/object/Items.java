package spring.portlet.dto.object;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Date;

public class Items {
    private Salary salary;
    private Snippet snippet;
    private boolean archived;
    private boolean premium;
    private String name;
    private Area area;
    private String url;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private Date created_at;
    private String alternate_url;
    private String apply_alternate_url;
    private String[] relations;
    private Employer employer;
    private boolean response_letter_required;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private Date published_at;
    private Address address;
    private String department;
    private String sort_point_distance;
    private Type type;
    private Integer id;

    public Items() {
    }


    public Salary getSalary() {
        return salary;
    }

    public Employer getEmployer() {
        return employer;
    }

    public String getName() {
        return name;
    }

    public String getAlternate_url() {
        return alternate_url;
    }

    public Date getPublished_at() {
        return published_at;
    }

    public Integer getId() {
        return id;
    }
}
