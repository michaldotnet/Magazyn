package pl.edu.wszib.dao;

import pl.edu.wszib.model.PointOfSale;

import java.util.List;

public interface PointOfSaleDAO {

    PointOfSale getPointOfSaleById(Long id);

    PointOfSale getPointOfSaleByName(String name);

    PointOfSale getPointOfSaleByLocation(String location);

    List<PointOfSale> getAllPointOfSale();

    PointOfSale addPointOfSale(PointOfSale pointOfSale);

    PointOfSale updatePointOfSale(PointOfSale pointOfSale);

    void deletePointOfSale(Long id);
}
