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

@WebServlet("/order")
public class OrderServlet extends HttpServlet {

    private OrderRepository orderRepository = new OrderRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle a GET request, such as getting all orders
        List<Order> allOrders = orderRepository.getAllOrders();
        // Convert to JSON format and send the response
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new Gson().toJson(allOrders));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle a POST request, such as adding a new order

        // Get JSON data from the request body
        BufferedReader reader = request.getReader();
        StringBuilder jsonInput = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonInput.append(line);
        }

        Order newOrder = new Gson().fromJson(jsonInput.toString(), Order.class);

        // Add new product
        orderRepository.addOrder(newOrder);

        // Send response
        response.setStatus(HttpServletResponse.SC_CREATED);
        response.getWriter().write("Order created successfully");
    }
}

