package pl.edu.wszib.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.wszib.dao.PointOfSaleDAO;
import pl.edu.wszib.model.PointOfSale;
import pl.edu.wszib.services.PointOfSaleService;

import java.util.List;

@Component
public class PointOfSaleServiceImpl implements PointOfSaleService {

    private PointOfSaleDAO pointOfSaleDAO;

    @Autowired
    public PointOfSaleServiceImpl(PointOfSaleDAO pointOfSaleDAO) {
        this.pointOfSaleDAO = pointOfSaleDAO;
    }

    @Override
    public PointOfSale getPointOfSaleById(Long id) {
        return pointOfSaleDAO.getPointOfSaleById(id);
    }

    @Override
    public PointOfSale getPointOfSaleByName(String name) {
        return pointOfSaleDAO.getPointOfSaleByName(name);
    }

    @Override
    public PointOfSale getPointOfSaleByLocation(String location) {
        return pointOfSaleDAO.getPointOfSaleByLocation(location);
    }

    @Override
    public List<PointOfSale> getAllPointOfSale() {
        return pointOfSaleDAO.getAllPointOfSale();
    }

    @Override
    public PointOfSale addPointOfSale(PointOfSale pointOfSale) {
        return pointOfSaleDAO.addPointOfSale(pointOfSale);
    }

    @Override
    public PointOfSale updatePointOfSale(Long pointOfSaleId, PointOfSale pointOfSale) {
        pointOfSale.setId(pointOfSaleId);
        return pointOfSaleDAO.updatePointOfSale(pointOfSale);
    }

    @Override
    public void deletePointOfSale(Long id) {
        pointOfSaleDAO.deletePointOfSale(id);
    }
}
