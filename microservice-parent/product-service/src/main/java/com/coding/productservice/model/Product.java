package com.coding.productservice.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity(name = "product_service")
public class Product {

    @Id
   /* @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sequence")
    @SequenceGenerator(name="sequence", sequenceName="sequence", allocationSize=1)*/
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
}
