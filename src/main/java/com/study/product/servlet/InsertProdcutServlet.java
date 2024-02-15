package com.study.product.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.study.product.dao.ProductDao;
import com.study.product.entity.Product;

import lombok.Builder;

@WebServlet("/product")
public class InsertProdcutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public InsertProdcutServlet() {
        super();

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuilder builder = new StringBuilder();
        String readProduct = null;
        BufferedReader reader = request.getReader();
        
        while((readProduct = reader.readLine()) != null) {
            builder.append(readProduct);
        }
        
        Gson gson = new Gson();
        
        Product product = gson.fromJson(builder.toString(), Product.class);
        
        ProductDao productDao = ProductDao.getInstance();
        
        Product findProduct = ProductDao.findProductByProductName(product.getProductName()); // 수정된 부분
        
        if (findProduct != null) {
            Map<String, Object> errorMap = new HashMap<>();
            errorMap.put("errorMessage", "이미 등록된 상품입니다.");
            
            response.setStatus(400);
            response.setContentType("application/json");
            response.getWriter().println(gson.toJson(errorMap));
            return;
        }
        
        int successCount = productDao.saveProduct(product);
        
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("status", 201);
        responseMap.put("data", "응답데이터");
        responseMap.put("successCount", successCount);
        
        response.setStatus(201);
        response.setContentType("application/json");
        
        PrintWriter writer = response.getWriter();
        writer.println(gson.toJson(responseMap));
        System.out.println("추가되었습니다!");
    }


}
