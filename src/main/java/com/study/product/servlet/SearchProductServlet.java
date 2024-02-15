package com.study.product.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.study.product.dao.ProductDao;
import com.study.product.entity.Product;

@WebServlet("/products")
public class SearchProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SearchProductServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ProductDao productDao = ProductDao.getInstance();
		List<Product> products = productDao.getProductListAll();
		Gson gson = new Gson();
		
		response.setStatus(200);
		response.setContentType("application/json");
		response.getWriter().println(gson.toJson(products));
		
		
	}

}
