package com.visa.prj.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.visa.prj.entity.Customer;
import com.visa.prj.entity.Item;
import com.visa.prj.entity.Order;
import com.visa.prj.entity.Product;
import com.visa.prj.service.OrderService;

@Controller
public class ProductController {
	@Autowired // it is managed by spring container so autowired is used else ctx.getBean
	private OrderService os;
	@RequestMapping("listproducts.do")
	public ModelAndView getProducts() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("list.jsp");
		mav.addObject("products",os.fetchProducts());  // list name in list.jsp
		return mav;
	}
	@RequestMapping("productform.do")
	public ModelAndView getProductForm() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("form.jsp");
		mav.addObject("product", new Product());
		return mav;
	}
	@RequestMapping("addProduct.do")
	public String addProduct(@ModelAttribute("product") Product p, Model m) {
		os.insertProduct(p);
		m.addAttribute("msg","Product added successfully!!");
		return "index.jsp";
	}
	@RequestMapping("cart.do")
	public String addToCart(HttpServletRequest req) {	
		String[] prds = req.getParameterValues("prds"); // only product ids
		Order o = new Order();
		List<Item> items = new ArrayList<Item>();
		// Get product for product id
		for (String s : prds) {
			Product p = os.getById(Integer.parseInt(s));
			Item i = new Item();
			i.setProduct(p);
			i.setAmount(p.getPrice());
			i.setQty(1);
			items.add(i);
		}
		o.setItems(items);
		Customer c = new Customer();
		String email = req.getSession().getAttribute("user").toString();
		c.setEmail(email);
		//c.setFirstName(email.split("@")[0]);
		o.setCustomer(c);
		os.placeOrder(o);
		// create Item object
		// add product to item
		// create Order object
		//set customer to order based on who has logged
		// place order
		return "index.jsp";
	}
}