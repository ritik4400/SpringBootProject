package com.bank;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class AccountService {
	@Autowired
	 public AccountRepository accountRepository;
	//addnew account
	public Account createAccount( Account account ) {
		return accountRepository.save(account);
	}
	//return all account
	public List<Account> getAllAccount()
	{
		List<Account> l1 = (List<Account>)accountRepository.findAll();
		return l1 ;
		
	}
	//return specific account 
	 public  Optional<Account> getAccount( Long id) {
		 
		return accountRepository.findById(id) ;
	}
	
	
	public Account deposit(Long id, Double amount) {
		Account account = getAccount(id).orElseThrow(()->new RuntimeException("account not found"));
		account.setBalance(account.getBalance()+ amount);
		
		return accountRepository.save(account) ;
	}
	public Account withdraw(Long id, Double amount) {
		// TODO Auto-generated method stub
		Account  account  = getAccount(id).orElseThrow(()->new RuntimeException("account not found"));
		if(account.getBalance() < amount) {
			throw new RuntimeException("Insuficient funds");
		}
		account.setBalance(account.getBalance()-amount);
		return accountRepository.save(account);
	}
	
	
}
