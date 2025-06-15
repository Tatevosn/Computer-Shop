package org.ComputerShop.model.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "computers")
public class ComputerEntity extends BaseEntity {

    @NotBlank
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String brand;

    @NotBlank
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String model;


    @Column(nullable = false)
    private double price;

    @NotBlank
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String processor;

    @NotBlank
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String ram;

    @NotBlank
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String storage;

    @NotBlank
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String graphicCard;

    public ComputerEntity() {
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public String getGraphicCard() {
        return graphicCard;
    }

    public void setGraphicCard(String graphicCard) {
        this.graphicCard = graphicCard;
    }


    @Override
    public String toString() {
        return "ComputerEntity{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", price=" + price +
                ", processor='" + processor + '\'' +
                ", ram='" + ram + '\'' +
                ", storage='" + storage + '\'' +
                ", videoCard='" + graphicCard + '\'' +
                '}';
    }


}