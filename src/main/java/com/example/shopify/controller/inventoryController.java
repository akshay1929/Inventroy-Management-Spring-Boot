package com.example.shopify.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.IOException;
import java.util.*;

import javax.servlet.http.HttpServletResponse;

import com.example.shopify.domain.Product;
import com.example.shopify.service.inventoryService;

@Controller
public class inventoryController {
	@Autowired
    private inventoryService service;
 
    @GetMapping("/")
    public String viewHomePage(Model model) {
        List<Product> listproduct = service.listAll();
        model.addAttribute("listproduct", listproduct);
        System.out.print("Get / ");
        return "index";
    }
 
    @GetMapping("/new")
    public String add(Model model) {
        model.addAttribute("product", new Product());
        return "new";
    }
 
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveProduct(@ModelAttribute("product") Product prod) {
        service.save(prod);
        return "redirect:/";
    }
 
    @RequestMapping("/edit/{id}")
    public String showEditProductPage(@PathVariable(name = "id") int id, Model model) {
        Product product = service.get(id);
        model.addAttribute("product", product);
        return "new";
    }
    
    @RequestMapping("/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") int id) {
        service.delete(id);
        return "redirect:/";
    }
    
    @GetMapping("/export")
    public void exportToCSV(HttpServletResponse response) throws IOException {
    	response.setContentType("text/csv");
    	String filename = "inventory.csv";
    	String headerkey = "Content-Disposition";
    	String headervalue = "attachment; filename=" + filename;
    	
    	response.setHeader(headerkey, headervalue);
    	
    	List<Product> listproduct = service.listAll();
    	
    	ICsvBeanWriter  writer = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
    	
    	String [] csvHeader = {"Product ID", "Product Name", "Price", "Quantity", "Description", "Type"};
    	String [] mapping =  {"id", "productname", "price", "quantity", "description", "type"};
    	
    	writer.writeHeader(csvHeader);
    	
    	for (Product p : listproduct) {
    		writer.write(p, mapping);
    	}
    	writer.close();
    	
    }
    
}
