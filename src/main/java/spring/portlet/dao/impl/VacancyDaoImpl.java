package spring.portlet.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring.portlet.dao.VacancyDao;
import spring.portlet.model.Vacancy;
import spring.portlet.util.MyDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class VacancyDaoImpl implements VacancyDao {

    private MyDataSource dataSource;

    @Autowired
    public VacancyDaoImpl(MyDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void saveAllVacancies(List<Vacancy> list) {

        try (Connection connection = dataSource.getConnection()) {
            for (int j = 0; j < list.size(); j++) {
                Vacancy vacancy = list.get(j);
                String query = "INSERT INTO vacancies(id ,vacancy_name, company_name, url, payment_from, payment_to, publish_date, currency) VALUES(?,?,?,?,?,?,?,?)";
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setInt(1, j);
                    statement.setString(2, vacancy.getVacancyName());
                    statement.setString(3, vacancy.getCompanyName());
                    statement.setString(4, vacancy.getUrl());
                    Integer paymentFrom = vacancy.getPaymentFrom();
                    if (paymentFrom == null) {
                        statement.setNull(5, Types.BIGINT);
                    } else statement.setInt(5, paymentFrom);

                    Integer paymentTo = vacancy.getPaymentTo();
                    if (paymentTo == null) {
                        statement.setNull(6, Types.BIGINT);
                    } else statement.setInt(6, paymentTo);
                    statement.setDate(7, vacancy.getPublishedAt());
                    statement.setString(8, vacancy.getCurrency());
                    statement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Vacancy> findAllVacanciesBeyond(int index, int step) {
        List<Vacancy> vacancies = new ArrayList<>();
        int max = index * step;
        int min = max - step;
        String query = "SELECT * FROM vacancies WHERE id>'" + min + "' AND id<" + max + "";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Vacancy vacancy = new Vacancy();
                vacancy.setId(resultSet.getInt("id"));
                vacancy.setVacancyName(resultSet.getString("vacancy_name"));
                vacancy.setCompanyName(resultSet.getString("company_name"));
                vacancy.setCurrency(resultSet.getString("currency"));
                vacancy.setPublishedAt(resultSet.getDate("publish_date"));
                vacancy.setUrl(resultSet.getString("url"));
                vacancy.setPaymentTo(resultSet.getInt("payment_to"));
                vacancy.setPaymentFrom(resultSet.getInt("payment_from"));
                vacancies.add(vacancy);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vacancies;
    }

    @Override
    public int countVacancies() {
        int count = 0;
        String query = "SELECT COUNT(*) FROM vacancies";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            count = resultSet.getInt("COUNT(*)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
}
