package com.verduleria.service.impl;

import com.verduleria.dao.ProductoDao;
import com.verduleria.domain.Producto;
import com.verduleria.services.ProductoService;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ProductoServiceImpl implements ProductoService {
    
    //Creacion de la Copia unica del Producto
    @Autowired
    private ProductoDao productoDao;
    
    @Override
    public List<Producto> getProductos(boolean activos){
        var lista= (List<Producto>) productoDao.findAll();
        
        if(activos){
            lista.removeIf(e -> !e.isActivo());
        }
        
        return lista;
    }

    @Override
    public Producto getProducto(Producto producto) {
        return productoDao.findById(producto.getIdProducto()).orElse(null);
    }

    @Override
    public void deleteProducto(Producto producto) {
        productoDao.delete(producto);
    }

    @Override
    public void saveProducto(Producto producto) {
        productoDao.save(producto);
    }
    
}