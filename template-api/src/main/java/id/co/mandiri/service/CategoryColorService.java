package id.co.mandiri.service;

import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesRequest;
import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesResponse;
import com.maryanto.dimas.plugins.web.commons.ui.datatables.service.ServiceCrudDataTablesPattern;
import id.co.mandiri.dao.CategoryColorDao;
import id.co.mandiri.entity.CategoryColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CategoryColorService implements ServiceCrudDataTablesPattern <CategoryColor, String> {


    @Autowired
    private CategoryColorDao categoryColorDao;

    @Override
    public CategoryColor findId(String s) {
        return categoryColorDao.findId(s);
    }

    @Override
    public List<CategoryColor> findAll() {
        return null;
    }

    @Override
    @Transactional
    public CategoryColor save(CategoryColor value) {
        return categoryColorDao.save(value);
    }

    @Override
    @Transactional
    public CategoryColor update(CategoryColor value) {
        return categoryColorDao.update(value);
    }

    @Override
    @Transactional
    public boolean remove(CategoryColor value) {
        return categoryColorDao.remove(value);
    }

    @Override
    @Transactional
    public boolean removeById(String s) {
        return categoryColorDao.removeById(s);
    }

    @Override
    public DataTablesResponse<CategoryColor> datatables(DataTablesRequest<CategoryColor> params) {
        List<CategoryColor> values = categoryColorDao.datatables(params);
        Long rowCount = categoryColorDao.datatables(params.getValue());
        return new DataTablesResponse<>(values, params.getDraw(), rowCount, rowCount);
    }
}
