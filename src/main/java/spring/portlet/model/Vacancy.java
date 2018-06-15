package spring.portlet.model;

import lombok.Data;

import java.sql.Date;

@Data
public class Vacancy {

    private Integer id;
    private String vacancyName;
    private String companyName;
    private Date publishedAt;
    private Integer paymentFrom;
    private Integer paymentTo;
    private String currency;
    private String url;


    @Override
    public String toString() {
        return "Vacancy{" +
                "id=" + id +
                ", vacancyName='" + vacancyName + '\'' +
                ", companyName='" + companyName + '\'' +
                ", publishedAt=" + publishedAt +
                ", paymentFrom=" + paymentFrom +
                ", paymentTo=" + paymentTo +
                ", currency='" + currency + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}