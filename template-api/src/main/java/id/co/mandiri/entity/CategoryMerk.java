//package id.co.mandiri.entity;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.hibernate.annotations.GenericGenerator;
//import org.hibernate.annotations.Type;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "merk_category")
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//
//public class CategoryMerk {
//    @Id
//    @GenericGenerator
//    @Column(name = "id", nullable = false, length = 64)
//    private String id;
//
//    @Column(name = "name", nullable = false, length = 150)
//    private String name;
//
//    @Lob
//    @Type(type = "text")
//    @Column(name = "description")
//    private String description;
//}
