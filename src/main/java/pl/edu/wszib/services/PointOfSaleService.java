package pl.edu.wszib.services;

import pl.edu.wszib.model.PointOfSale;

import java.util.List;

public interface PointOfSaleService {

    PointOfSale getPointOfSaleById(Long id);

    PointOfSale getPointOfSaleByName(String name);

    PointOfSale getPointOfSaleByLocation(String location);

    List<PointOfSale> getAllPointOfSale();

    PointOfSale addPointOfSale(PointOfSale pointOfSale);

    PointOfSale updatePointOfSale(Long pointOfSaleId, PointOfSale pointOfSale);

    void deletePointOfSale(Long id);

}
