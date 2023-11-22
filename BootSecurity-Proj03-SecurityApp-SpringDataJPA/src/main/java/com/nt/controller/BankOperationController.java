package com.nt.controller;

import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/bank")
public class BankOperationController {

	@GetMapping("/home")
	public String showHome() {
		return "home";
	}
	@GetMapping("/offers")
	public String showOffers() {
		return "show_offers";
	}
	
	@GetMapping("/balance")
	public String showBalance(Map<String,Object> map) {
		map.put("account_balance", new Random().nextInt(100000000));
		return "show_balance";
	}
	
	@GetMapping("/loan")
	public String approveBalance(Map<String,Object> map) {
		map.put("approved_amount", new Random().nextInt(10000));
		return "Loan_approve";
	}
	
	@GetMapping("/denied")
	public String showAccessDeniedPage() {
		return "authorization_failure";
		
	}

}
