//package id.co.mandiri.dao;
//
//import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesRequest;
//import com.maryanto.dimas.plugins.web.commons.ui.datatables.dao.DaoCrudDataTablesPattern;
//import id.co.mandiri.entity.CategoryMerk;
//import id.co.mandiri.repository.CategoryMerkRepository;
//import id.co.mandiri.utils.QueryComparator;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public class MerkDao implements DaoCrudDataTablesPattern<CategoryMerk, String> {
//
//    @Autowired
//    private NamedParameterJdbcTemplate jdbcTemplate;
//
//    @Autowired
//    private CategoryMerkRepository categoryMerkRepository;
//
//    @Override
//    public CategoryMerk findId(String s) {
//        return categoryMerkRepository.findOne(s);
//    }
//
//    @Override
//    @Deprecated
//    public List<CategoryMerk> findAll() {
//        return null;
//    }
//
//    @Override
//    public CategoryMerk save(CategoryMerk categoryMerk) {
//        return categoryMerkRepository.save(categoryMerk);
//    }
//
//    @Override
//    public CategoryMerk update(CategoryMerk categoryMerk) {
//        return categoryMerkRepository.save(categoryMerk);
//    }
//
//    @Override
//    public boolean remove(CategoryMerk categoryMerk) {
//        categoryMerkRepository.delete(categoryMerk);
//        return true;
//    }
//
//    @Override
//    public boolean removeById(String s) {
//        categoryMerkRepository.delete(s);
//        return true;
//    }
//
//    @Override
//    public List<CategoryMerk> datatables(DataTablesRequest<CategoryMerk> params) {
//        String baseQuery = "select id, name_merk, description " +
//                "from merk_category " +
//                "where 1 = 1 ";
//
//        CategoryMerk param = params.getValue();
//
//        MerkQueryCompare compare = new MerkQueryCompare(baseQuery);
//        StringBuilder query = compare.getQuery(param);
//        MapSqlParameterSource values = compare.getParameters();
//
//        switch (params.getColOrder().intValue()){
//            case 0:
//                if (StringUtils.equalsIgnoreCase(params.getColDir(), "asc"))
//                    query.append(" order by id asc ");
//                else
//                    query.append(" order by id desc ");
//                break;
//            case 1:
//                if(StringUtils.equalsIgnoreCase(params.getColDir(), "asc"))
//                    query.append(" order by name asc ");
//                else
//                    query.append(" order by name desc ");
//                break;;
//            case 2:
//                if (StringUtils.equalsIgnoreCase(params.getColDir(), "asc"))
//                    query.append(" order by description asc ");
//                else
//                    query.append(" order by description desc ");
//                break;
//            default:
//
//        }
//
//    }
//
//    @Override
//    public Long datatables(CategoryMerk categoryMerk) {
//        return null;
//    }
//
//    private class MerkQueryCompare implements QueryComparator<CategoryMerk>{
//
//        private MapSqlParameterSource parameterSource;
//        private StringBuilder query;
//
//        MerkQueryCompare(String query){
//            this.parameterSource = new MapSqlParameterSource();
//            this.query = new StringBuilder(query);
//        }
//
//        @Override
//        public StringBuilder getQuery(CategoryMerk param){
//            if(StringUtils.isNoneBlank(param.getId())){
//                query.append(" and lower(id) like : id ");
//                parameterSource.addValue("id",
//                        new StringBuilder("%")
//                                .append(param.getId().toLowerCase())
//                                .append("%")
//                                .toString());
//            }
//
//            if (StringUtils.isNoneBlank(param.getName())){
//                query.append(" and lower(name_merk) like :name_merk");
//                parameterSource.addValue("name_merk", new StringBuilder("%")
//                        .append(param.getName().toLowerCase())
//                        .append("%")
//                        .toString());
//            }
//
//            if (StringUtils.isNoneBlank(param.getDescription())){
//                query.append(" and lower(description) like :description ");
//                parameterSource.addValue("description", new StringBuilder("%")
//                        .append(param.getDescription().toLowerCase())
//                        .append("%")
//                        .toString());
//            }
//            return query;
//        }
//
//        @Override
//        public MapSqlParameterSource getParameters(){
//            return this.parameterSource;
//        }
//    }
//
//
//
//
//}
