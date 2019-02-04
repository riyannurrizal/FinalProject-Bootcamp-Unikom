package id.co.mandiri.dao;

import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesRequest;
import com.maryanto.dimas.plugins.web.commons.ui.datatables.dao.DaoCrudDataTablesPattern;
import id.co.mandiri.entity.CategoryColor;
import id.co.mandiri.repository.CategoryColorRepository;
import id.co.mandiri.utils.QueryComparator;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryColorDao implements DaoCrudDataTablesPattern<CategoryColor, String> {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private CategoryColorRepository categoryColorRepository;

    @Override
    public CategoryColor findId(String s){
        return categoryColorRepository.findOne(s);
    }

    @Override
    @Deprecated
    public List<CategoryColor> findAll(){
        return null;
    }

    @Override
    public CategoryColor save(CategoryColor categoryColor){
        return categoryColorRepository.save(categoryColor);
    }

    @Override
    public CategoryColor update(CategoryColor categoryColor){
        return categoryColorRepository.save(categoryColor);
    }

    @Override
    public boolean remove(CategoryColor categoryColor){
        categoryColorRepository.delete(categoryColor);
        return true;
    }

    @Override
    public boolean removeById(String s) {
        categoryColorRepository.delete(s);
        return true;
    }


    @Override
    public List<CategoryColor> datatables(DataTablesRequest<CategoryColor> params) {
        String baseQueryColor = "select id, name, code, description " +
                "from color_category " +
                "where 1 = 1 ";

        CategoryColor param = params.getValue();

        CategoryColorQueryCompare compare = new CategoryColorQueryCompare(baseQueryColor);
        StringBuilder query = compare.getQuery(param);
        MapSqlParameterSource values = compare.getParameters();

        switch (params.getColOrder().intValue()){
            case 0:
                if (StringUtils.equalsIgnoreCase(params.getColDir(), "asc"))
                    query.append(" order by id asc ");
                else
                    query.append(" order by id desc ");
                break;
            case 1:
                if (StringUtils.equalsIgnoreCase(params.getColDir(), "asc"))
                    query.append(" order by name asc ");
                else
                    query.append(" order by name desc ");
                break;
            case 2:
                if (StringUtils.equalsIgnoreCase(params.getColDir(), "asc"))
                    query.append(" order by code asc ");
                else
                    query.append(" order by code desc ");
                break;
            case 3:
                if (StringUtils.equalsIgnoreCase(params.getColDir(), "asc"))
                    query.append(" order by description asc ");
                else
                    query.append(" order by description desc ");
                break;
            default:
                    if (StringUtils.equalsIgnoreCase(params.getColDir(), "asc"))
                        query.append(" order by id asc ");
                    else
                        query.append(" order by id asc ");
                    break;
        }
        query.append("limit :limit offset :offset");
        values.addValue("offset", params.getStart());
        values.addValue("limit", params.getLength());

        return this.jdbcTemplate.query(query.toString(), values, (resultSet, i) ->
                new CategoryColor(
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getString("code"),
                        resultSet.getString("description")

                ));

    }

    @Override
    public Long datatables(CategoryColor param) {
        String baseQuery = "select count(*) " +
                "from color_category " +
                "where 1 = 1 ";

        CategoryColorQueryCompare compare = new CategoryColorQueryCompare(baseQuery);
        StringBuilder query = compare.getQuery(param);
        MapSqlParameterSource values = compare.getParameters();

        return this.jdbcTemplate.queryForObject(
                query.toString(),
                values,
                ((resultSet, i) -> resultSet.getLong(1))
        );
    }

    private class CategoryColorQueryCompare implements QueryComparator<CategoryColor>{

        private MapSqlParameterSource parameterSource;
        private StringBuilder query;

        CategoryColorQueryCompare (String query){
            this.parameterSource = new MapSqlParameterSource();
            this.query = new StringBuilder(query);
        }

        @Override
        public StringBuilder getQuery(CategoryColor param){
            if (StringUtils.isNotBlank(param.getId())){
                query.append(" and lower(id) like :id ");
                parameterSource.addValue("id",
                        new StringBuilder("%")
                                .append(param.getId().toLowerCase())
                                .append("%")
                                .toString());
            }

            if (StringUtils.isNotBlank(param.getName())){
                query.append(" and lower(name) like :name ");
                parameterSource.addValue("name",
                        new StringBuilder("%")
                                .append(param.getName().toLowerCase())
                                .append("%")
                                .toString());
            }

            if (StringUtils.isNotBlank(param.getCode())){
                query.append(" and lower(code) like :code ");
                parameterSource.addValue("code",
                        new StringBuilder("%")
                                .append(param.getCode().toLowerCase())
                                .append("%")
                                .toString());
            }

            if (StringUtils.isNotBlank(param.getDescription())){
                query.append(" and lower(description) like :description ");
                parameterSource.addValue("name",
                        new StringBuilder("%")
                                .append(param.getDescription().toLowerCase())
                                .append("%")
                                .toString());
            }
            return query;
        }

        @Override
        public MapSqlParameterSource getParameters(){
            return this.parameterSource;
        }
    }
}