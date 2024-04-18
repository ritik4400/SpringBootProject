package com.bank;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class AccountController {

	@Autowired
	public AccountService accountService;
	//create new bank account
	@PostMapping("/add")
    public Account createAccount(@RequestBody Account account ) {
        System.out.println("Received Account: " + account); // Log the received account
        return accountService.createAccount(account);   
    }
	//get all counts 
	@GetMapping("/accounts")
	public List<Account> getAllAccount()
	{
		return accountService.getAllAccount();
		
	}
	//get specific account deatails 
	@GetMapping("/accounts/{id}")
	public  Optional<Account> getAccount(@PathVariable Long id) {
		return accountService.getAccount(id);
	}
	//deposit specific amount into account
	@PostMapping("/deposit/{id}")
	public Account deposit(@PathVariable Long id,@RequestBody Map<String,Double> request) {
		Double amount = request.get("amount");
		return accountService.deposit(id,amount);
	}
	//  withdraw specific amount into account
	@PostMapping("/withdraw/{id}")
	public Account withdraw(@PathVariable Long id,@RequestBody Map<String,Double> request) {
		Double amount = request.get("amount");
		return accountService.withdraw(id,amount);
	}
}
