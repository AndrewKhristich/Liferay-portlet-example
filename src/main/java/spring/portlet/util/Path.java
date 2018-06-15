package spring.portlet.util;

public class Path {

    public static final String HH_API = "https://api.hh.ru/vacancies?area=1202&specialization=1&per_page=100&page=";
    public static final String TABLES_SQL_QUERY = "CREATE TABLE vacancies(\n" +
            "  id BIGINT PRIMARY KEY ,\n" +
            "  vacancy_name VARCHAR(255) DEFAULT NULL,\n" +
            "  company_name VARCHAR(255) DEFAULT NULL,\n" +
            "  publish_date TIMESTAMP DEFAULT NULL,\n" +
            "  payment_from BIGINT DEFAULT NULL,\n" +
            "  payment_to BIGINT DEFAULT NULL,\n" +
            "  currency VARCHAR(255) DEFAULT NULL,\n" +
            "  url VARCHAR(255) DEFAULT NULL\n" +
            "  );";
    public static final String DROP_TABLES_SQL_QUERY = "DROP TABLE IF EXISTS vacancies;";
}
