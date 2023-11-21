package org.example;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {

    private OrderRepository orderRepository = new OrderRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle a GET request, such as getting all products
        List<Product> allProducts = orderRepository.getAllProducts();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new Gson().toJson(allProducts));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Processing the POST request, that is, adding a new product
        try (BufferedReader reader = request.getReader()) {
            Product newProduct = new Gson().fromJson(reader, Product.class);
            // Add new product
            orderRepository.addProduct(newProduct);
            // Send response
            response.setStatus(HttpServletResponse.SC_CREATED);
            response.getWriter().write("Product created successfully");
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Invalid JSON format");
        }
    }


    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Processing a DELETE request, for example, deleting a product by its id

        String productIdParam = request.getParameter("productId");

        if (productIdParam != null && !productIdParam.isEmpty()) {
            try {
                long productId = Long.parseLong(productIdParam);

                boolean removed = orderRepository.removeProduct(productId);

                if (removed) {
                    response.getWriter().write("Product deleted successfully");
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().write("Product not found");
                }
            } catch (NumberFormatException e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("Invalid productId format");
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("productId parameter is required");
        }
    }
}