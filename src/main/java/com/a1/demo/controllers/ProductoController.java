package com.a1.demo.controllers;


import com.a1.demo.dto.ProductoDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    @Value("${proyecto.autor}")
    private String autorProyecto;

    private List<ProductoDTO> listaProductos = new ArrayList<>();

    public ProductoController() {
        listaProductos.add(new ProductoDTO(1, "Teclado Mecánico", 850.50, "Hardware"));
        listaProductos.add(new ProductoDTO(2, "Monitor 24 Pulgadas", 2900.00, "Hardware"));
        listaProductos.add(new ProductoDTO(3, "Cable de Red Cat 6", 120.00, "Redes"));
    }

    @GetMapping
    public String listarProductos(@RequestParam(required = false) String categoria, Model model) {
        List<ProductoDTO> productosAmostrar = new ArrayList<>();

        if (categoria != null && !categoria.isEmpty()) {
            for (ProductoDTO p : listaProductos) {
                if (p.getCategoria().equalsIgnoreCase(categoria)) {
                    productosAmostrar.add(p);
                }
            }
        } else {
            productosAmostrar = listaProductos;
        }

        model.addAttribute("productos", productosAmostrar);
        model.addAttribute("autor", autorProyecto); 
        return "lista"; 
    }

    @GetMapping("/{id}")
    public String verDetalle(@PathVariable int id, Model model) {
        ProductoDTO encontrado = null;
        for (ProductoDTO p : listaProductos) {
            if (p.getId() == id) {
                encontrado = p;
                break;
            }
        }
        model.addAttribute("producto", encontrado);
        return "detalle"; 
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("productoDTO", new ProductoDTO());
        return "formulario"; 
    }

    @PostMapping("/guardar")
    public String guardarProducto(@ModelAttribute ProductoDTO productoDTO) {
        productoDTO.setId(listaProductos.size() + 1);
        listaProductos.add(productoDTO);
        return "redirect:/productos";
    }

    @PostMapping("/api/crear")
    @ResponseBody
    public ProductoDTO crearDesdePostman(@RequestBody ProductoDTO nuevoProducto) {
        nuevoProducto.setId(listaProductos.size() + 1);
        listaProductos.add(nuevoProducto);
        return nuevoProducto; 
    }
}