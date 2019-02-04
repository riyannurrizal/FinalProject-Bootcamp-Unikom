package id.co.mandiri.controller;

import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesRequest;
import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesResponse;
import id.co.mandiri.dto.CategoryColorDTO;
import id.co.mandiri.dto.CategoryColorMapperRequestNew;
import id.co.mandiri.dto.CategoryColorMapperRequestUpdate;
import id.co.mandiri.entity.CategoryColor;
import id.co.mandiri.service.CategoryColorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/master/category-color")
public class CategoryColorController {

    @Autowired
    private CategoryColorService service;

    @PostMapping("/datatables")
    public DataTablesResponse<CategoryColor> datatables(
            @RequestParam(required = false, value = "draw", defaultValue = "0") Long draw,
            @RequestParam(required = false, value = "start", defaultValue = "0") Long start,
            @RequestParam(required = false, value = "length", defaultValue = "10") Long length,
            @RequestParam(required = false, value = "order[0][column]", defaultValue = "0") Long iSortCol0,
            @RequestParam(required = false, value = "order[0] [dir]", defaultValue = "asc") String sSortDir0,
            @RequestBody(required = false) CategoryColor params){

        if (params == null) params = new CategoryColor();
        log.info("draw: {}, start: {}, length: {}, type: {}", draw, start, length, params);
        return service.datatables(
                new DataTablesRequest(draw, length, start, sSortDir0, iSortCol0, params)
        );
    }

    @GetMapping("/list")
    private ResponseEntity<List<CategoryColor>> list() {
        List<CategoryColor> list = service.findAll();
        if (list.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryColor> findById(@PathVariable("id") String id){
        CategoryColor params = service.findId(id);
        if (params != null){
            return new ResponseEntity<>(params, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new CategoryColor(), HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/")
    public ResponseEntity<CategoryColor> save(@Valid @RequestBody CategoryColorDTO.CategoryColorRequestNewDTO params){
        CategoryColor value = CategoryColorMapperRequestNew.converter.convertToEntity(params);

        value = service.save(value);
        return new ResponseEntity<>(value, HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<CategoryColor> update(@Valid @RequestBody CategoryColorDTO.CategoryColorRequestUpdateDTO params){
        CategoryColor value = CategoryColorMapperRequestUpdate.converter.convertToEntity(params);
        value = service.save(value);
        return new ResponseEntity<>(value, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CategoryColor> delete(@PathVariable("id") String id){
        boolean deleted = service.removeById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }






}
