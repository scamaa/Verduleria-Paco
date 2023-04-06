package com.verduleria.services;

import com.verduleria.domain.Producto;
import java.util.List;

public interface ProductoService {
    
    public List<Producto> getProductos(boolean activos);

    public Producto getProducto(Producto producto);

    public void deleteProducto(Producto producto);

    public void saveProducto(Producto producto);
    
}